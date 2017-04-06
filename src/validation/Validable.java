package validation;

import java.util.Collection;

public interface Validable {
	public boolean isValid();
	public Collection<Validator<?>> getValidators();
	public void addValidator(Validator<?> validator);
	public void addValidators(Collection<Validator<?>> validators);
}
