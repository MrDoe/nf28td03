package controller;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Contact;
import model.Country;
import model.Group;
import model.Model;
import validation.Validator;

public class Controller {
		private Contact editingContact;
		private Model model;
		// FXMl items declarations :

	    @FXML
	    private ChoiceBox<Country> country;
		private ObservableList<Country> countriesData = FXCollections.observableArrayList();

	    @FXML
	    private TextField firstname;

	    @FXML
	    private DatePicker birthdate;

	    @FXML
	    private RadioButton genderM;

	    @FXML
	    private TextField city;

	    @FXML
	    private TextField street;

	    @FXML
	    private TextField postalCode;

	    @FXML
	    private Button save;

	    @FXML
	    private Button addItem;

	    @FXML
	    private Button removeItem;


	    @FXML
	    private RadioButton genderF;

	    @FXML
	    private TextField lastname;

	    @FXML
	    private Button load;

	    @FXML
	    private ToggleGroup genderRadioGroup;


	    @FXML
	    private Button debug;

	    @FXML
	    private TreeView<Object> listeContacts;

	    private HashMap<String, Control> controls;
		public Controller(){
			this.editingContact = new Contact();
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

		private void initData(){
			countriesData.addAll(Country.getCountries());
		}


		@FXML
		public void initialize() {
			initNodesMapping();

			country.setItems(countriesData);



			save.setOnAction((event) -> {
				if(editingContact.isValid())
					System.out.println("Les donn�es sont valides et pr�tes � �tre enregistr�es.");
				else{
					for(Validator<?> validator : editingContact.getValidators()){
						if(!validator.isValid()){
							setInvalid(controls.get(validator.getPropertyName()), validator.getMessages());
						}
					}
				}
			});

			debug.setOnAction((event) -> {
				editingContact.debug();
			});

			load.setOnAction((event) -> {
				editingContact.load();
			});

            createTree();
		}

		public void initContactBindings(){
			lastname.textProperty().bindBidirectional(editingContact.lastnameProperty());
			firstname.textProperty().bindBidirectional(editingContact.firstnameProperty());
			street.textProperty().bindBidirectional(editingContact.addressProperty().get().streetProperty());
			postalCode.textProperty().bindBidirectional(editingContact.addressProperty().get().postalCodeProperty());
			city.textProperty().bindBidirectional(editingContact.addressProperty().get().cityProperty());
			country.valueProperty().bindBidirectional(editingContact.addressProperty().get().countryProperty()); // Not working ??
			birthdate.valueProperty().bindBidirectional(editingContact.birthdateProperty());


			genderRadioGroup.selectedToggleProperty().addListener(
				(event, oldValue, newValue) -> {
					editingContact.genderProperty().setValue(newValue.getUserData().toString());
				}
			);

			editingContact.genderProperty().addListener(
				(event, oldValue, newValue) -> {
					if(newValue.equals("M"))
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
        }

		public void setInvalid(Control node){
			if(node == null)
				throw new NullPointerException();
			node.setStyle("-fx-border-color: red;");
		}

		public void setInvalid(Control node, ArrayList<String> messages){
			setInvalid(node);
			Tooltip tooltip = new Tooltip();
			String tooltipMsg = "";
			for (String message : messages) {
				tooltipMsg += message+"\n";
			}
			tooltip.setText(tooltipMsg);
			node.setTooltip(tooltip);
		}

		public void creatNewGroup(){
			Group g = new Group();
			g.nameProperty().set(Group.DEFAULT_GROUP_NAME);
			model.groupsProperty().add(g);
		}

}
