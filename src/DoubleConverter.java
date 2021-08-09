import java.util.ArrayList;
import java.util.List;

public class DoubleConverter implements Converter<Double> {
	
	public List<Double> convert(List<Object> list){
		List<Double> result = new ArrayList<Double>();
		list.forEach(obj->result.add(Double.parseDouble(obj.toString())));
		return result;
	}
	public Double convert(Object obj) {
		return Double.parseDouble(obj.toString());
	}
	
}
