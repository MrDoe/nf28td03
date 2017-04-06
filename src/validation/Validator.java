package validation;

import java.util.ArrayList;

import constraints.AbstractConstraint;

public class Validator<T> {
	private ArrayList<AbstractConstraint<T>> constraints;
	private String propertyName;
	private T property;
	private boolean isValid = true;
	public Validator(String propertyName, T property) {
		if(propertyName == null)
			throw new NullPointerException();
		if(property == null)
			throw new NullPointerException();
		this.property = property;
		this.propertyName = propertyName;
		this.constraints = new ArrayList<AbstractConstraint<T>>();
	}
	
	public String getPropertyName(){
		return this.propertyName;
	}
	
	public void addConstraint(AbstractConstraint<T> constraint){
		if(constraint == null)
			throw new NullPointerException();
		constraint.setObject(property);
		
		constraints.add(constraint);
	}
	
	public boolean validate(){
		boolean isValid = true;
		for (AbstractConstraint<T> constraint : constraints) {
			constraint.validate();
			// Toggle valid
			if(isValid && !constraint.isValid())
				isValid = false;
		}
		this.isValid = isValid;
		return isValid;
	}
	
	public ArrayList<String> getMessages(){
		ArrayList<String> messages = new ArrayList<>();
		for (AbstractConstraint<T> constraint : constraints) {
			messages.add(constraint.getMessage());
		}
		return messages;
	}

	public boolean isValid() {
		return isValid;
	}
}
