package ml.poc.agnels.edu.domain;

import java.util.List;
import java.util.Map;

import ml.poc.agnels.edu.domain.Clusterer.ClusteredPoints;

public interface IClusterStuff {
	public Map<Data,ClusteredPoints> clusterData(List<Data> data, int numClusters) throws Exception;
}
