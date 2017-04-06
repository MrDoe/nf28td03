package controller;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import model.Contact;
import model.Country;
import model.Group;
import validation.Validator;

public class Controller {
		private Contact model;
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
	    private ChoiceBox<Group> groups;
		private ObservableList<Group> groupsData = FXCollections.observableArrayList();

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
	    
	    private HashMap<String, Control> controls;
		public Controller(){
			this.model = new Contact();
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
//			this.nodes.put("gender", genderRadioGroup);
			this.controls.put("group", groups);
			
		}
		
		private void initData(){
			groupsData.addAll(Group.getGroups());
			countriesData.addAll(Country.getCountries());
		}
		

		@FXML
		public void initialize() {
			initNodesMapping();
			
			country.setItems(countriesData);
			groups.setItems(groupsData);

			
			lastname.textProperty().bindBidirectional(model.lastnameProperty());
			firstname.textProperty().bindBidirectional(model.firstnameProperty());
			street.textProperty().bindBidirectional(model.addressProperty().get().streetProperty());
			postalCode.textProperty().bindBidirectional(model.addressProperty().get().postalCodeProperty());
			city.textProperty().bindBidirectional(model.addressProperty().get().cityProperty());
			country.valueProperty().bindBidirectional(model.addressProperty().get().countryProperty()); // Not working ??
			birthdate.valueProperty().bindBidirectional(model.birthdateProperty());

						
			genderRadioGroup.selectedToggleProperty().addListener(
				(event, oldValue, newValue) -> {
					model.genderProperty().setValue(newValue.getUserData().toString());
				}
			);
			
			model.genderProperty().addListener(
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
			
			groups.valueProperty().bindBidirectional(model.groupProperty());
			
			save.setOnAction((event) -> {
				if(model.isValid())
					System.out.println("Les données sont valides et prêtes à être enregistrées.");
				else{
					for(Validator<?> validator : model.getValidators()){
						if(!validator.isValid()){
							setInvalid(controls.get(validator.getPropertyName()), validator.getMessages());
						}
					}
				}
			});
			
			debug.setOnAction((event) -> {
				model.debug();
			});
			
			load.setOnAction((event) -> {
				model.load();
			});
			
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
		
}
