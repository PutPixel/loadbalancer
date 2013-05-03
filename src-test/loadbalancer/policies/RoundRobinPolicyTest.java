package loadbalancer.policies;

import java.util.Collections;

import loadbalancer.AbstractTest;
import loadbalancer.Balancer;
import loadbalancer.nodes.info.Node;
import loadbalancer.policies.RoundRobinPolicy;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoundRobinPolicyTest extends AbstractTest {

	private static Balancer balancer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		balancer = new Balancer(new RoundRobinPolicy());
	}
	
	@Test
	public void elect_on_empty() {
		Node bestNodeToInvoke = balancer.getBestNodeToInvoke(Collections.EMPTY_LIST);
		assertThat(bestNodeToInvoke, IsNull.nullValue());
	}

	@Test
	public void chain_invvocation() {
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(1)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(2)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(3)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(1)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(2)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(3)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(1)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(2)));
		assertThat(balancer.getBestNodeToInvoke(nodes_1), Is.is(nodes_1.get(3)));
	}

}
