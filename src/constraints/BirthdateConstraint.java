package constraints;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;

public class BirthdateConstraint extends DateConstraint {
	public final static String INCONSISTENT_BIRTHDATE = "Vous ne pouvez pas être né après aujourd'hui.";
	public final static String EMPTY_BIRTHDATE = "Vous devez renseigner une date de naissance.";
	public BirthdateConstraint(ObjectProperty<LocalDate> value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		boolean validFlag = true;
		if(value.get() == null){
			setMessage(EMPTY_BIRTHDATE);
			validFlag = false;
		}
		else if(value.get().isAfter(LocalDate.now())){
			setMessage(INCONSISTENT_BIRTHDATE);
			validFlag = false;
		}
		setValid(validFlag);
		return isValid();
	}

}
