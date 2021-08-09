import java.util.List;
import java.util.Map;

/*
 * One of the metric to measure the performance of the prediction model
 * */
public class AccuracyMeasurer implements PerformanceMeasurer{
	protected DataInfo<?> di;
	public AccuracyMeasurer(DataInfo<?> di) {
		super();
		this.di = di;
	}
	/*
	 * To measure the accuracy of the prediction
	 * @param test The testing set
	 * @param result the prediction
	 * @return the accuracy of the prediction
	 * */
	public double measure(List<? extends Instance> test, Map<? extends Instance,String> result)
	{
		/*make sure the number of instance between testing set and prediction are the same*/
		if(test.size()!=result.size())
		{
			System.out.println("The size of testing set and prediction are not matched.");
			System.exit(0);
		}else
		{
			double match = 0;
			for(int i=0;i<test.size();i++)
			{
				/*To compare the prediction and actual label instance by instance*/
				System.out.println("Instance "+ i + " class: " + test.get(i).getAttributes().get(di.getLabelNo()).toString());
				System.out.println("Instance "+ i + " prediction: " + result.get(test.get(i)));
				
				/*if match*/
				if(test.get(i).getAttributes().get(di.getLabelNo()).toString().compareTo(result.get(test.get(i)))==0)
				{
					match++;
				}
			}
			return match/test.size();
		}
		return -1;
	}
}
