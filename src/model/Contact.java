package model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import validation.Editable;

public class Contact implements Editable{
	private StringProperty lastname;
	private StringProperty firstname;
	private ObjectProperty<Address> address;
	private ObjectProperty<LocalDate> birthdate;
	private StringProperty gender;
	private ObjectProperty<Group> group;


	public Contact(Contact contact){
		lastname = new SimpleStringProperty(null, "lastname", contact.lastname.getValue());
		firstname = new SimpleStringProperty(null, "firstname", contact.firstname.getValue());
		Address address = new Address();
		address.streetProperty().setValue(contact.address.getValue().streetProperty().getValue());
		address.cityProperty().setValue(contact.address.getValue().cityProperty().getValue());
		address.postalCodeProperty().setValue(contact.address.getValue().postalCodeProperty().getValue());
		address.countryProperty().setValue(contact.address.getValue().countryProperty().getValue());
		this.address = new SimpleObjectProperty<Address>(null, "address", address);
		birthdate = new SimpleObjectProperty<LocalDate>(null, "birthdate", contact.birthdate.getValue());
		gender = new SimpleStringProperty(null, "gender", contact.gender.getValue());
		group = new SimpleObjectProperty<Group>(null,"group", contact.group.getValue());
	}
	
	public Contact(){
		lastname = new SimpleStringProperty(null, "lastname");
		firstname = new SimpleStringProperty(null, "firstname");
		address = new SimpleObjectProperty<Address>(null, "address", new Address());
		birthdate = new SimpleObjectProperty<LocalDate>(null, "birthdate");
		gender = new SimpleStringProperty(null, "gender");
		group = new SimpleObjectProperty<Group>(null,"group");
		
//		validators.put(firstname, new ArrayList<>());
//		validators.get(firstname).add(new NotEmptyStringConstraint(firstname));
//		validators.put(lastname, new ArrayList<>());
//		validators.get(lastname).add(new NotEmptyStringConstraint(lastname));
//		validators.put(birthdate, new ArrayList<>());
//		validators.get(birthdate).add(new BirthdateConstraint(birthdate));



//		validators = new ArrayList<>();
//		validators.add(new NotEmptyStringValidator(lastname));
//		validators.add(new NotEmptyStringValidator(firstname));
//		validators.add(new BirthdateValidator(birthdate));
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
//		System.out.println("----------- BEGIN test part ---------------");
//
//		System.out.println("----------- END test part ---------------");
	}

	public void load(){
		// sample data :
		firstname.setValue("Nicolas");
		lastname.setValue("Rigaud");
		Address address = this.address.getValue();
		address.streetProperty().setValue("28 quater rue d'Amiens Apt 5C");
		address.postalCodeProperty().setValue("60200");
		address.cityProperty().setValue("Compi�gne");
//
		Country c = new Country();
		c.nameProperty().set("France");
		address.countryProperty().set(c);

//

		birthdate.set(LocalDate.of(1993, 3, 15));
		gender.set("M");

		// Groupes ??
//		Group g = new Group();
//		g.nameProperty().set("Groupe 1");
//		group.set(g);
	}

	@Override
	public void reset() {
		firstname.setValue(null);
		lastname.setValue(null);
//		address.setValue(new Address());
		address.getValue().streetProperty().setValue(null);
		address.getValue().cityProperty().setValue(null);
		address.getValue().postalCodeProperty().setValue(null);
		address.getValue().countryProperty().setValue(null);
		birthdate.setValue(null);
		gender.setValue(null);
		group.setValue(null);
	}
	
	public String toString(){
		return firstname.getValueSafe()+" "+lastname.getValueSafe();
	}

	@Override
	public void load(Editable object) throws Exception {
		if(!(object instanceof Contact))
			throw new Exception("Must have a Contact instance to perform loading of this Editable.");
		reset();
		Contact from = (Contact) object;
		lastname.setValue(from.lastname.getValue());
		firstname.setValue(from.firstname.getValue());
		address.getValue().streetProperty().setValue(from.address.getValue().streetProperty().getValue());
		address.getValue().cityProperty().setValue(from.address.getValue().cityProperty().getValue());
		address.getValue().postalCodeProperty().setValue(from.address.getValue().postalCodeProperty().getValue());
		address.getValue().countryProperty().setValue(from.address.getValue().countryProperty().getValue());
		birthdate.setValue(from.birthdate.getValue());
		gender.setValue(from.gender.getValue());
		group.setValue(from.group.getValue());
	}

}
