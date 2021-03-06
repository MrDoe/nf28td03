package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import constraints.BirthdateConstraint;
import constraints.NotEmptyStringConstraint;
import constraints.NotNullConstraint;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import model.Contact;
import model.Country;
import model.Group;
import model.Model;
import validation.Editing;
import validation.PropertyValidatorFactory;
import validation.Validator;

public class Controller {
		private Editing<Contact> editingContact;
		private Contact originalContact = null;
		private TreeItem<Object> originalContactItem = null;
		private Model model;

	    private HashMap<String, Control> controls;
	    private String groupIcon = "resources/group.png";
	    private String contactIcon = "resources/contact.png";
	    private ObservableList<Country> countriesData = FXCollections.observableArrayList();
	    private TreeItem<Object> selectedItem;
	    // FXMl items declarations :

	    @FXML
	    private Menu menu_fichier;

	    @FXML
	    private ChoiceBox<Country> country;

	    @FXML
	    private Button removeItem;

	    @FXML
	    private TextField firstname;

	    @FXML
	    private MenuItem menu_fichier_load;

	    @FXML
	    private DatePicker birthdate;

	    @FXML
	    private Button debug;

	    @FXML
	    private TextField city;

	    @FXML
	    private TextField postalCode;

	    @FXML
	    private Button save;

	    @FXML
	    private TextField lastname;

	    @FXML
	    private ToggleGroup genderRadioGroup;

	    @FXML
	    private Button addItem;

	    @FXML
	    private RadioButton genderM;

	    @FXML
	    private Button load;

	    @FXML
	    private TreeView<Object> listeContacts;

	    @FXML
	    private TextField street;

	    @FXML
	    private MenuItem menu_fichier_save;

	    @FXML
	    private RadioButton genderF;

	    @FXML
	    private Button tree_debug;

	    @FXML
	    private GridPane editPanel;
	    
		public Controller(){
			this.editingContact = new Editing<Contact>(new Contact());
			this.model = new Model();
			this.controls = new HashMap<>();
			initData();
		}

		private void initNodesMapping(){
			this.controls.put("firstname", firstname);
			this.controls.put("lastname", lastname);
			this.controls.put("street", street);
			this.controls.put("city", city);
			this.controls.put("postalCode", postalCode);
			this.controls.put("country", country);
			this.controls.put("birthdate", birthdate);
		}
		
