import java.util.List;
import java.util.Map;

public class KFoldCrossValidation {
	protected int k;
	
	
	public KFoldCrossValidation(int k) {
		super();
		this.k = k;
	}

	
	public int getK() {
		return k;
	}


	public void setK(int k) {
		this.k = k;
	}


	public double validate(RecordClassifier classifier,CSVLoader dl, PerformanceMeasurer pm,DataInfo<?> di,DataValidator dv, String trainingPath,String testingPath)
	{
		double measure = 0;
		for(int i=0;i<this.k;i++)
		{
			System.out.println("Fold " +i+" :");
			List<Record> trainingSet = dl.load(trainingPath + i);
			List<Record> testingSet =  dl.load(testingPath + i);
			
			classifier.train(trainingSet);
			Map<Record,String> resultMap = classifier.classify(testingSet);
			double result = pm.measure(testingSet, resultMap);
			System.out.println("Performance: " + result);
			measure += result;
		}
		measure /= k;
		System.out.println("Average performance: " +measure);
		return measure;
	}
	public double validate(Classifier classifier, DataInfo<?> di,DataValidator dv, List<List<Record>> trainingSets,List<List<Record>> testingPath)
	{
		return 0;
	}
	
}
