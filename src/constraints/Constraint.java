package constraints;

public abstract class Constraint<T> {
	private boolean isValid = false;
	private String message;
	protected T value;
	
	Constraint(){
		
	}
	Constraint(String message){
		this.message = message; 
	}
	public void setObject(T value){
		if(value == null)
			throw new NullPointerException("Validator must be initialized with an instanciated object.");
		this.value = value;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(this.message == null || this.message.isEmpty())
			this.message = message;
	}

	public abstract boolean validate();

	public boolean isValid() {
		return isValid;
	}

	protected void setValid(boolean isValid) {
		this.isValid = isValid;
	}	
	
}
