package validation;

public interface Editable {
	public void debug();
	public void load();
	public void load(Editable object) throws Exception;
	public void reset();	
}
