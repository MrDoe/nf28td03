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


	public Contact(){
		lastname = new SimpleStringProperty(null, "lastname");
		firstname = new SimpleStringProperty(null, "firstname");
		address = new SimpleObjectProperty<Address>(null, "address", new Address());
		birthdate = new SimpleObjectProperty<LocalDate>(null, "birthdate");
		gender = new SimpleStringProperty(null, "gender");
		group = new SimpleObjectProperty<Group>(null,"group",new Group());

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
		Address address = this.address.get();
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
		Group g = new Group();
		g.nameProperty().set("Groupe 1");
		group.set(g);
	}

	@Override
	public void reset() {
		System.out.println("TODO : reset values");

	}

}
