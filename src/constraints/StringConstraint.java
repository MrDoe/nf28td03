package constraints;

import javafx.beans.property.StringProperty;

public abstract class StringConstraint extends AbstractConstraint<StringProperty> {

	public StringConstraint(StringProperty p){
		super(p);
	}
}
