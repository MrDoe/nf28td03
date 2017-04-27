package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address implements Externalizable{
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

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		street.set(in.readUTF());
		city.set(in.readUTF());
		postalCode.set(in.readUTF());
		country.set((Country) in.readObject());
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(street.get());
		out.writeUTF(city.get());
		out.writeUTF(postalCode.get());
		out.writeObject(country.get());
	}
}
