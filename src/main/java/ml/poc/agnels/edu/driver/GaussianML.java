package ml.poc.agnels.edu.driver;

import ml.poc.agnels.edu.domain.ClassificationEngine;
import ml.poc.agnels.edu.domain.ClusteringEngine;

public class GaussianML extends MachineLearningOperations<ClassificationEngine>{

	@Override
	public ClassificationEngine getClassificationEngine() {
		return classificationEngine;
	}

	@Override
	public void setClassificationEngine(ClassificationEngine classificationEngine) {
		this.classificationEngine = classificationEngine;
	}

	@Override
	public ClusteringEngine getClusterer() {
		return clusteringEngine;
	}

	@Override
	public void setClusterer(ClusteringEngine clusteringEngine) {
		this.clusteringEngine = clusteringEngine;
	}
}
