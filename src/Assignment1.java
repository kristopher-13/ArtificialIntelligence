import java.util.List;
import java.util.Map;


/*
 * This program is to classify the data using K-nearest neighbor algorithm, decision tree algorithm and perceptron
 * 
 * 
 * @author  Kristopher Tam
 * @version 0.1
 * @since   2021-03-11
 *  
 * */
public class Assignment1 {
	public static void Q1(String[] args)
	{
		/*'Select' suitable modules*/
		long startTime = System.currentTimeMillis();
		
		Converter<Double> dc = new DoubleConverter();
		DataInfo<Double> WineDataInfo = new DataInfo<Double>(13,
															  new String[] {"Alcohol","Malic_acid","Ash","Alcalinity_of_ash", "Magnesium","Total_phenols","Flavanoids","Nonflavanoid_phenols","Proanthocyanins","Color_intensity","Hue","OD280OD315_of_diluted_wines","Proline","Class"},
															  new Class[] {Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,Double.class,String.class},
															  dc);
		System.out.println(WineDataInfo.getNoOfCol());
		DataValidator dv = new ColsValidator(WineDataInfo);
		CSVLoader dl = new CSVLoader(dv,WineDataInfo);
		PerformanceMeasurer pm = new AccuracyMeasurer(WineDataInfo);
		FindRanger fr = new FindRanger(WineDataInfo);
		DistanceCalculator cal = new EuclideanDistance(WineDataInfo);
		KNearestNeighbourClassifier KNN = new KNearestNeighbourClassifier(fr,WineDataInfo,2,cal);
		//KNearestNeighbourClassifier KNN = new KNearestNeighbourClassifier(fr,WineDataInfo,Integer.parseInt(args[2]),cal);
		
		/*For easy testing*/
		List<Record> trainingSet = dl.load("C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part1/wine-training");
		List<Record> testingSet = dl.load("C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part1/wine-test");
		//List<Record> trainingSet = dl.load(args[0]);
		//List<Record> testingSet = dl.load(args[1]);
		
		KNN.train(trainingSet);
		
		/*For easy testing*/
		System.out.println(pm.measure(testingSet, KNN.classify(testingSet)));
		//System.out.println(pm.measure(testingSet, KNN.classify(Integer.parseInt(args[2]), testingSet, cal)));
		
		long finishTime = System.currentTimeMillis();
		System.out.println("That took: " + (finishTime - startTime) + " ms");
	}
	public static void Q2(String[] args)
	{
		
		Converter<String> sc = new StringConverter();
		DataInfo<String> HepatitsDataInfo = new DataInfo<String>(0,
												  new String[] {"Class","AGE","FEMALE","STEROID","ANTIVIRALS","FATIGUE","MALAISE","ANOREXIA","BIGLIVER","FIRMLIVER","SPLEENPALPABLE","SPIDERS","ASCITES","VARICES","BILIRUBIN","SGOT","HISTOLOGY"},
												  new Class[] {String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class},
												  sc
												  );
		DataValidator dv = new ColsValidator(HepatitsDataInfo);
		CSVLoader dl = new CSVLoader(dv,HepatitsDataInfo);
		PerformanceMeasurer pm = new AccuracyMeasurer(HepatitsDataInfo);
		ImpurityCalculator ic = new GiniImpurityStrCalculator(HepatitsDataInfo);
		RecordClassifier model = new DecisionTreeClassifier(HepatitsDataInfo,ic);
		
		KFoldCrossValidation kfoldcv = new KFoldCrossValidation(10);
		//String trainingPath = args[0];
		//String testingPath = args[1];
		String trainingPath = "C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part2/hepatitis-training-run-";
		String testingPath = "C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part2/hepatitis-test-run-";
		kfoldcv.validate(model, dl, pm, HepatitsDataInfo, dv, trainingPath, testingPath);
		
		/*
		 List<Instance> trainingSet = dl.load("C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part2/hepatitis-training");
		 
		List<Instance> testingSet = dl.load("C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part2/hepatitis-test");
		List<Instance> trainingSet  = dl.load(args[0]);
		List<Instance> testingSet = dl.load(args[1]);
		
		model.train(trainingSet);
		model.getRoot().report(" ");
		Map<Instance,String> resultMap = model.classify(testingSet);
		Map<Instance,String> baselineMap = model.baselineClassify(testingSet);
		
		System.out.println("Model accuarcy "+pm.measure(testingSet, resultMap));
		System.out.println("Baseline accuarcy "+pm.measure(testingSet, baselineMap));
		*/
		
	}
	public static void Q3(String[] args)
	{
		long startTime = System.currentTimeMillis();
		ImageInfo ii = new ImageInfo();
		XOFileLoader dl = new XOFileLoader();
		Perceptron p = new Perceptron(100,3);
		ImageAccuracy ia = new ImageAccuracy();
		
		String trainingPath = "C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part3/image.data";
		String testingPath = "C://Users/Kristopher/Documents/VUW/SEM3/Comp 307/ass1_data/ass1_data/part3/image.data";
		
		List<Image> trainingSet = dl.load(trainingPath);
		List<Image> testingSet = dl.load(testingPath);
		//List<Image> trainingSet = dl.load(args[0]);
		//List<Image> testingSet = dl.load(args[1]);
			
		p.train(trainingSet);
		Map<Image,String> result = p.classify(testingSet);
		System.out.println("accuracy" + ia.measure( testingSet, result));
		long finishTime = System.currentTimeMillis();
		System.out.println("That took: " + (finishTime - startTime) + " ms");
	}
	public static void main(String[] args)
	{	
		//Please comment out 2of the below function!!!!!!!
		//Q1(args);
		//Q2(args);
		Q3(args);
		
	}
}
