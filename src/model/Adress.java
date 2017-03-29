package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adress {
	private StringProperty street;
	private StringProperty postalCode;
	private StringProperty city;
	private Country country;
	
	public Adress(){
		street = new SimpleStringProperty();
		postalCode = new SimpleStringProperty();
		city = new SimpleStringProperty();
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

}
