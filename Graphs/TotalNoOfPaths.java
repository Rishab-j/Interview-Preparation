import java.util.*;

public class TotalNoOfPaths {
    void BFS(Vector<Integer>[] adj, int src, int dist[], int paths[], int n) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++)
            visited[i] = false;
        dist[src] = 0;
        paths[src] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = true;
        while (!q.isEmpty()) {
            int curr = q.peek();
            q.poll();

            // For all neighbors of current vertex do:
            for (int x : adj[curr]) {

                // if the current vertex is not yet
                // visited, then push it to the queue.
                if (visited[x] == false) {
                    q.add(x);
                    visited[x] = true;
                }

                // check if there is a better path.
                if (dist[x] > dist[curr] + 1) {
                    dist[x] = dist[curr] + 1;
                    paths[x] = paths[curr];
                }

                // additional shortest paths found
                else if (dist[x] == dist[curr] + 1)
                    paths[x] += paths[curr];
            }
        }
    }
}
