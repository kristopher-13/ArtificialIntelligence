
public class SimpleFeatureValueCalculator implements FeatureValueCalculator {
	public int calculateValue(Feature f, Image image,int threshold)
	{
		int sum=0;
		for(int k=0;k<f.getK();k++)
		{
			//System.out.println("i " + i);
			//System.out.println("c " + f.getCoor(i));
			//System.out.println("a " + image.getValue(f.getCoor(i)));
		//System.out.println("b " + f.getValue(i));
			if (image.getValue(f.getRow(k),f.getCol(k))==f.getValue(k))
			{
				sum++;
			}
			
			
			//System.out.println("I" + i);
			//System.out.println("coor" + f.getCoor(i).toString());
			//System.out.println("image" + image.getValue(f.getCoor(i)));
			//System.out.println("f " + f.getValue(i));
				
			
		}		
		//System.out.println("sum " + sum);
		//System.out.println("threshold " + threshold);
		if (sum>=threshold)
		{
			return 1;
		}else
		{
			return 0;
		}
	}
}
