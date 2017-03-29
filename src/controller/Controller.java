package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
		
		public Controller(){
			this.model = new Contact();
			initViewValues();
		}
		
		private void initViewValues(){
			groupsData.addAll(Group.getGroups());
			
			countriesData.addAll(Country.getCountries());
//			Country countryTest = new Country();
//			countryTest.nameProperty().setValue("TOTO");
//			countriesData.add(countryTest);
//			for(Country countryItem : Country.getCountries()){
//				
//			}
			
		}
		

		@FXML
		public void initialize() {
			save.setOnAction((event) -> {
				System.out.println("save button action");
			});
			
			country.setItems(countriesData);
			groups.setItems(groupsData);
		}
		
}
