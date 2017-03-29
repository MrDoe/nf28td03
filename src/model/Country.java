package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country {
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
}
