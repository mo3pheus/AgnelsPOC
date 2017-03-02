package ml.poc.agnels.edu.driver;

import java.io.IOException;
import java.util.Map;

import ml.poc.agnels.edu.domain.ClassificationEngine;
import ml.poc.agnels.edu.domain.Clusterer;
import ml.poc.agnels.edu.domain.Data;
import ml.poc.agnels.edu.domain.DataModel;
import ml.poc.agnels.edu.domain.Clusterer.ClusteredPoints;

public class IrisDriver {

	public static void main(String[] args) {
		SanketML irisProblem = new SanketML();
		try {
			irisProblem.loadData("iris.data.txt", ",", 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		irisProblem.populateTrainTestSets(80);

		System.out.println(" Number of training samples = " + irisProblem.getTrainingData().size());
		System.out.println(" Number of testing samples = " + irisProblem.getTestingData().size());

		/*
		 * This part should be done by the students. Implement your own version
		 * of ClassificationEngine and compare accuracy you get.
		 * 
		 * 1. Instantiate the classificationEngine,
		 * 2. Report accuracy.
		 * 
		 */
		ClassificationEngine classificationEngine = new ClassificationEngine();
		classificationEngine.buildModels(irisProblem.getTrainingData(), 4);

		for (DataModel model : classificationEngine.getModles()) {
			System.out.println(model.toString());
		}
		irisProblem.setClassificationEngine(classificationEngine);

		System.out.println("Accuracy Percentage = " + irisProblem.getAccuracy() + " %");

		/*
		 * Implements k-means clustering algorithm
		 */
		int numClusters = 3;
		Clusterer clusterer = new Clusterer();
		try {
			Map<Data, ClusteredPoints> result = clusterer.clusterData(irisProblem.getTrainingData(), numClusters);
			irisProblem.setClusterer(clusterer);

			for (Data centroid : result.keySet()) {
				ClusteredPoints points = result.get(centroid);
				System.out.println(" Centroid = " + centroid.toString() + " memberSize = " + points.getPoints().size());
			}

			System.out.println(
					" For number of clusters = " + numClusters + " Cost = " + irisProblem.getCostFunction(result));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in clustering data!");
		}
	}
}

/*
 * public static void main1(String[] args) { DataOperations bloodProblem =
 * new DataOperations();
 * 
 * // Recency (months),Frequency (times),Monetary (c.c. blood),Time //
 * (months),"whether he/she donated blood in March 2007"
 * 
 * bloodProblem.loadArrayData("transfusion.data.txt", 4);
 * bloodProblem.populateTrainTestSets(92);
 * 
 * System.out.println(" Number of training samples = " +
 * bloodProblem.getTrainingData().size());
 * System.out.println(" Number of testing samples = " +
 * bloodProblem.getTestingData().size());
 * 
 * bloodProblem.buildGaussianModel();
 * System.out.println(bloodProblem.getGd().getModels().size());
 * System.out.println(bloodProblem.getAccuracy()); }
 * 
 * public static void main3(String[] args) { DataOperations glassProblem =
 * new DataOperations();
 * 
 * // Recency (months),Frequency (times),Monetary (c.c. blood),Time //
 * (months),"whether he/she donated blood in March 2007"
 * 
 * glassProblem.loadArrayData("glass.data.csv", 9);
 * glassProblem.populateTrainTestSets(90);
 * 
 * System.out.println(" Number of training samples = " +
 * glassProblem.getTrainingData().size());
 * System.out.println(" Number of testing samples = " +
 * glassProblem.getTestingData().size());
 * 
 * glassProblem.buildGaussianModel();
 * System.out.println(glassProblem.getGd().getModels().size());
 * System.out.println(glassProblem.getAccuracy()); }
 */
