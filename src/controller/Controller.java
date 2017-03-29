package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Contact;
import model.Country;
import model.Group;

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
	    
	    
		public Controller(){
			this.model = new Contact();
			initViewValues();
		}
		
		private void initViewValues(){
			groupsData.addAll(Group.getGroups());
			
			countriesData.addAll(Country.getCountries());
			
			
		}
		

		@FXML
		public void initialize() {
			
			country.setItems(countriesData);
			groups.setItems(groupsData);

			genderF.setUserData("F");
			genderM.setUserData("M");
			
			lastname.textProperty().bindBidirectional(model.lastnameProperty());
			firstname.textProperty().bindBidirectional(model.firstnameProperty());
			street.textProperty().bindBidirectional(model.addressProperty().getValue().streetProperty());
			city.textProperty().bindBidirectional(model.addressProperty().getValue().cityProperty());
			country.valueProperty().bindBidirectional(model.addressProperty().getValue().countryProperty());
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
			
			groups.valueProperty().bindBidirectional(model.groupProperty());
			
			save.setOnAction((event) -> {
				model.validate();
			});
			
			debug.setOnAction((event) -> {
				model.debug();
			});
			
			load.setOnAction((event) -> {
				System.out.println("save button action");
			});
			
		}
		
}
