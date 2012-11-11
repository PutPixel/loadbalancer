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

	public final Node getBestNodeToInvoke(List<Node> nodes) {
		Node node = doGetBestNode(policy.balance(nodes));
		return node;
	}

	protected Node doGetBestNode(List<Node> orderedNodes) {
		return Iterables.getFirst(orderedNodes, null);
	}

}
