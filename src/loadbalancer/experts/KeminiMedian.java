package loadbalancer.experts;

import java.util.List;
import java.util.Map;

import loadbalancer.BalancingException;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class KeminiMedian<T extends Comparable<T>> {

	public static class ExpertOpinion<T extends Comparable<T>> {
		private List<T> range;

		public List<T> getRange() {
			return ImmutableList.copyOf(range);
		}

		public void setRange(List<T> range) {
			this.range = Preconditions.checkNotNull(range);
		}

		public static <T extends Comparable<T>> ExpertOpinion<T> createOpinion(
				List<T> range) {
			ExpertOpinion<T> expertOpinion = new ExpertOpinion<T>();
			expertOpinion.setRange(range);
			return expertOpinion;
		}

		public int getPosition(T object) {
			return range.indexOf(object);
		}
	}

	private List<ExpertOpinion<T>> opinions;
	private Map<ExpertOpinion<T>, BinariComporation<T>> comporationMap;

	public KeminiMedian(List<ExpertOpinion<T>> opinions) {
		checkSizeOfAllOpinionsIsSame(opinions);
		this.opinions = ImmutableList.copyOf(opinions);
		FluentIterable<BinariComporation<T>> comporations = FluentIterable
				.from(opinions).transform(
						new Function<ExpertOpinion<T>, BinariComporation<T>>() {

							@Override
							public BinariComporation<T> apply(
									ExpertOpinion<T> input) {
								return BinariComporation.create(input);
							}
						});

		comporationMap = Maps.newHashMap();
		for (BinariComporation<T> binariComporation : comporations) {
			comporationMap.put(binariComporation.getOpinion(),
					binariComporation);
		}
	}

	private void checkSizeOfAllOpinionsIsSame(List<ExpertOpinion<T>> opinions) {
		int baseSize = opinions.get(0).range.size();
		for (ExpertOpinion<T> expertOpinion : opinions) {
			if (expertOpinion.range.size() != baseSize) {
				throw new BalancingException(
						"Opnions have diffrent number of nodes");
			}
		}
	}

	public static <T extends Comparable<T>> KeminiMedian<T> createMedian(
			List<ExpertOpinion<T>> opinions) {
		return new KeminiMedian<T>(opinions);
	}

	public ExpertOpinion<T> electBest() {
		int opinionsCount = opinions.size();
		int[][] pareComporationMatrix = new int[opinionsCount][opinionsCount];

		for (int i = 0; i < opinions.size(); i++) {
			ExpertOpinion<T> baseOpinion = opinions.get(i);
			BinariComporation<T> baseOptionComporation = comporationMap
					.get(baseOpinion);
			for (int j = 0; j < opinions.size(); j++) {
				ExpertOpinion<T> comparedOpinion = opinions.get(j);
				BinariComporation<T> comparedOptionComporation = comporationMap
						.get(comparedOpinion);
				int distance = Math.abs(computeDistance(baseOptionComporation, comparedOptionComporation));
				pareComporationMatrix[i][j] = distance;
				pareComporationMatrix[j][i] = distance;
			}
		}
		
		int bestOptionIndex = -1;
		int minDisagreement = Integer.MAX_VALUE;

		for (int i = 0; i < pareComporationMatrix.length; i++) {
			int[] disagreement = pareComporationMatrix[i];
			int value = summ(disagreement);

			if (minDisagreement > value) {
				minDisagreement = value;
				bestOptionIndex = i;
			}
		}
		
		return opinions.get(bestOptionIndex);
	}

	private int computeDistance(BinariComporation<T> baseOptionComporation,
			BinariComporation<T> comparedOptionComporation) {
		int[][] baseMatrix = baseOptionComporation.getComporationMatrix();
		int[][] matrixToSubtract = comparedOptionComporation.getComporationMatrix();
		int[][] result = subtractMatrix(baseMatrix, matrixToSubtract);
		
		int summ = 0;
		
		for (int[] columns : result) {
			summ+=summ(columns);
		}
		return summ;
	}

	private int summ(int[] columns) {
		int summ2 = 0;
		for (int row : columns) {
			summ2 += Math.abs(row);
		}
		return summ2;
	}

	private int[][] subtractMatrix(int[][] baseMatrix, int[][] matrixToSubtract) {

		int[][] result = new int[baseMatrix.length][baseMatrix.length];

		for (int i = 0; i < baseMatrix.length; i++) {
			for (int j = 0; j < baseMatrix.length; j++) {
				result[i][j] = baseMatrix[i][j] - matrixToSubtract[i][j];
			}
		}
		return result;
	}
}
