package Graph;

import org.graphstream.graph.Node;

import java.util.Comparator;
import java.util.Map;

public class NodeComparator implements Comparator<Node> {

    private Map<Node, Double> costs;

    public NodeComparator(Map<Node, Double> costs) {
        this.costs = costs;
    }


    public int compare(Node node1, Node node2) {
        int cost1 = costs.get(node1).intValue();
        int cost2 = costs.get(node2).intValue();

        return Integer.valueOf(cost1).compareTo(cost2);
    }


}
