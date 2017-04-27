package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country implements Externalizable{
	private StringProperty name;
	
	public Country(){
		this.name = new SimpleStringProperty();
	}

	public StringProperty nameProperty(){
		return name;
	}

	public static List<Country> getCountries(){
		ArrayList<Country> countryList = new ArrayList<Country>();
		List<String> nameList = Arrays.stream(Locale.getISOCountries()).map(x->(new Locale("", x)).getDisplayCountry()).sorted().collect(Collectors.toList());
		Country country;
		for(String name : nameList){
			country = new Country();
			country.nameProperty().setValue(name);
			countryList.add(country);
		}
		return countryList;
	}
	
	public String toString(){
		return name.getValue();
	}
	
	public Object getId(){
		return name.getValueSafe();
	}
	
	
	@Override
	public boolean equals(Object country){
		if(country instanceof Country)
		{
			if(((Country) country).getId().equals(this.getId()))
				return true;
		}		
		return false;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name.set(in.readUTF());
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name.get());
		
	}
}
