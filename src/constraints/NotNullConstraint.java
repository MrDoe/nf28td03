package constraints;

import javafx.beans.property.ObjectProperty;

public class NotNullConstraint extends Constraint<ObjectProperty<?>> {
	public final static String NULL_VALUE = "Cette valeur doit �tre renseign�e.";

	@Override
	public boolean validate() {
		boolean validFlag = true;
		if(this.value.get() == null){
			this.setMessage(NULL_VALUE);
			validFlag = false;
		}
		this.setValid(validFlag);
		return isValid();
	}

}
