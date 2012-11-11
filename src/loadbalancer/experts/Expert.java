package loadbalancer.experts;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class Expert {

	private final List<RangesForParametr> parametrsRanges;

	private Expert(List<RangesForParametr> parametrsRanges) {
		this.parametrsRanges = ImmutableList.copyOf(parametrsRanges);
	}

	static class RangesForParametr {
		private String parametrId;
		private List<Range> ranges = Lists.newArrayList();

		public String getParametrId() {
			return parametrId;
		}

		public List<Range> getRanges() {
			return ImmutableList.copyOf(ranges);
		}
	}

	static class Range {
		private BigDecimal low;
		private BigDecimal high;
		private BigDecimal score;

		public boolean acceptsPoint(BigDecimal point) {
			return low.compareTo(point) >= 0 && high.compareTo(point) < 0;
		}

		public BigDecimal getScore() {
			return score;
		}
	}

	public static class ExpertBuilder {

		private final List<RangesForParametr> parametrsRanges = Lists
				.newArrayList();

		private ExpertBuilder() {
		}

		public ParametrBuilder param(String paramId) {
			return new ParametrBuilder(paramId, this);
		}

		public Expert build() {
			return new Expert(parametrsRanges);
		}
	}

	public static class ParametrBuilder {
		private RangesForParametr rangesForParametr;
		private final ExpertBuilder expertBuilder;

		private ParametrBuilder(String paramId, ExpertBuilder expertBuilder) {
			this.expertBuilder = expertBuilder;
			rangesForParametr = new RangesForParametr();
			rangesForParametr.parametrId = paramId;
			expertBuilder.parametrsRanges.add(rangesForParametr);
		}

		public ParametrBuilder newRange(BigDecimal low, BigDecimal high,
				BigDecimal score) {
			Range range = new Range();
			range.low = low;
			range.high = high;
			range.score = score;
			rangesForParametr.ranges.add(range);
			return this;
		}

		public ParametrBuilder param(String paramId) {
			return expertBuilder.param(paramId);
		}
	}

	public ExpertBuilder builder() {
		return new ExpertBuilder();
	}

	public List<RangesForParametr> getParametrsRanges() {
		return parametrsRanges;
	}

}
