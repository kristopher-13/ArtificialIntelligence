
import java.util.List;
import java.util.TreeMap;

public class GiniImpurityStrCalculator extends ImpurityCalculator {
	
	public GiniImpurityStrCalculator(DataInfo<String> di) {
		super(di);
	}
	public double calculate(List<Record> dataSet, int index)
	{
		TreeMap<Object,TreeMap<String,Long>> currentImpurityMap = getDistributionMap(dataSet,index);
		//System.out.println(currentImpurityMap.toString());
		double dataTotal = dataSet.size();
		if(index==di.getLabelNo())
		{
			double giniImpurity = 1;
			for(TreeMap<String,Long> category:currentImpurityMap.values())
			{
				giniImpurity -= Math.pow((double)(category.firstEntry().getValue()/dataTotal),2);
			}
			
			return giniImpurity;
		}else {
		//System.out.println("i " + index);
		
		//Form a map to count the appearance of each value and its group
			
			double giniImpurity = 0;
			for(TreeMap<String,Long> category:currentImpurityMap.values())
			{
				double currentImpurity = 1;
				double categoryTotal = category.values().stream().reduce((a,b)->a+b).get();	//calculate the total number of instance belongs to this category
				for(double value:category.values())
				{
					//System.out.println("v " + value);
					//System.out.println("t " + categoryTotal);
					currentImpurity -= Math.pow((double)(value/categoryTotal),2);	//gini Impurity of this label
					//System.out.println("c " + currentImpurity);
				}
				giniImpurity += (currentImpurity*categoryTotal)/dataTotal; //weighted average
				//System.out.println("g " + giniImpurity);
			}
			
			return giniImpurity;
		}
	}
	public TreeMap<Double,Integer> calculate(List<Record> dataSet,List<Integer> indexList)
	{
		
		TreeMap<Double,Integer> attributeImpurity = new TreeMap<Double,Integer>();
		
		for(int i:indexList) //for each attribute	
		{
			attributeImpurity.put(calculate(dataSet,i),i);
		}
		return attributeImpurity;
	}
	
	
	
}
