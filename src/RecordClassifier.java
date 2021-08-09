import java.util.List;
import java.util.Map;

public interface RecordClassifier extends Classifier {
	public void train(List<Record> trainingSet);
	public Map<Record,String> classify(List<Record> testingSet);
}
