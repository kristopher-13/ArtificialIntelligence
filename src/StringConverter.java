import java.util.List;
import java.util.ArrayList;

public class StringConverter implements Converter<String> {
	public List<String> convert(List<Object> list){
		List<String> result = new ArrayList<String>();
		list.forEach(obj->result.add(obj.toString()));
		return result;
	}
	public String convert(Object obj) {
		return obj.toString();
	}
}
