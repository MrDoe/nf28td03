package constraints;

public abstract class AbstractConstraint<T> {
	private boolean isValid = false;
	private String message;
	protected T value;
	
	public AbstractConstraint(T value){
		if(value == null)
			throw new NullPointerException("Validator must be initialized with an instanciated object.");
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
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
