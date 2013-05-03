package loadbalancer.experts;

import java.math.BigDecimal;
import java.util.List;

import loadbalancer.IBalancerPolicy;
import loadbalancer.experts.KeminiMedian.AgentOpinion;
import loadbalancer.nodes.info.Node;
import loadbalancer.nodes.info.NodeParametr;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class AgentsPolicy implements IBalancerPolicy {
	
	private final List<Agent> experts;
	
	private static BigDecimal d(double d){
		return BigDecimal.valueOf(d);
	}

	{
		Agent agent1 = Agent.build()
			.param("CPU")
				.newRange(d(-1), d(0.1),  d(100))
				.newRange(d(0.1), d(0.3),  d(50))
				.newRange(d(0.3), d(0.6),  d(20))
				.newRange(d(0.6), d(0.8),  d(5))
				.newRange(d(0.8), d(2.1),  d(0))
			.param("MEM")
				.newRange(d(-1), d(0.4),  d(100))
				.newRange(d(0.4), d(0.6),  d(70))
				.newRange(d(0.6), d(0.9),  d(40))
				.newRange(d(0.9), d(2.1),  d(0))
			.param("CONNECTIONS")
				.newRange(d(-1), d(10),  d(100))
				.newRange(d(10), d(15),  d(60))
				.newRange(d(15), d(20),  d(10))
				.newRange(d(20), d(100),  d(0)).build();
		
		Agent agent2 = Agent.build()
			.param("CPU")
				.newRange(d(-1), d(0.1),  d(100))
				.newRange(d(0.1), d(0.3),  d(50))
				.newRange(d(0.3), d(0.6),  d(20))
				.newRange(d(0.6), d(0.8),  d(5))
				.newRange(d(0.8), d(2.1),  d(0))
			.param("MEM")
				.newRange(d(-1), d(0.4),  d(100))
				.newRange(d(0.4), d(0.6),  d(70))
				.newRange(d(0.6), d(0.9),  d(40))
				.newRange(d(0.9), d(2.1),  d(0))
			.param("CONNECTIONS")
				.newRange(d(-1), d(10),  d(100))
				.newRange(d(10), d(15),  d(60))
				.newRange(d(15), d(20),  d(10))
				.newRange(d(20), d(100),  d(0)).build();
		
		Agent agent3 = Agent.build()
			.param("CPU")
				.newRange(d(-1), d(0.6),  d(100))
				.newRange(d(0.6), d(0.8),  d(5))
				.newRange(d(0.8), d(2.1),  d(0))
			.param("MEM")
				.newRange(d(-1), d(0.8),  d(100))
				.newRange(d(0.6), d(2.1),  d(0))
			.param("CONNECTIONS")
				.newRange(d(-1), d(30),  d(100))
				.newRange(d(30), d(100),  d(0)).build();
		
		experts = Lists.newArrayList(agent1, agent2, agent3);
	}

	@Override
	public List<Node> balance(final List<Node> avlNodes) {
		List<AgentOpinion<Node>> ranges = FluentIterable.from(experts)
				.transform(new Function<Agent, AgentOpinion<Node>>() {

					@Override
					public AgentOpinion<Node> apply(Agent input) {
						return sortNodesAcordingToExpert(input, avlNodes);
					}

				}).toImmutableList();
		return KeminiMedian.createMedian(ranges).electBest().getRange();
	}

	private AgentOpinion<Node> sortNodesAcordingToExpert(
			final Agent expert, List<Node> avlNodes) {
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
		
		return AgentOpinion.createOpinion(sortedCopy);
	}
}
