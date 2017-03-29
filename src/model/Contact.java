package model;

import java.util.Date;

import javafx.beans.property.StringProperty;

public class Contact {
	private StringProperty name;
	private StringProperty firstname;
	private Adress adress;
	private StringProperty birthdate;
	private StringProperty gender;
	private Group group;
	
	public StringProperty nameProperty(){
		return name;
	}

	public StringProperty firstnameProperty(){
		return firstname;
	}

	public StringProperty birthdateProperty(){
		return birthdate;
	}

	public StringProperty genderProperty(){
		return gender;
	}

}
