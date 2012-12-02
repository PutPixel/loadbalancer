package loadbalancer.experts;

import loadbalancer.AbstractTest;
import loadbalancer.Balancer;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class ExpertsPolicyTest extends AbstractTest {

	private static Balancer balancer;

	@BeforeClass
	public static void setUpBeforeClass() {
		balancer = new Balancer(new ExpertsPolicy());
	}

	@Test
	public void best_on_cluster_one() {
		assertThat(balancer.getBestNodeToInvoke(nodes_1),
				AnyOf.anyOf(Is.is(nodes_1.get(2)), Is.is(nodes_1.get(3))));
	}

	@Test
	public void best_on_cluster_two() {
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
	}

}
