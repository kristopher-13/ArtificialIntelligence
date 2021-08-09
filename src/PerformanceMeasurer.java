import java.util.List;
import java.util.Map;

public interface PerformanceMeasurer {
	public double measure(List<? extends Instance> test, Map<? extends Instance,String> result);
}
