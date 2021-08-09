
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
 * A data loader for loading csv file into a set of instance
 * */
public class CSVLoader extends DataLoader {
	DataValidator dv;
	DataInfo df;
	
	public CSVLoader(DataValidator dv, DataInfo df) {
		this.dv = dv;
		this.df = df;
	}
	
	/*
	 * Load the data
	 * @params path the file path
	 * @return the dataset
	 * */
	public List<Record> load(String path)
	{
		try
		{
			File file = new File(path);
			Scanner scanner = new Scanner(file);
			List<Record> result = new ArrayList<Record>();
			
			scanner.nextLine(); //Skip header
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] record = line.split(" ");
				List<Object> tempList = new ArrayList<Object>();
				Arrays.stream(record).forEach(s->tempList.add(s));
				Record temp = new Record(tempList,this.df); //convert string to object
				dv.validate(temp);	//check if it is the wine data
				result.add(temp);
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
