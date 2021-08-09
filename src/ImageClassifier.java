import java.util.List;
import java.util.Map;

public interface ImageClassifier extends Classifier {
	public void train(List<Image> trainingSet);
	public Map<Image,String> classify(List<Image> testingSet);
}
