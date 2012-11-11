package loadbalancer.experts;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.sun.rmi.rmid.ExecOptionPermission;

import loadbalancer.IBalancerPolicy;
import loadbalancer.experts.KeminiMedian.ExpertOpinion;
import loadbalancer.nodes.info.Node;
import loadbalancer.nodes.info.NodeParametr;

public class ExpertsPolicy implements IBalancerPolicy {
	
	private final List<Expert> experts;
	
	private static BigDecimal d(double d){
		return BigDecimal.valueOf(d);
	}

	{
		Expert expert1 = Expert.build()
			.param("CPU")
				.newRange(d(0), d(0.1),  d(100))
				.newRange(d(0.1), d(0.3),  d(50))
				.newRange(d(0.3), d(0.6),  d(20))
				.newRange(d(0.6), d(0.8),  d(5))
				.newRange(d(0.8), d(1.1),  d(0))
			.param("MEM")
				.newRange(d(0), d(0.4),  d(100))
				.newRange(d(0.4), d(0.6),  d(70))
				.newRange(d(0.6), d(0.9),  d(40))
				.newRange(d(0.9), d(1.1),  d(0))
			.param("CONNECTIONS")
				.newRange(d(0), d(10),  d(100))
				.newRange(d(10), d(15),  d(60))
				.newRange(d(15), d(20),  d(10))
				.newRange(d(20), d(100),  d(0)).build();
		
		Expert expert2 = Expert.build()
			.param("CPU")
				.newRange(d(0), d(0.1),  d(100))
				.newRange(d(0.1), d(0.3),  d(50))
				.newRange(d(0.3), d(0.6),  d(20))
				.newRange(d(0.6), d(0.8),  d(5))
				.newRange(d(0.8), d(1.1),  d(0))
			.param("MEM")
				.newRange(d(0), d(0.4),  d(100))
				.newRange(d(0.4), d(0.6),  d(70))
				.newRange(d(0.6), d(0.9),  d(40))
				.newRange(d(0.9), d(1.1),  d(0))
			.param("CONNECTIONS")
				.newRange(d(0), d(10),  d(100))
				.newRange(d(10), d(15),  d(60))
				.newRange(d(15), d(20),  d(10))
				.newRange(d(20), d(100),  d(0)).build();
		
		Expert expert3 = Expert.build()
			.param("CPU")
				.newRange(d(0), d(0.6),  d(100))
				.newRange(d(0.6), d(0.8),  d(5))
				.newRange(d(0.8), d(1.1),  d(0))
			.param("MEM")
				.newRange(d(0), d(0.8),  d(100))
				.newRange(d(0.6), d(1.1),  d(0))
			.param("CONNECTIONS")
				.newRange(d(0), d(30),  d(100))
				.newRange(d(30), d(100),  d(0)).build();
		
		experts = Lists.newArrayList(expert1, expert2, expert3);
	}

	@Override
	public List<Node> balance(final List<Node> avlNodes) {
		List<ExpertOpinion<Node>> ranges = FluentIterable.from(experts)
				.transform(new Function<Expert, ExpertOpinion<Node>>() {

					@Override
					public ExpertOpinion<Node> apply(Expert input) {
						return sortNodesAcordingToExpert(input, avlNodes);
					}

				}).toImmutableList();
		return KeminiMedian.createMedian(ranges).electBest().getRange();
	}

	private ExpertOpinion<Node> sortNodesAcordingToExpert(
			final Expert expert, List<Node> avlNodes) {
		List<Node> sortedCopy = Ordering.natural().reverse().onResultOf(new Function<Node, BigDecimal>() {

			@Override
			public BigDecimal apply(Node input) {
				BigDecimal summ = BigDecimal.ZERO;
				for (NodeParametr param: input.getParametrs() ) {
					summ = summ.add(expert.getScoreForParametr(param));
				}
				System.err.println(input + " score: " + summ);
				return summ;
			}
		}).sortedCopy(avlNodes);
		
		return ExpertOpinion.createOpinion(sortedCopy);
	}
}
