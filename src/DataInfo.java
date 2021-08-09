import java.util.List;

/*
 * To convert the raw data to the type that required by other module
 * Also store all the information of a file as Java cannot interpret the 
 * */
public class DataInfo <T>{
	protected int labelNo;
	protected String[] attrName;
	protected Class[] dataType;
	protected Converter<T> converter;
	
	public DataInfo(int labelNo, String[] attrName, Class[] dataType, Converter<T> converter) {
		super();
		this.labelNo = labelNo;
		this.attrName = attrName;
		this.dataType = dataType;
		this.converter = converter;
	}
	/*
	 * The logic to turn the attribute into number for calculating data.
	 * Categorical data can be converted into number by implementing this method
	 * 
	 * @param list To convert the attributes list of a instance into any datatype needed.
	 * @return a set of double
	 * */
	public List<T> convert(List<Object> list)
	{
		return this.converter.convert(list);
	}
	public T convert(Object obj)
	{
		return this.converter.convert(obj);
	}
	
	public int getLabelNo() {
		return labelNo;
	}

	public String[] getAttrName() {
		return attrName;
	}

	public int getNoOfCol()
	{
		return attrName.length;
	}

		
}
