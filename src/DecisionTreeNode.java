import java.util.Map;
import java.util.Map.Entry;

public class DecisionTreeNode {
	protected DecisionTreeNode parent = null;
	protected String label = "";
	protected int index = -1;
	protected Map<Object,DecisionTreeNode> childMap = null;
	
	//root
	public DecisionTreeNode(int index, Map<Object, DecisionTreeNode> childMap) {
		super();
		this.index = index;
		this.childMap = childMap;
	}
	//child
	public DecisionTreeNode(DecisionTreeNode parent, int index, Map<Object, DecisionTreeNode> childMap) {
		super();
		this.parent = parent;
		this.index = index;
		this.childMap = childMap;
	}
	
	public DecisionTreeNode(DecisionTreeNode parent, String label) {	//child leaf
		super();
		this.parent = parent;
		this.label = label;
	}
	
	public DecisionTreeNode( String label) {	//root and leaf
		super();
		this.label = label;
	}

	public DecisionTreeNode next(Record i) {
		return childMap.getOrDefault(i.getObject(index), null);
	}
	
	public boolean isLeaf()
	{
		return childMap == null;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Map<Object, DecisionTreeNode> getChildMap() {
		return childMap;
	}
	public void setChildMap(Map<Object, DecisionTreeNode> childMap) {
		this.childMap = childMap;
	}
	public boolean isRoot()
	{
		return parent == null;
	}

	public DecisionTreeNode getParent() {
		return parent;
	}

	public void setParent(DecisionTreeNode parent) {
		this.parent = parent;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	public void report(String indent){
		if (!isLeaf())
		{
			for(Entry<Object,DecisionTreeNode> e:childMap.entrySet())
			{
				System.out.printf("%s%s = %s:%n", indent, index, e.getKey().toString());
				e.getValue().report(indent+"\t");
			}	
		}else {
			System.out.printf("%s Class = %s:%n", indent, label);
		}
		
	}


	
	
	
}
