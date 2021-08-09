
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DecisionTreeClassifier implements RecordClassifier {
	DataInfo<String> di;
	ImpurityCalculator ic;
	DecisionTreeNode root;
	
	public DecisionTreeClassifier(DataInfo<String> di,ImpurityCalculator ic) {
		super();
		this.di = di;
		this.ic = ic;
	}

	public void train(List<Record> trainingSet) {
		List<Integer> attr = new ArrayList<Integer>();
		for(int i =0;i<trainingSet.get(0).getAttrNo();i++)
		{
			if(i!=di.labelNo)
			{
				attr.add(i);
			}
		}
		this.root = buildTree(null,trainingSet,attr);
	}
	public DecisionTreeNode buildTree(DecisionTreeNode parent,List<Record> trainingSet, List<Integer> attr) 
	{
		
		if(isPure(trainingSet))
		{
			return new DecisionTreeNode(parent,di.convert(trainingSet.get(0).getObject((di.getLabelNo()))));
		}else if(attr.size()==0)
		{
			return new DecisionTreeNode(parent,mostProbableLabel(trainingSet));
		}else {
			
			int index = ic.calculate(trainingSet, attr).firstEntry().getValue();
	
			double categoryImpurity = ic.calculate(trainingSet, index);
			double classImpurity = ic.calculate(trainingSet, di.getLabelNo());
			
			//System.out.println("index " + index);
			//System.out.println(categoryImpurity);
			//System.out.println(classImpurity);
			
			if(categoryImpurity>classImpurity)
			{
				return new DecisionTreeNode(parent,mostProbableLabel(trainingSet));
			}else {
				attr.remove((Object)index);
				Map<Object,List<Record>> instanceMap = trainingSet.stream().collect(Collectors.groupingBy(p -> p.getObject(index)));
				if (instanceMap.keySet().size()==1)	//only one option left
				{
					return new DecisionTreeNode(parent,mostProbableLabel(trainingSet));
				}else
				{
					//System.out.println("map " + instanceMap.toString());
					Map<Object,DecisionTreeNode> childMap = new HashMap<Object,DecisionTreeNode>();
					DecisionTreeNode current = new DecisionTreeNode(parent,index,childMap);
					
					for(Entry<Object, List<Record>> e:instanceMap.entrySet())
					{
						childMap.put(di.convert(e.getKey()), buildTree(current,e.getValue(),attr));
					}
					//System.out.println(childMap.toString());
					current.setChildMap(childMap);
					return current;
				}
			}
		}
	}
	
	public DecisionTreeNode getRoot() {
		return root;
	}
	public void setRoot(DecisionTreeNode root) {
		this.root = root;
	}
	public Map<Record,String> classify(List<Record> testingSet)
	{
		Map<Record,String> resultMap = new HashMap<Record,String>();
		for(Record i:testingSet)
		{	
			DecisionTreeNode current = this.root;
			while(current.isLeaf()==false)
			{
				//current.report(" ");
				current = current.next(i);
			}
			resultMap.put(i, current.getLabel());
		}
		return resultMap;
	}
	public Map<Record,String> baselineClassify(List<Record> testingSet)
	{
		Map<Record,String> resultMap = new HashMap<Record,String>();
		String result = mostProbableLabel(testingSet);
		for(Record i:testingSet)
		{
			
			resultMap.put(i, result );
		}
		return resultMap;
	}
	private boolean isPure(List<Record> trainingSet)
	{
		HashSet<String> set = new HashSet<String>();
		for(Record i:trainingSet)
		{
			set.add(di.convert(i.getObject(di.getLabelNo()))) ;
			if (set.size()>1)
			{
				return false;
			}
		}
		return true;
	}	
	private String mostProbableLabel(List<Record> trainingSet)
	{
		TreeMap<String,Long> map = new TreeMap<String,Long>();
		for(Record i:trainingSet)
		{
			String label = di.convert(i.getObject(di.getLabelNo()));
			long count = map.getOrDefault(label, (long)0);
			count++;
			map.put(label, count);
		}
		return map.lastKey();
	}
}
