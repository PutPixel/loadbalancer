package loadbalancer;

import java.util.List;

import loadbalancer.nodes.info.Node;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

public class Balancer {

	private final IBalancerPolicy policy;

	public Balancer(IBalancerPolicy policy) {
		this.policy = Preconditions.checkNotNull(policy);
	}

	public Node getBestNodeToInvoke(List<Node> nodes) {
		return Iterables.getFirst(policy.balance(nodes), null);
	}

}
