import java.util.*;

public class Kosaraju {

    public void dfs(int src, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack, boolean[] vis) {

        vis[src] = true;

        for (int e : adj.get(src)) {
            if (!vis[e])
                dfs(e, adj, stack, vis);
        }

        stack.push(src);

    }

    public void dfs(ArrayList<Integer>[] adj, int src, boolean[] vis) {

        vis[src] = true;

        for (int e : adj[src]) {
            if (!vis[e])
                dfs(adj, e, vis);
        }

    }

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here

        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i])
                dfs(i, adj, stack, vis);
        }

        // ArrayList<Integer>[] al = new ArrayList[n];
        ArrayList<Integer>[] rev = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            rev[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < V; i++) {
            for (int e : adj.get(i)) {
                rev[e].add(i);
            }
        }

        int scc = 0;
        vis = new boolean[V];
        while (!stack.isEmpty()) {
            int t = stack.pop();
            if (!vis[t]) {
                scc++;
                dfs(rev, t, vis);
            }
        }

        return scc;
    }

}
