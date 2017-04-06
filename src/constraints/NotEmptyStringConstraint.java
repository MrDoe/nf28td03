package constraints;

import javafx.beans.property.StringProperty;

public class NotEmptyStringConstraint extends StringConstraint {
	public NotEmptyStringConstraint() {
		super();
	}

	public final static String EMPTY_STRING = "Cette valeur doit être renseignée.";

	@Override
	public boolean validate() {
		boolean validFlag = true;
		if(this.value.getValueSafe().isEmpty()){
			this.setMessage(EMPTY_STRING);
			validFlag = false;
		}
		this.setValid(validFlag);
		return isValid();
	}

}
