package loadbalancer;

import loadbalancer.nodes.info.Node;
import loadbalancer.nodes.info.NodeParametr;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;

import com.google.common.collect.Lists;

public class AbstractTest extends Assert {

	protected List<Node> nodes_1 = Lists.newArrayList();
	protected List<Node> nodes_2 = Lists.newArrayList();

	{
		nodes_1.add(new Node("1_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.23")), 
				new NodeParametr("MEM", new BigDecimal("0.53")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("10")))));
		nodes_1.add(new Node("2_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.83")), 
				new NodeParametr("MEM", new BigDecimal("0.63")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("20")))));
		nodes_1.add(new Node("3_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.13")), 
				new NodeParametr("MEM", new BigDecimal("0.33")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("5")))));
		nodes_1.add(new Node("4_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.13")), 
				new NodeParametr("MEM", new BigDecimal("0.33")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("5")))));
	}
	
	{
		nodes_2.add(new Node("1_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.23")), 
				new NodeParametr("MEM", new BigDecimal("0.53")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("10")))));
		nodes_2.add(new Node("2_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.83")), 
				new NodeParametr("MEM", new BigDecimal("0.63")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("20")))));
		nodes_2.add(new Node("3_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.23")), 
				new NodeParametr("MEM", new BigDecimal("0.63")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("15")))));
		nodes_2.add(new Node("4_Node", Lists.newArrayList(
				new NodeParametr("CPU", new BigDecimal("0.53")), 
				new NodeParametr("MEM", new BigDecimal("0.33")), 
				new NodeParametr("CONNECTIONS", new BigDecimal("15")))));
	}

}
