import java.util.List;

public interface Converter<T> {
	public List<T> convert(List<Object> list);
	public T convert(Object obj);
}
