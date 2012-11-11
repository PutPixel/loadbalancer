package loadbalancer.roundrobin;

import java.util.List;

import loadbalancer.IBalancerPolicy;
import loadbalancer.nodes.info.Node;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

public class RoundRobinPolicy implements IBalancerPolicy {

	private String lastNodeId = "FirstInvocation";

	@Override
	public List<Node> balance(List<Node> avlNodes) {
		List<Node> result = doBalance(avlNodes);
		lastNodeId = Iterables.getFirst(result, null).getNodeId();
		return result;
	}

	private List<Node> doBalance(List<Node> avlNodes) {
		List<Node> sortedByIdCopy = Ordering.natural()
				.onResultOf(new Function<Node, String>() {

					@Override
					public String apply(Node input) {
						return input.getNodeId();
					}
				}).sortedCopy(avlNodes);

		Optional<Node> barrierNode = Iterables.tryFind(sortedByIdCopy,
				new Predicate<Node>() {

					@Override
					public boolean apply(Node input) {
						return lastNodeId.equals(input.getNodeId());
					}
				});

		for (Node node : barrierNode.asSet()) {
			int barrierIndex = sortedByIdCopy.indexOf(node);
			if (barrierIndex + 1 == sortedByIdCopy.size()) {
				break;
			}
			List<Node> firstChain = sortedByIdCopy.subList(barrierIndex + 1,
					sortedByIdCopy.size());
			List<Node> lastChain = sortedByIdCopy.subList(0, barrierIndex + 1);
			return ImmutableList.<Node> builder()
					.addAll(firstChain).addAll(lastChain).build();
		}
		return sortedByIdCopy;
	}
}
