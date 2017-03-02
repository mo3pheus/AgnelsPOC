package ml.poc.agnels.edu.driver;

import ml.poc.agnels.edu.domain.ClassificationEngine;
import ml.poc.agnels.edu.domain.Clusterer;

public class SanketML extends MachineLearningOperations<ClassificationEngine>{

	@Override
	public ClassificationEngine getClassificationEngine() {
		return classificationEngine;
	}

	@Override
	public void setClassificationEngine(ClassificationEngine classificationEngine) {
		this.classificationEngine = classificationEngine;
	}

	@Override
	public Clusterer getClusterer() {
		return clusteringEngine;
	}

	@Override
	public void setClusterer(Clusterer clusteringEngine) {
		this.clusteringEngine = clusteringEngine;
	}
}
