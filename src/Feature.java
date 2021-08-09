import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Feature {
	protected int[] row;
	protected int[] col;
	protected boolean[] prediction;
	protected int k;
	public Feature(int k) {
		super();
		this.k = k;
		init(k);
	}
	
	private void init(int k)
	{
		row = new int[k];
		col = new int[k];
		prediction = new boolean[k];
		Random r = new Random();
		for(int i=0; i<k;i++)
		{
			row[i] = r.nextInt(10);
			col[i] = r.nextInt(10);
			prediction[i] = r.nextBoolean();
		}
	}
		
	
	public int getK() {
		return k;
	}
	public int getRow(int k)
	{
		return row[k];
	}
	public int getCol(int k)
	{
		return col[k];
	}
	public boolean getValue(int k)
	{
		return prediction[k];
	}

}
