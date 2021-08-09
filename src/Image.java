import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Image implements Instance {
	boolean[][] pixel = new boolean[10][10];
	int Xsize;
	int Ysize;
	String label;
	public Image(boolean[][] pixel,int Xsize,int Ysize, String label) {
		super();
		this.pixel = pixel;
		this.Xsize = Xsize;
		this.Ysize = Ysize;
		this.label = label;
	}
	
	public boolean getValue(int x, int y)
	{
		return pixel[x][y];
	}
	public String getLabel()
	{
		return this.label;
	}
	public List<Object> getAttributes()
	{
		return Arrays.asList(pixel);
	}
}
