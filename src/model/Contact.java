package model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
	private StringProperty lastname;
	private StringProperty firstname;
	private ObjectProperty<Address> address;
	private ObjectProperty<LocalDate> birthdate;
	private StringProperty gender;
	private ObjectProperty<Group> group;
	
	public Contact(){
		lastname = new SimpleStringProperty();
		firstname = new SimpleStringProperty();
//		Address 
		address = new SimpleObjectProperty<Address>(new Address());
		birthdate = new SimpleObjectProperty<LocalDate>();
		gender = new SimpleStringProperty();
		group = new SimpleObjectProperty<Group>();
	}
	
	public StringProperty lastnameProperty(){
		return lastname;
	}

	public StringProperty firstnameProperty(){
		return firstname;
	}

	public ObjectProperty<LocalDate> birthdateProperty(){
		return birthdate;
	}

	public StringProperty genderProperty(){
		return gender;
	}
	
	public ObjectProperty<Group> groupProperty(){
		return group;
	}
	
	public ObjectProperty<Address> addressProperty(){
		return address;
	}
	
	
	
	public void debug(){
		System.out.println("lastname : "+lastname);
		System.out.println("firstname : "+firstname);
		System.out.println("address : "+ address);
		System.out.println("birthdate : "+ birthdate);
		System.out.println("gender : " + gender);
		System.out.println("group : " +  group);
	}
	
	public void load(){
		
	}
	
	public void validate(){
		
	}

}
