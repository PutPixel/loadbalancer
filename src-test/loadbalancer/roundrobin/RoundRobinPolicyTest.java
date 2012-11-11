package loadbalancer.roundrobin;

import loadbalancer.AbstractTest;
import loadbalancer.Balancer;

import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoundRobinPolicyTest extends AbstractTest {

	private static Balancer balancer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		balancer = new Balancer(new RoundRobinPolicy());
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
