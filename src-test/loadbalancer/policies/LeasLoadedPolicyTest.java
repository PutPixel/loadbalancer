package loadbalancer.policies;

import java.util.Collections;

import loadbalancer.AbstractTest;
import loadbalancer.Balancer;
import loadbalancer.nodes.info.Node;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class LeasLoadedPolicyTest extends AbstractTest {

	private static Balancer balancer;

	@BeforeClass
	public static void setUpBeforeClass() {
		balancer = new Balancer(new LeastLoadedPolicy());
	}
	
	@Test
	public void elect_on_empty() {
		Node bestNodeToInvoke = balancer.getBestNodeToInvoke(Collections.EMPTY_LIST);
		assertThat(bestNodeToInvoke, IsNull.nullValue());
	}

	@Test
	public void best_on_cluster_one() {
		assertThat(balancer.getBestNodeToInvoke(nodes_1),
				AnyOf.anyOf(Is.is(nodes_1.get(2)), Is.is(nodes_1.get(3))));
	}

	@Test
	public void best_on_cluster_two() {
		assertThat(balancer.getBestNodeToInvoke(nodes_2), Is.is(nodes_2.get(0)));
	}

	public static void main(String[] args) {
		LeasLoadedPolicyTest leasLoadedPolicyTest = new LeasLoadedPolicyTest();
		LeasLoadedPolicyTest.setUpBeforeClass();
		leasLoadedPolicyTest.best_on_cluster_one();
		leasLoadedPolicyTest.best_on_cluster_two();
	}

}
