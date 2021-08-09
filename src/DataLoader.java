
import java.util.List;

/*
 * To load the data from the data source
 * */
public abstract class DataLoader {
	DataValidator dv;
	
	public abstract List<? extends Instance> load(String path);
}
