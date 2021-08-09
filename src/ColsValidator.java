/*
 * This class is to validate the columns of a instance
 */

public class ColsValidator implements DataValidator {
	
	protected DataInfo<?> di;
	
	public ColsValidator(DataInfo<?> di)
	{
		this.di = di;
	}
	
	public void validate(Record a) {
		
		if(a.getAttributes().size()!=di.getNoOfCol())
		{
			
			System.out.println("The set contains more/less attributes than specified");
			System.exit(0);
		}
	};
}
