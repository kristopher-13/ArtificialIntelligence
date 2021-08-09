import java.util.ArrayList;
import java.util.List;

public class ImageInfo {
	int dimension;
	int k;
	List<Integer> max;
	int threshold;
	public ImageInfo()
	{
		this.dimension=2;
		this.k=3;
		max = new ArrayList<Integer>();
		max.add(10);
		max.add(10);
		this.threshold = 2;
	}
}
