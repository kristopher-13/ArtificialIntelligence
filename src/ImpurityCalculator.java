import java.util.List;
import java.util.TreeMap;

public abstract class ImpurityCalculator {
protected DataInfo<String> di;
	
	public ImpurityCalculator(DataInfo<String> di) {
		super();
		this.di = di;
	}
	public TreeMap<Object,TreeMap<String,Long>> getDistributionMap(List<Record> dataSet, int i)
	{
		TreeMap<Object,TreeMap<String,Long>> currentImpurityMap = new TreeMap<Object,TreeMap<String,Long>>();
		for(Record instance:dataSet)
		{
			TreeMap<String,Long> tempCount = currentImpurityMap.getOrDefault(instance.getObject(i), new TreeMap<String,Long>()); //get the inner map
			long count = tempCount.getOrDefault(instance.getObject(di.getLabelNo()),(long) 0);	//get the label
			count++;	//increase the count for that label
			tempCount.put(di.convert(instance.getObject(di.getLabelNo())), count);	//put it back to inner map
			currentImpurityMap.put(di.convert(instance.getObject(i)),tempCount);	//put it back to outer map
		}
		return currentImpurityMap;
	}
	public abstract double calculate(List<Record> dataSet, int index);
	public abstract TreeMap<Double,Integer> calculate(List<Record> dataSet,List<Integer> indexList);
}
