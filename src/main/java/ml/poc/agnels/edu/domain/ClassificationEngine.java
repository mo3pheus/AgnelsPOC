package ml.poc.agnels.edu.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassificationEngine implements Classifier {

	private ArrayList<DataModel> models = null;

	/**
	 * This function builds the gaussian models based on training data. Note:
	 * This assumes that all samples in the trainingData belong to the same
	 * class.
	 * 
	 * @param trainingData
	 *            - List of type Data - list of training samples.
	 * @param numberOfFields
	 *            - feature cardinality.
	 * 
	 */
	public void buildModels(ArrayList<Data> trainingData, int numberOfFields) {
		models = new ArrayList<DataModel>();
		Map<String, Integer> classMap = getClassMap(trainingData);
		Object[] classIds = classMap.keySet().toArray();

		for (int i = 0; i < classIds.length; i++) {
			ArrayList<Data> temp = new ArrayList<Data>();
			for (int j = 0; j < trainingData.size(); j++) {
				if ((trainingData.get(j) != null) && (trainingData.get(j).getClassId() != null)
						&& (trainingData.get(j).getClassId().equals((String) classIds[i]))) {
					temp.add(trainingData.get(j));
				}
			}

			models.add(new DataModel(temp, numberOfFields));
		}
	}

	/**
	 * @return This function returns the built models.
	 * 
	 *         Note:: If buildModels() has not been called, this will return
	 *         null.
	 */
	public ArrayList<DataModel> getModles() {
		return models;
	}

	/**
	 * This function classifies the given sample into one of the trained
	 * classes. It also returns a confidence measure on the prediction.
	 * 
	 */
	public Result classify(Data sample) {
		Result id = new Result();
		double maxProb = Double.MIN_VALUE;
		String classId = "";

		for (int i = 0; i < models.size(); i++) {
			double dist = ClassificationEngine.getDistance(sample, models.get(i));

			if (dist > maxProb) {
				maxProb = dist;
				classId = models.get(i).getClassId();
			}
		}

		id.setClassId(classId);
		id.setConfidence(maxProb);

		return id;
	}

	/**
	 * 
	 * This function computes the Euclidean distance between two samples.
	 * 
	 * @param sample
	 * @param model
	 * @return double - distanc measure
	 */
	public static double getDistance(Data sample, DataModel model) {
		double distance = -1.0d;

		for (int i = 0; i < sample.getFields().length; i++) {
			distance += (((sample.getFields()[i] - model.getMean().getFields()[i]) / model.getStdDev().getFields()[i])
					* ((sample.getFields()[i] - model.getMean().getFields()[i]) / model.getStdDev().getFields()[i]));
		}
		distance = Math.sqrt(distance);

		return Math.pow(Math.E, (-1.0d * distance));
	}

	/**
	 * Returns a classMap of the classId and classNumber
	 * 
	 * @param data
	 * @return
	 */
	private Map<String, Integer> getClassMap(ArrayList<Data> data) {
		Map<String, Integer> classMap = new HashMap<String, Integer>();
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getClassId() != null) {
				classMap.put(data.get(i).getClassId(), 1);
			}
		}

		return classMap;
	}
}
