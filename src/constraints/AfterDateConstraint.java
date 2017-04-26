package constraints;
import java.time.LocalDate;

public class AfterDateConstraint extends DateConstraint {
	
	public final static String INCONSISTENT_DATE = "Vous ne pouvez pas �tre n� apr�s aujourd'hui.";
	private LocalDate compareDate;
	
	public AfterDateConstraint(){
		this.compareDate = LocalDate.now();
	}
	
	public AfterDateConstraint(LocalDate date) {
		super();
		if(date == null)
			throw new NullPointerException("The date to compare cannot be null.");
		this.setCompareDate(date);
	}
	
	@Override
	public boolean validate() {
		boolean validFlag = true;
		
		if(value.get().isAfter(LocalDate.now())){
			setMessage(INCONSISTENT_DATE);
			validFlag = false;
		}
		setValid(validFlag);
		return isValid();
	}
	public LocalDate getCompareDate() {
		return compareDate;
	}
	public void setCompareDate(LocalDate compareDate) {
		this.compareDate = compareDate;
	}

}
