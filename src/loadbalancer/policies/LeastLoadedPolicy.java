package loadbalancer.policies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import loadbalancer.IBalancerPolicy;
import loadbalancer.nodes.info.Node;
import loadbalancer.nodes.info.NodeParametr;

public class LeastLoadedPolicy implements IBalancerPolicy {

	@Override
	public List<Node> balance(List<Node> avlNodes) {
		final Map<String, BigDecimal> highestParams = fillHighestParametrMap(avlNodes);

		return Ordering.natural().onResultOf(new Function<Node, BigDecimal>() {

			@Override
			public BigDecimal apply(Node input) {
				BigDecimal summ = BigDecimal.ZERO;
				for (NodeParametr param : input.getParametrs()) {
					summ = summ.add(param.getValue().divide(
							highestParams.get(param.getParametrId()), 5,
							BigDecimal.ROUND_HALF_UP));
				}
				return summ;
			}
		}).sortedCopy(avlNodes);
	}

	private Map<String, BigDecimal> fillHighestParametrMap(List<Node> avlNodes) {
		Map<String, BigDecimal> highestParams = Maps.newHashMap();
		for (Node node : avlNodes) {
			for (NodeParametr nodeParametr : node.getParametrs()) {
				String paramId = nodeParametr.getParametrId();
				BigDecimal value = highestParams.get(paramId);
				if (value == null
						|| value.compareTo(nodeParametr.getValue()) < 0) {
					highestParams.put(paramId, nodeParametr.getValue());
				}
			}
		}

		return highestParams;
	}

}
