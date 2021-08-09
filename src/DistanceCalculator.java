import java.util.List;

public abstract class DistanceCalculator {
	DataInfo<Double> di;
	
	public abstract double calculate(Record a, Record b);
	public abstract double calculate(Record a, Record b,List<Double> rangeArr);
}
