package validation;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;

public abstract class DateConstraint extends AbstractConstraint<ObjectProperty<LocalDate>> {

	public DateConstraint(ObjectProperty<LocalDate> value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
}
