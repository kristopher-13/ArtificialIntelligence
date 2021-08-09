import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class distanceTable {
	private int k;
	
	private TreeMap<Double,String> map = new TreeMap<Double,String>();
	public distanceTable(int k)
	{
		this.k = k;	
	}
	
	public void insert(String s,double d)
	{
		
		//System.out.println("1" + map.toString());
		map.put(d,s);
		//System.out.println("2" + map.toString());
		if(map.size()>k)
		{
			Entry<Double,String> lastEntry = map.lastEntry();
			map.entrySet().removeIf(entry->entry.getKey()==lastEntry.getKey());
		}	
	}
	public String count() {

		TreeMap<String, Long> countMap = map.
								   values().
								   stream().
								   collect(Collectors.groupingBy(e->e, TreeMap::new, Collectors.counting()));
		//System.out.println("a" + countMap.toString());

		Map<String,Long> filterMap = countMap.entrySet().stream().filter(entry->entry.getValue()==countMap.values().stream().max(Long::compare).get()).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
		//System.out.println("a2" + filterMap.toString());
		if(filterMap.size()==1)
		{
			return countMap.lastKey();
		}else
		{
			TreeMap<String,Double> sumDistanceMap = map.entrySet()
	    		    .stream()
	    		    .collect(Collectors.toMap(
	    		         Entry::getValue,                        
	    		         entry -> entry.getKey(),
	    		         (existing,replacement)->existing+replacement
	    		         , TreeMap::new));
			
			Map<String,Double> filterDMap = sumDistanceMap.entrySet()
																.stream()
																.filter(entry->countMap.containsKey(entry.getKey())==true)
																.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
			//System.out.println("b" + filterDMap.toString());
			TreeMap<String,Double> filterD2Map = filterDMap.entrySet().stream().filter(entry->entry.getValue()==sumDistanceMap.values().stream().min(Double::compare).get()).collect(Collectors.toMap(e->e.getKey(),e->e.getValue(),(a,b)->a,TreeMap::new));
			//System.out.println("b2" + filterD2Map.toString());
	    	return filterD2Map.firstKey(); 
		}
	}
		
	
}
