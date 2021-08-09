import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KNearestNeighbourClassifier implements RecordClassifier {
	protected FindRanger findRanger;
	protected List<Double> rangeArr;
	protected List<Record> trainingSet;
	protected DataInfo<?> di;
	protected int k;
	protected DistanceCalculator cal;

	
	public KNearestNeighbourClassifier(FindRanger fr,DataInfo<?> di,int k,DistanceCalculator cal) {
		super();
		this.findRanger = fr;
		this.di = di;
		this.k = k;
		this.cal = cal;
	}

	public void train(List<Record> trainingSet)
	{
		this.trainingSet= trainingSet;
		rangeArr = findRanger.findRange(trainingSet);
	}
		
	public Map<Record,String> classify(List<Record>testingSet)
	{
		Map<Record,String> result = new HashMap<Record,String>();
		
		Iterator<Record> itTesting = testingSet.iterator();
		while(itTesting.hasNext()) {
			Record test = itTesting.next();
			Iterator<Record> itTraining = trainingSet.iterator(); 
			distanceTable dt = new distanceTable(k);
			while(itTraining.hasNext()) 
			{
				Record train = itTraining.next();
				dt.insert(train.getAttributes().get(di.getLabelNo()).toString(), cal.calculate(test,train,rangeArr));	
				//dt.insert(train.getAttributes().get(WineEnum.Class.getValue()).toString(), cal.calculate(test,train));					
			}
			//System.out.println(dt.count());
			result.put(test,dt.count());
		}
		return result;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public DistanceCalculator getCal() {
		return cal;
	}

	public void setCal(DistanceCalculator cal) {
		this.cal = cal;
	}
	
}
