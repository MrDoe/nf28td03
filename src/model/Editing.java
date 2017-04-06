package model;

import java.time.LocalDate;
import java.util.ArrayList;

import constraints.BirthdateConstraint;
import constraints.NotEmptyStringConstraint;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import validation.Validator;

public class Editing<T extends Editable> {
	private T object;
	private ArrayList<Validator<?>> validators;
	public Editing(T object){
		if(object == null)
			throw new NullPointerException();
		this.object = object;
		validators = new ArrayList<>();


//		Validator<StringProperty> vFirstName = new Validator<>(firstname.getName(), firstname);
//		vFirstName.addConstraint(new NotEmptyStringConstraint());
//		validators.add(vFirstName);
//
//		Validator<ObjectProperty<LocalDate>> vBirthDate = new Validator<>(birthdate.getName(), birthdate);
//		vBirthDate.addConstraint(new BirthdateConstraint());
//		validators.add(vBirthDate);
	}

	public T getData(){
		return this.object;
	}

	public boolean isValid(){
		boolean isValid = true;
		for(Validator<?> validator : validators){
			if(!validator.validate()){
				isValid = false;
				// G�rer les messages (ici print pour debug)
			}
		}
		return isValid;
	}

	public ArrayList<Validator<?>> getValidators(){
		return this.validators;
	}
}
