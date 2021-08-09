import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prediction {
	
	public static List<Integer> predict(int fsize)
	{	
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<fsize;i++) {
			Random r = new Random();
			if(r.nextBoolean())
			{
				result.add(Integer.valueOf(1));
			}else
			{
				result.add(Integer.valueOf(0));
			}
		}
		
		return result;
	}
}
