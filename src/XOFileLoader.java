
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class XOFileLoader extends DataLoader {
	public List<Image> load(String path)
	{
		try
		{
			File file = new File(path);
			Scanner scanner = new Scanner(file);
			List<Image> result = new ArrayList<Image>();
			
			String line = scanner.nextLine();
			while(scanner.hasNextLine()) {
				
				
			
				line = scanner.nextLine();	//second line
				String label = line.substring(1);	//provide flexibility, not necessarily X or O
				line = scanner.nextLine();	//third line

				int pos=0;
				
				boolean[][] pixel = new boolean[10][10];
		
				while (true)
				{
					line = scanner.nextLine(); 
					if(line.indexOf("P1")!=-1 ||scanner.hasNextLine()==false)
					{
						break;
					}
					List<Integer> list = line.chars().map(x -> x - '0').mapToObj(Integer::valueOf).collect(Collectors.toList());
					//System.out.println(list.toString());
					for(Integer o:list)
					{	
						int row = pos%10;
						int col = pos/10;
						pixel[row][col] = o==1? true:false;
						pos++;
					}
				}
				//System.out.println(pixelMap.toString());
				//System.out.println(label);
				result.add(new Image(pixel,10,10,label));

				
			}
			scanner.close();
			return result;
			
		}catch(FileNotFoundException e)
		{
			System.out.println("The file is not found, please check again");
		}
		return null;
	}
}
