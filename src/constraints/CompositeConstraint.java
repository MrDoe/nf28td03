package constraints;

public interface CompositeConstraint {
	public void add(Constraint<?> constraint);
	public void remove(Constraint<?> constraint);
	
}
