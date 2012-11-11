package loadbalancer;

import java.util.List;

import loadbalancer.nodes.info.Node;

public interface IBalancerPolicy {

	/**
	 * Policy sorts available nodes according they load.
	 * 
	 * @param avlNodes
	 * @return Sorted list of nodes. First node is best for invocation.
	 */
	List<Node> balance(List<Node> avlNodes);
}
