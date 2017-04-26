package validation;

import java.util.ArrayList;
import java.util.Collection;

import validation.Editable;

public class Editing<T extends Editable> implements Validable {
	private T object;
	private ArrayList<Validator<?>> validators;
	public Editing(T object){
		if(object == null)
			throw new NullPointerException();
		this.object = object;
		validators = new ArrayList<>();
	}

	public T getData(){
		return this.object;
	}
	
	public void load(T object){
		try {
			this.object.load(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Collection<Validator<?>> getValidators(){
		return this.validators;
	}
	
	public void addValidator(Validator<?> validator){
		if(validator==null)
			throw new NullPointerException("Validator cannot be null.");
		validators.remove(validator);
		validators.add(validator);
	}
	
	public void addValidators(Collection<Validator<?>> validators){
		if(validators == null)
			throw new NullPointerException("Validators list cannot be null.");
		this.validators.removeAll(validators);
		this.validators.addAll(validators);
	}
}
