package loadbalancer.experts;

import java.util.List;

import com.google.common.collect.Lists;

import loadbalancer.IBalancerPolicy;
import loadbalancer.nodes.info.Node;

public class ExpertsPolicy implements IBalancerPolicy {
	
	private final List<Expert> experts = Lists.newArrayList();

	{
	}

	@Override
	public List<Node> balance(List<Node> avlNodes) {
		// TODO Auto-generated method stub
		return null;
	}

}
