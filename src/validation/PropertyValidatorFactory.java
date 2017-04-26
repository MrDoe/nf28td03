package validation;

import constraints.Constraint;
import javafx.beans.property.Property;

public class PropertyValidatorFactory {
	private static PropertyValidatorFactory instance; 
	private PropertyValidatorFactory() {
	
	}
	
	public static PropertyValidatorFactory getInstance(){
		if(instance == null)
			instance = new PropertyValidatorFactory();
		return instance;
	}
	
	public <T extends Property<?>> Validator<T> createPropertyValidator(T property){
		return new Validator<T>(property.getName(), property);		
	}
	@SafeVarargs
	public final <T extends Property<?>> Validator<T> createPropertyValidator(T property, Constraint<T>...abstractConstraints){
		Validator<T> validator = new Validator<T>(property.getName(), property);
		for (Constraint<T> constraint : abstractConstraints) {
			validator.addConstraint(constraint);			
		}
		return validator;
	}

}
