import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Perceptron implements ImageClassifier {
	List<Feature> featureList = new ArrayList<Feature>();
	ImageInfo imageInfo = new ImageInfo();
	FeatureValueCalculator fvc = new SimpleFeatureValueCalculator();
	float[] weightArr;
	int limit = 200;
	int n;
	public Perceptron(int n,int k)
	{
		this.n=n;
		weightArr= new float[n];
		Random r = new Random();
		for(int i=0;i<n;i++)
		{
			featureList.add(new Feature(k));
			weightArr[i] = r.nextFloat() * (r.nextBoolean()?1:-1);
			
		}
		//System.out.println(weightArr.toString());
	}
	public void train(List<Image> imageList)
	{	
		//System.out.println(weightArr.toString());
		
		boolean allCorrect = true;
		int counter = 0;
		while(true)
		{
			
			allCorrect = true;
			for(int i=0;i<imageList.size();i++)
			{	
				//System.out.println(i+"1 "+Arrays.toString(weightArr));
		
				float[] featureArr = new float[n];
				double result = 1;	//dummy
				for(int j=0; j<featureList.size();j++)
				{

					featureArr[j] = fvc.calculateValue(featureList.get(j), imageList.get(i), imageInfo.threshold)  ;
					result = result+ (featureArr[j]* weightArr[j]);
				}
				//System.out.println(i+"2 "+Arrays.toString(weightArr));
				
				if(((result>=0) && (imageList.get(i).getLabel().compareTo("X") ==0)))
				{	
	
					allCorrect = false;
					for(int k=0;k<featureList.size();k++)
					{
						weightArr[k] = weightArr[k] - featureArr[k];

					}
				}else if((result<0 && imageList.get(i).getLabel().compareTo("O") ==0))
				{
					allCorrect = false;
					for(int k=0;k<featureList.size();k++)
					{
						weightArr[k] = weightArr[k] + featureArr[k];

					}
				}
				//System.out.println(i+"3 "+Arrays.toString(weightArr));
			}
			if(allCorrect==true||counter >= limit)
			{
				break;
			}
			counter++;
		
		}
		System.out.println("weight " + Arrays.toString(weightArr));
	}
	public Map<Image,String> classify(List<Image> testingSet)
	{
		Map<Image,String> result = new HashMap<Image,String>();
		for(Image i:testingSet)
		{
			double score =0;
			for(int j=0; j<featureList.size();j++)
			{
				score = score + fvc.calculateValue(featureList.get(j), i, imageInfo.threshold) * weightArr[j];
			}
			//System.out.println(score);
			if (score>=0)
			{
				result.put(i, "O");
			}
			else
			{
				result.put(i, "X");
			}
				
		}
		return result;
	}
	
}
