/*import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;*/

public class KMeanClustering {
	/*private FindRanger findRanger;
	private List<Double> rangeArr;
	private List<Instance> trainingSet;
	
	public KMeanClustering(FindRanger fr) {
		super();
		this.findRanger = fr;	
	}

	public void train(List<Instance> trainingSet)
	{
		this.trainingSet= trainingSet;
		rangeArr = findRanger.findRange(trainingSet);
	}
	
	public List<List<Instance>> cluster(int k, List<Instance>testingSet, DistanceCalculator cal)
	{
		Map<Instance,String> result = new HashMap<Instance,String>();
		List<List<Instance>> Original = new ArrayList();
		Iterator<Instance> itTesting = testingSet.iterator();
		while(itTesting.hasNext()) {
			Instance test = itTesting.next();
			Iterator<Instance> itTraining = trainingSet.iterator(); 
			distanceTable dt = new distanceTable(k);
			while(itTraining.hasNext()) 
			{
				Instance train = itTraining.next();
				dt.insert(train.getAttributes().get(WineEnum.Class.getValue()).toString(), cal.calculate(test,train,rangeArr));	
				//dt.insert(train.getAttributes().get(WineEnum.Class.getValue()).toString(), cal.calculate(test,train));					
			}
			//System.out.println(dt.count());
			result.put(test,dt.count());
		}
		return result;
	}*/
}
