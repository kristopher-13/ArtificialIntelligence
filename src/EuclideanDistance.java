import java.util.List;

public class EuclideanDistance extends DistanceCalculator {
	DataInfo<Double> di;
	
	public EuclideanDistance(DataInfo<Double> di) {
		super();
		this.di = di;
	}

	@Override
	public double calculate(Record a, Record b) {

		double result = 0;
		List<Double> aList = di.convert(a.getAttributes());
		List<Double> bList = di.convert(b.getAttributes());

		for(int i=0;i<aList.size();i++)
		{
			result += Math.pow(aList.get(i)-bList.get(i), 2);
		}
		Math.sqrt(result);
		return result;		
	}

	public double calculate(Record a, Record b, List<Double> rangeArr) {

		double result = 0;
		List<Double> aList = di.convert(a.getAttributes());
		List<Double> bList = di.convert(b.getAttributes());

		for(int i=0;i<aList.size();i++)
		{
			result += Math.pow(aList.get(i)-bList.get(i), 2)/Math.pow(rangeArr.get(i),2);
		}
		Math.sqrt(result);
		return result;		
	}
}
