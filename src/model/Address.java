package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {
	private StringProperty street;
	private StringProperty postalCode;
	private StringProperty city;
	private ObjectProperty<Country> country;
	
	public Address(){
		street = new SimpleStringProperty(null, "street");
		postalCode = new SimpleStringProperty(null, "postalCode");
		city = new SimpleStringProperty(null, "city");
		country = new SimpleObjectProperty<Country>(null, "country");
	}
	
	public StringProperty streetProperty(){
		return street;
	}

	public StringProperty postalCodeProperty(){
		return postalCode;
	}

	public StringProperty cityProperty(){
		return city;
	}

	public ObjectProperty<Country> countryProperty(){
		return country;
	}	
	
	public String toString(){
		return "{ street : "+street+" ; postalCode : + "+postalCode+" ; city :"+city+" ; country : "+country+" }";
	}
}
