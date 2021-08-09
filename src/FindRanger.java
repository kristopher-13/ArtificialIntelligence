import java.util.ArrayList;
import java.util.List;

public class FindRanger {
	private DataInfo<Double> di;
	
	public FindRanger(DataInfo<Double> di) {
		super();
		this.di = di;
	}

	public List<Double> findRange(List<Record> dataSet)
	{
		List<Double> result = new ArrayList<Double>();
		for(int index=0;index<dataSet.get(0).getAttrNo();index++) {
			int i = index;
			double max = dataSet.stream().map(instance->di.convert(instance.getAttributes()).get(i)).max(Double::compare).get();
			double min = dataSet.stream().map(instance->di.convert(instance.getAttributes()).get(i)).min(Double::compare).get();
			double diff = max-min;
		result.add(diff);	
		}
		return result;	
	}
}
