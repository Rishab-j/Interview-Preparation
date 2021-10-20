
/**
 * CloneGraph
 */

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Map<Integer, Node> map = new HashMap<Integer, Node>();

        return helper(node, map);
    }

    private Node helper(Node node, Map<Integer, Node> map) {
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }

        Node cloned = new Node(node.val);
        map.put(node.val, cloned);

        for (Node neighbor : node.neighbors) {
            cloned.neighbors.add(helper(neighbor, map));
        }
        return cloned;
    }

    // BFS
    public Node cloneGraph_2(Node node) {
        if (node == null)
            return null;

        HashMap<Integer, Node> map = new HashMap<>();

        Node clone = new Node(node.val);

        map.put(clone.val, clone);

        Queue<Node> q = new LinkedList<>();

        q.add(node);

        while (q.size() != 0) {
            Node r = q.remove();

            List<Node> neighbors = r.neighbors;

            for (Node n : neighbors) {
                if (!map.containsKey(n.val)) {
                    Node c = new Node(n.val);
                    map.put(n.val, c);
                    q.add(n);
                }

                map.get(r.val).neighbors.add(map.get(n.val));
            }

        }

        return clone;
    }
}