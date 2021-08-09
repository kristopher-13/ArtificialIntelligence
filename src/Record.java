import java.util.List;


public class Record implements Instance{
	protected List<Object> attributes;
	protected DataInfo df;
	
	public Record(List<Object> attributes, DataInfo df) {
		super();
		this.attributes = attributes;
		this.df = df;
	}

	public List<Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}
	
	public Object getObject(int i)
	{
		return this.attributes.get(i);
	}
	
	public int getAttrNo()
	{
		return this.attributes.size();
	}
	
	public String getLabel()
	{
		return (String)this.attributes.get(df.labelNo);
	}
	
	
}