		private void initValidators(){
			PropertyValidatorFactory validatorFactory = PropertyValidatorFactory.getInstance();
			ArrayList<Validator<?>> contactValidators = new ArrayList<>();
			
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().firstnameProperty(), 
					new NotEmptyStringConstraint()));
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().lastnameProperty(), 
					new NotEmptyStringConstraint()));
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().birthdateProperty(),
					new BirthdateConstraint()));
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().addressProperty().getValue().streetProperty(),
					new NotEmptyStringConstraint()));
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().addressProperty().getValue().cityProperty(),
					new NotEmptyStringConstraint()));
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().addressProperty().getValue().postalCodeProperty(),
					new NotEmptyStringConstraint()));
			contactValidators.add(validatorFactory.createPropertyValidator(
					editingContact.getData().addressProperty().getValue().countryProperty(),
					new NotNullConstraint()));			
			editingContact.addValidators(contactValidators);
		}

		private void initData(){
			countriesData.addAll(Country.getCountries());
		}


		@FXML
		public void initialize() {
			initNodesMapping();
			initValidators();
			initContactBindings();
			
			editPanel.setVisible(false);
			
			country.setItems(countriesData);
			
			save.setOnAction((event) -> {
				if(editingContact.isValid()){
					for (Entry<String, Control> control : controls.entrySet()) {
						setValid(control.getValue());
					}
					System.out.println(originalContact);
					if(originalContact != null){
						try {
							originalContact.load(editingContact.getData());
						    TreeModificationEvent<Object> event1 = new TreeModificationEvent<>(TreeItem.valueChangedEvent(), originalContactItem);
						    Event.fireEvent(originalContactItem, event1);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						originalContact = null;
						originalContactItem = null;
				} else {
						Contact newContact = new Contact(editingContact.getData());
						getCurrentGroup().addContact(newContact);						
					}
					editPanel.setVisible(false);
				}
				else{
					for(Validator<?> validator : editingContact.getValidators()){
						if(!validator.isValid())
							setInvalid(controls.get(validator.getPropertyName()), validator.getMessages());						
						else
							setValid(controls.get(validator.getPropertyName()));
					}
				}
			});

			debug.setOnAction((event) -> {
				editingContact.getData().debug();
			});

			load.setOnAction((event) -> {
				editingContact.getData().load();
			});

			tree_debug.setOnAction((event) -> {
				model.debug();
			});

			addItem.setOnAction((event) -> {
				MultipleSelectionModel<TreeItem<Object>> selectionModel = listeContacts.getSelectionModel(); 
				if(selectionModel.getSelectedItem() != null){
					if(selectionModel.getSelectedItem().equals(listeContacts.getRoot())){
						createNewGroup();
					}
					if(selectionModel.getSelectedItem().getValue() instanceof Group){
						editPanel.setVisible(true);
						createNewContact((Group) selectionModel.getSelectedItem().getValue());
					}
				}
			});

			removeItem.setOnAction((event) -> {
				MultipleSelectionModel<TreeItem<Object>> selectionModel = listeContacts.getSelectionModel(); 
				if(selectionModel.getSelectedItem() != null){
					if(selectionModel.getSelectedItem().getValue() instanceof Group){
						model.removeGroup((Group) selectionModel.getSelectedItem().getValue());
					}
					if(selectionModel.getSelectedItem().getValue() instanceof Contact){
						Contact toRemove = (Contact) selectionModel.getSelectedItem().getValue();
						toRemove.groupProperty().getValue().removeContact(toRemove);
						
					}
				}
			});
			
			menu_fichier_save.setOnAction((event) -> {
				FileChooser fc = new FileChooser();
				fc.setTitle("S�lection d'un fichier de sauvegarde...");
				fc.getExtensionFilters().addAll(
				         new ExtensionFilter("Out file", "*.out"));
				Window window = editPanel.getScene().getWindow();
				 File selectedFile = fc.showSaveDialog(window);
				 if (selectedFile != null) {
				    System.out.println("writing");
				    try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile));
						oos.writeInt(model.groupsProperty().size());
					    for(Group group : model.groupsProperty()){
					    	oos.writeObject(group);
					    }
					    oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }

			});
			
			menu_fichier_load.setOnAction((event) -> {
				FileChooser fc = new FileChooser();
				fc.setTitle("S�lection d'un fichier de chargement...");
				fc.getExtensionFilters().addAll(
				         new ExtensionFilter("Out file", "*.out"));
				Window window = editPanel.getScene().getWindow();
				 File selectedFile = fc.showOpenDialog(window);
				 if (selectedFile != null) {
				    System.out.println("writing");
				    try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
						int nbGroups = ois.readInt();
						for(int i = 0; i < nbGroups; i++){
							model.addGroup((Group) ois.readObject());
						}
						ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				
			});
			
            createTree();
            initTreeBindings();
		}
		
		public void initTreeBindings(){
			listeContacts.getRoot().setValue(model);
			ListChangeListener<Contact> listenerContactList = new ListChangeListener<Contact>() {
				
				@Override
				public void onChanged(Change<? extends Contact> c) {
					System.out.println("list contact changed");
					while(c.next()){
						if(c.wasAdded()){
							List<? extends Contact> contactList = c.getAddedSubList();
							for (Contact contact : contactList) {
								TreeItem<Object> contactItem = new TreeItem<Object>(contact, new ImageView(contactIcon));
								for(TreeItem<Object> groupItem : listeContacts.getRoot().getChildren()){
									if(groupItem.getValue().equals(contact.groupProperty().getValue())){
										groupItem.getChildren().add(contactItem);
									}
								}
							}							
						}
						if(c.wasRemoved()){
							List<? extends Contact> contactList = c.getRemoved();
							for (Contact contact : contactList) {
								TreeItem<Object> itemToRemove = null;
								for(TreeItem<Object> groupItem : listeContacts.getRoot().getChildren()){
									Group tmpGroup = (Group) groupItem.getValue(); 
									if((tmpGroup).equals(contact.groupProperty().getValue())){
										for (TreeItem<Object> contactItem : groupItem.getChildren()) {
											if(contactItem.getValue().equals(contact))
												itemToRemove = contactItem;
										}
										if(itemToRemove != null){
											groupItem.getChildren().remove(itemToRemove);
										}
										
									}
								}
							}
						}
					}
				}
			};
			
			
			
			ListChangeListener<Group> listenerGroupList = new ListChangeListener<Group>() {
				
				@Override
				public void onChanged(Change<? extends Group> c) {
					while(c.next()){
						if(c.wasAdded()){
							List<? extends Group> groupList = c.getAddedSubList();
							for (Group group : groupList) {
								TreeItem<Object> grpItem = new TreeItem<Object>(group, new ImageView(groupIcon));
								listeContacts.getRoot().getChildren().add(grpItem);
								grpItem.setExpanded(true);
								group.contactsProperty().addListener(listenerContactList);
							}
						}
						if(c.wasRemoved()){
							List<? extends Group> groupList = c.getRemoved();
							for (Group group : groupList) {
								TreeItem<Object> itemToRemove = null;
								for(TreeItem<Object> groupItem : listeContacts.getRoot().getChildren()){
									if(((Group) groupItem.getValue()).equals(group))
										itemToRemove = groupItem;
								}
								if(itemToRemove != null){
									listeContacts.getRoot().getChildren().remove(itemToRemove);
								}
							}
						}
						
					}
					
				}
			};
			
			model.groupsProperty().addListener(listenerGroupList);
			
			
			ChangeListener<TreeItem<Object>> treeListener = new ChangeListener<TreeItem<Object>>() {
				
				@Override
				public void changed(ObservableValue<? extends TreeItem<Object>> observable, TreeItem<Object> oldValue, TreeItem<Object> newValue) {
					if(!(newValue.getValue() instanceof Contact)){
						editPanel.setVisible(false);
						originalContact = null;
						originalContactItem = null;
						return;
					}
					originalContact = (Contact) newValue.getValue();
					originalContactItem = newValue;
					editingContact.load(originalContact);
					editPanel.setVisible(true);
				}
			};
			listeContacts.getSelectionModel().selectedItemProperty().addListener(treeListener);
			
		}
		
		public void initContactBindings(){
			lastname.textProperty().bindBidirectional(editingContact.getData().lastnameProperty());
			firstname.textProperty().bindBidirectional(editingContact.getData().firstnameProperty());
			street.textProperty().bindBidirectional(editingContact.getData().addressProperty().get().streetProperty());
			postalCode.textProperty().bindBidirectional(editingContact.getData().addressProperty().get().postalCodeProperty());
			city.textProperty().bindBidirectional(editingContact.getData().addressProperty().get().cityProperty());
			country.valueProperty().bindBidirectional(editingContact.getData().addressProperty().get().countryProperty());
			birthdate.valueProperty().bindBidirectional(editingContact.getData().birthdateProperty());


			genderRadioGroup.selectedToggleProperty().addListener(
				(event, oldValue, newValue) -> {
					editingContact.getData().genderProperty().setValue(newValue.getUserData().toString());
				}
			);

			editingContact.getData().genderProperty().addListener(
				(event, oldValue, newValue) -> {
					if(newValue == null)
						genderRadioGroup.selectToggle(genderF);
					else if(newValue.equals("M"))
						genderRadioGroup.selectToggle(genderM);
					else
						genderRadioGroup.selectToggle(genderF);
				}
			);

			genderF.setUserData("F");
			genderM.setUserData("M");

			genderRadioGroup.selectToggle(genderF);
        }

        public void createTree(Object... rootItems) {

			TreeItem<Object> root = new TreeItem<>("Fiche de contacts");
			listeContacts.setRoot(root);
			listeContacts.getRoot().setExpanded(true);
			listeContacts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			listeContacts.setCellFactory(param -> new TextFieldTreeCellImpl());
        }

		public void setValid(Control node){
			if(node == null)
				throw new NullPointerException();
			node.setStyle("");
			node.setTooltip(null);
		}
		public void setInvalid(Control node){
			if(node == null)
				throw new NullPointerException();
			node.setStyle("-fx-border-color: red;");
		}

		public void setInvalid(Control node, Collection<String> collection){
			setInvalid(node);
			Tooltip tooltip = new Tooltip();
			String tooltipMsg = "";
			for (String message : collection) {
				tooltipMsg += message+"\n";
			}
			tooltip.setText(tooltipMsg);
			node.setTooltip(tooltip);
		}

		public void createNewGroup(){
			Group g = new Group();
			g.nameProperty().set(Group.DEFAULT_GROUP_NAME);
			model.addGroup(g);
		}

		public void removeGroup(Group g){
			model.removeGroup(g);
		}

		public void createNewContact(Group group){
			editingContact.getData().reset();
			editingContact.getData().groupProperty().set(group);
		}

		public Group getCurrentGroup(){
			if(selectedItem.getValue() instanceof Group)
				return (Group) selectedItem.getValue();
			return null; 
				
		}

	    private final class TextFieldTreeCellImpl extends TreeCell<Object> {
	    	 
	        private TextField textField;
	 
	        public TextFieldTreeCellImpl() {
	        }
	 
	        @Override
	        public void startEdit() {
	            super.startEdit();
	            if(!(getItem() instanceof Group)){
	            	return;
	            }
	            if (textField == null) {
	                createTextField();
	            }
	            setText(null);
	            setGraphic(textField);
	            textField.selectAll();
	        }
	 
	        @Override
	        public void cancelEdit() {
	            super.cancelEdit();
	            if(!(getItem() instanceof Group)){
	            	return;
	            }
	            Group group = (Group) getItem();
	            setText(group.toString());
	            setGraphic(getTreeItem().getGraphic());
	        }
	 
	        @Override
	        public void updateItem(Object item, boolean empty) {
	            super.updateItem(item, empty);
	 
	            if (empty) {
	                setText(null);
	                setGraphic(null);
	            } else {
	                if (isEditing()) {
	                    if (textField != null) {
	                        textField.setText(getString());
	                    }
	                    setText(null);
	                    setGraphic(textField);
	                } else {
	                    setText(getString());
	                    setGraphic(getTreeItem().getGraphic());
	                }
	            }
	        }
	 
	        private void createTextField() {
	            textField = new TextField(getString());
	            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
	 
	                @Override
	                public void handle(KeyEvent t) {
	                    if (t.getCode() == KeyCode.ENTER) {
	                        Group group = (Group) getItem();
	                        group.nameProperty().setValue(textField.getText());
	                        commitEdit(group);
	                    } else if (t.getCode() == KeyCode.ESCAPE) {
	                        cancelEdit();
	                    }
	                }
	            });
	        }
	 
	        private String getString() {
	            return getItem() == null ? "" : getItem().toString();
	        }
	    }}
