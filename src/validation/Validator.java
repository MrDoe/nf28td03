package validation;

import java.util.ArrayList;

public class Validator<T> {
	private ArrayList<AbstractConstraint<T>> constraints;
	
	public Validator() {
		this.constraints = new ArrayList<AbstractConstraint<T>>();
	}
	
	public void addConstraint(AbstractConstraint<T> constraint){
		if(constraint == null)
			throw new NullPointerException();
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
		return isValid;
	}
	
	public String[] getMessages(){
		ArrayList<String> messages = new ArrayList<>();
		for (AbstractConstraint<T> constraint : constraints) {
			messages.add(constraint.getMessage());
		}
		return messages.toArray(new String[messages.size()]);
	}

}
