package validation;

import java.util.ArrayList;
import java.util.Collection;

import constraints.Constraint;

public class Validator<T> {
	private ArrayList<Constraint<T>> constraints;
	private String propertyName;
	private T property;
	private boolean isValid = true;
	

	Validator(String propertyName, T property) {
		if(propertyName == null)
			throw new NullPointerException();
		if(property == null)
			throw new NullPointerException();		
		this.property = property;
		this.propertyName = propertyName;
		this.constraints = new ArrayList<Constraint<T>>();
	}
	
	public String getPropertyName(){
		return this.propertyName;
	}
	
	public void addConstraint(Constraint<T> constraint){
		if(constraint == null)
			throw new NullPointerException("Constraint cannot be null.");
		constraint.setObject(property);
		
		constraints.add(constraint);
	}

	public void addConstraint(Collection<Constraint<T>> constraintCollection){
		if(constraintCollection == null)
			throw new NullPointerException("Constraints list cannot be null.");
		for (Constraint<T> constraint : constraintCollection) {
			if(constraint != null){
				constraint.setObject(property);
				if(!constraints.contains(constraint))
					constraints.add(constraint);				
			}
		}
		
	}

	public boolean validate(){
		boolean isValid = true;
		for (Constraint<T> constraint : constraints) {
			constraint.validate();
			// Toggle valid
			if(isValid && !constraint.isValid())
				isValid = false;
		}
		this.isValid = isValid;
		return isValid;
	}
	
	public Collection<String> getMessages(){
		ArrayList<String> messages = new ArrayList<>();
		for (Constraint<T> constraint : constraints) {
			messages.add(constraint.getMessage());
		}
		return messages;
	}

	public boolean isValid() {
		return isValid;
	}
		
	@Override
	public boolean equals(Object v){
		if(v instanceof Validator<?>){
			if(this.propertyName.equals(((Validator<?>) v).getPropertyName()))
				return true;			
		}
		return false;
	}

//	public static <U extends Property<?>> Validator<U> createPropertyValidator(U property){
//		return new Validator<>(property.getName(), property);
//	}
}
