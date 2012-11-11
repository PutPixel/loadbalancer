package loadbalancer.experts;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;

import loadbalancer.experts.KeminiMedian.ExpertOpinion;

public class BinariComporation<T extends Comparable<T>> {

	private final ExpertOpinion<T> opinion;
	private final int[][] comporationMatrix;

	public BinariComporation(ExpertOpinion<T> opinion) {
		this.opinion = Preconditions.checkNotNull(opinion);
		List<T> range = opinion.getRange();
		int optionsCount = range.size();
		List<T> sortedOptions = Ordering.natural().sortedCopy(range);
		comporationMatrix = new int[optionsCount][optionsCount];
		for (int i = 0; i < optionsCount; i++) {
			T baseElement = sortedOptions.get(i);
			int basePosition = opinion.getPosition(baseElement);
			for (int j = 0; j < optionsCount; j++) {
				T comparedElement = sortedOptions.get(j);
				int comparedPosition = opinion.getPosition(comparedElement);
				if (basePosition <= comparedPosition) {
					comporationMatrix[i][j] = 1;
				} else {
					comporationMatrix[i][j] = 0;
				}
			}
		}
	}

	public static <T extends Comparable<T>> BinariComporation<T> create(
			ExpertOpinion<T> opinion) {
		return new BinariComporation<T>(opinion);
	}

	public ExpertOpinion<T> getOpinion() {
		return opinion;
	}

	public int[][] getComporationMatrix() {
		return comporationMatrix;
	}
}
