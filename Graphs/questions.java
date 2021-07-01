import java.util.*;

class questions {

    /** BFS of Graph */

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> que = new LinkedList<Integer>();
        boolean[] vis = new boolean[V];

        que.add(0);
        vis[0] = true;

        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int r = que.remove();
                ans.add(r);

                for (int e : adj.get(r)) {
                    if (!vis[e]) {
                        vis[e] = true;
                        que.add(e);
                    }
                }

            }
        }
        return ans;
    }

    /** Leetcode 785: Is Graph Bipartite */

    class Color {
        int vtx;
        int color;

        Color(int vtx, int color) {
            this.vtx = vtx;
            this.color = color;
        }
    }

    public boolean isBipartite(int[][] graph) {

        if (graph.length == 1)
            return true;

        int[] vis = new int[graph.length];
        Arrays.fill(vis, -1);

        for (int i = 0; i < graph.length; i++) {

            if (vis[i] == -1) {
                Queue<Color> que = new LinkedList<Color>();
                que.add(new Color(i, 1));

                while (que.size() != 0) {

                    Color r = que.remove();

                    if (vis[r.vtx] != -1) {
                        int color = r.color;
                        if (color != vis[r.vtx])
                            return false;
                    }

                    vis[r.vtx] = r.color;

                    for (int e : graph[r.vtx]) {
                        if (vis[e] == -1) {
                            int color = r.color == 1 ? 2 : 1;
                            que.add(new Color(e, color));
                        }
                    }

                }
            }
        }

        return true;
    }

    /** Leetcode 815: Bus Routes */

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target)
            return 0;

        HashMap<Integer, ArrayList<Integer>> busStands = new HashMap<>();

        boolean[] busVis = new boolean[routes.length];
        Set<Integer> busStopsVis = new HashSet<>();

        for (int i = 0; i < routes.length; i++) {
            for (int stand : routes[i]) {
                busStands.putIfAbsent(stand, new ArrayList<>());
                busStands.get(stand).add(i);
            }
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(source);
        busStopsVis.add(source);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int busStop = que.remove();
                for (int bus : busStands.get(busStop)) {
                    if (busVis[bus])
                        continue;

                    busVis[bus] = true;

                    for (int stop : routes[bus]) {
                        if (!busStopsVis.contains(stop)) {
                            busStopsVis.add(stop);

                            if (stop == target)
                                return level + 1;

                            que.add(stop);
                        }
                    }
                }
            }

            level++;
        }
        return -1;
    }

    /** Prim's Algo */

    public int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        int sum = 0;
        boolean[] vis = new boolean[V];

        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a, b) -> {
            return a.get(1) - b.get(1);
        });

        ArrayList<Integer> first = new ArrayList<>();
        first.add(0);
        first.add(0);

        pq.add(first);

        int N = 0;

        while (N < adj.size()) {
            ArrayList<Integer> vtx = pq.remove();

            if (vis[vtx.get(0)])
                continue;

            N++;
            vis[vtx.get(0)] = true;

            if (vtx.get(0) != 0)
                sum += vtx.get(1);

            for (ArrayList<Integer> edge : adj.get(vtx.get(0))) {
                if (!vis[edge.get(0)])
                    pq.add(edge);
            }
        }

        return sum;
    }

    /** Dijkstra Algo -> GFG */

    /** Leetcode 399. Evaluate Division */
    class Edge {
        String vtx;
        double val;

        Edge(String vtx, double val) {
            this.vtx = vtx;
            this.val = val;
        }
    }

    double res = 1;

    public boolean calculate(HashMap<String, ArrayList<Edge>> graph, String num, String den, HashSet<String> set,
            double ans) {

        if (num.equals(den)) {
            res = ans;
            return true;
        }

        set.add(num);
        boolean b = false;
        for (Edge e : graph.get(num)) {
            if (!set.contains(e.vtx)) {
                b = b || calculate(graph, e.vtx, den, set, ans * e.val);
            }
        }

        return b;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        if (equations.size() == 0)
            return new double[0];

        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        int i = 0;
        for (List<String> eq : equations) {
            graph.putIfAbsent(eq.get(0), new ArrayList<>());
            graph.get(eq.get(0)).add(new Edge(eq.get(1), values[i]));

            graph.putIfAbsent(eq.get(1), new ArrayList<>());
            graph.get(eq.get(1)).add(new Edge(eq.get(0), (1 / values[i])));

            i++;
        }

        double[] ans = new double[queries.size()];
        int j = 0;
        Arrays.fill(ans, -1.0d);

        for (List<String> q : queries) {
            if (graph.containsKey(q.get(0))) {
                boolean r = calculate(graph, q.get(0), q.get(1), new HashSet<String>(), 1.0d);
                if (r)
                    ans[j] = res;
                res = 1;

            }
            j++;
        }
        return ans;
    }

    /** GFG Mother vertex */

    void DFSUtil(ArrayList<ArrayList<Integer>> g, int v, boolean[] visited) {
        visited[v] = true;
        for (int x : g.get(v)) {
            if (!visited[x]) {
                DFSUtil(g, x, visited);
            }
        }
    }

    int motherVertex(ArrayList<ArrayList<Integer>> g, int V) {

        boolean[] visited = new boolean[V];

        int v = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFSUtil(g, i, visited);
                v = i;
            }
        }

        boolean[] check = new boolean[V];
        DFSUtil(g, v, check);
        for (boolean val : check) {
            if (!val) {
                return -1;
            }
        }
        return v;
    }

    /** Leetcode 1020. Number of Enclaves */

    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public void dfs(int[][] grid, int i, int j) {

        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1)
                dfs(grid, r, c);
        }

    }

    public int numEnclaves(int[][] grid) {

        if (grid.length == 0)
            return 0;

        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1)
                dfs(grid, i, 0);
            if (grid[i][m - 1] == 1)
                dfs(grid, i, m - 1);
        }

        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1)
                dfs(grid, 0, j);
            if (grid[n - 1][j] == 1)
                dfs(grid, n - 1, j);
        }

        int count = 0;
        for (int[] g : grid) {
            for (int e : g) {
                if (e == 1)
                    count++;
            }
        }

        return count;
    }

    /** Leetcode 542. 01 Matrix */

    class Data {
        int x, y, d;

        Data(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public int[][] updateMatrix(int[][] matrix) {

        Queue<Data> que = new LinkedList<Data>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0)
                    que.add(new Data(i, j, matrix[i][j]));

                else if (matrix[i][j] == 1)
                    matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        while (que.size() != 0) {
            Data r = que.remove();

            for (int d = 0; d < 4; d++) {

                int row = r.x + dir[d][0];
                int col = r.y + dir[d][1];

                if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length
                        && matrix[row][col] == Integer.MAX_VALUE) {
                    matrix[row][col] = r.d + 1;
                    que.add(new Data(row, col, r.d + 1));
                }

            }

        }
        return matrix;
    }

    /** Leetcode 200. Number of Islands */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsFill(grid, i, j);
                    count++;
                }
            }
        return count;
    }

    private void dfsFill(char[][] grid, int i, int j) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = '0';
            dfsFill(grid, i + 1, j);
            dfsFill(grid, i - 1, j);
            dfsFill(grid, i, j + 1);
            dfsFill(grid, i, j - 1);
        }
    }

    /** Leetcode Number of Distinct Islands */

    // Notebook

    /** Leetcode 127. Word Ladder */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord))
            return 0;

        Queue<String> que = new LinkedList<>();
        que.add(beginWord);

        int level = 1;
        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                String word = que.remove();
                char[] ch = word.toCharArray();

                for (int i = 0; i < ch.length; i++) {
                    char old = ch[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[i] = c;
                        String s = String.valueOf(ch);
                        if (s.equals(endWord))
                            return level + 1;

                        if (set.contains(s)) {
                            que.add(s);
                            set.remove(s);
                        }
                    }
                    ch[i] = old;
                }
            }
            level++;
        }

        return 0;
    }

    /** Leetcode 943. Shortest Bridge */

    class Node {
        int x, y, d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    // int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public void dfs(int[][] grid, Queue<Node> que, int r, int c) {

        grid[r][c] = 2;

        for (int d = 0; d < 4; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                que.add(new Node(x, y, 0));
                dfs(grid, que, x, y);
            }
        }

    }

    public int shortestBridge(int[][] grid) {

        Queue<Node> que = new LinkedList<Node>();
        boolean flag = false;

        for (int i = 0; i < grid.length; i++) {

            if (flag)
                break;

            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    que.add(new Node(i, j, 0));
                    dfs(grid, que, i, j);
                    flag = true;
                    break;
                }
            }

        }

        while (que.size() != 0) {
            Node r = que.remove();

            for (int d = 0; d < 4; d++) {
                int x = r.x + dir[d][0];
                int y = r.y + dir[d][1];

                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {

                    if (grid[x][y] == 1)
                        return r.d;

                    else if (grid[x][y] == 0) {
                        grid[x][y] = 2;
                        que.add(new Node(x, y, r.d + 1));
                    }

                }
            }
        }

        return 0;
    }

    /** Leetcode 1162. As Far from Land as Possible */

    // similar as 01 matrix

    /** Leetcode 773. Sliding Puzzle */

    public String swap(String curr, int i, int j) {
        StringBuilder sb = new StringBuilder(curr);
        sb.setCharAt(i, curr.charAt(j));
        sb.setCharAt(j, curr.charAt(i));

        return sb.toString();
    }

    public int slidingPuzzle(int[][] board) {

        int[][] dir = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

        String goal = "123450";
        String state = "";

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                state = state + board[i][j];
            }
        }

        if (state.equals(goal))
            return 0;

        HashSet<String> vis = new HashSet<>();
        Queue<String> que = new LinkedList<String>();

        que.add(state);
        vis.add(state);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                String r = que.remove();
                int idx = r.indexOf('0');

                for (int d : dir[idx]) {
                    String pattern = swap(r, idx, d);

                    if (pattern.equals(goal))
                        return level + 1;

                    if (!vis.contains(pattern)) {
                        vis.add(pattern);
                        que.add(pattern);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    /** Bellman Ford -> GFG */

    /** Leetcode 1034. Coloring A Border */

    public void paint(int[][] grid, int r, int c, boolean[][] vis, int color, int oldColor) {

        int count = 0;

        grid[r][c] = color;
        vis[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) {
                if (grid[x][y] == oldColor) {
                    count++;
                    paint(grid, x, y, vis, color, oldColor);
                } else if (grid[x][y] == color) {
                    if (vis[x][y])
                        count++;
                }
            }
        }
        if (count == 4)
            grid[r][c] = oldColor;
    }

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {

        int n = grid.length;
        int m = grid[0].length;

        int oldColor = grid[r0][c0];

        if (oldColor == color)
            return grid;

        boolean[][] vis = new boolean[n][m];

        paint(grid, r0, c0, vis, color, oldColor);

        return grid;
    }

    /** Rotting oranges -> easy */
    /** Topological Sort -> GFG */
    /** Kahn's Algo -> GFG */

    /** Leetcode 210. Course Schedule II */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        if (numCourses == 1)
            return new int[1];

        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
            indegree[pre[0]]++;
        }

        Queue<Integer> que = new LinkedList<Integer>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                que.add(i);
        }

        int[] ans = new int[numCourses];
        int i = 0;

        while (que.size() != 0) {
            int r = que.remove();
            ans[i] = r;
            i++;

            for (int e : graph[r]) {
                if (--indegree[e] == 0)
                    que.add(e);
            }

        }

        if (i == numCourses) {
            return ans;
        }

        return new int[] {};
    }

    // Articulation Point -> HackerEarth
    // Eulerian Path and Ckt. for directed and undirected graph -> GFG

    // ** Number of Islands 2 */

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] lands = new int[m * n];
        ArrayList<Integer> result = new ArrayList<>();
        int count = 0;
        int[][] neighbors = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int i = 0; i < m * n; i++) {
            lands[i] = -1;
        }
        for (int i = 0; i < positions.length; i++) {

            int pX = positions[i][0];
            int pY = positions[i][1];
            if (lands[pX * n + pY] != -1)
                continue;
            count++;
            lands[pX * n + pY] = pX * n + pY;
            for (int k = 0; k < neighbors.length; k++) {
                int nX = pX + neighbors[k][0];
                int nY = pY + neighbors[k][1];

                if (nX >= 0 && nX < m && nY >= 0 && nY < n && lands[nX * n + nY] != -1) {
                    int pRoot = find(lands, pX * n + pY);
                    int nRoot = find(lands, nX * n + nY);
                    if (pRoot != nRoot) {
                        count--;
                        lands[pRoot] = nRoot;// union happens here
                    }

                }
            }

            result.add(count);
        }

        return result;
    }

    private int find(int[] lands, int index) {
        while (index != lands[index])
            index = lands[index];
        return index;
    }

    /** Leetcode 959. Regions Cut By Slashes */

    int[] par;

    public int findPar(int x) {
        if (par[x] == x)
            return x;
        return par[x] = findPar(par[x]);
    }

    public int regionsBySlashes(String[] grid) {

        int n = grid.length + 1;
        int m = grid[0].length() + 1;
        int len = n * m;

        par = new int[len];

        int count = 1;

        for (int i = 0; i < len; i++) {
            par[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int j = 0;
            par[i * m + j] = 0;
            j = m - 1;
            par[i * m + j] = 0;
        }

        for (int j = 0; j < m; j++) {
            int i = 0;
            par[i * m + j] = 0;
            i = n - 1;
            par[i * m + j] = 0;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                char ch = grid[i].charAt(j);

                if (ch == '\\') {
                    int sIdx = i * m + j;
                    int p1 = findPar(sIdx);

                    int eIdx = (i + 1) * m + (j + 1);
                    int p2 = findPar(eIdx);

                    if (p1 != p2) {
                        par[p2] = p1;
                    } else {
                        count++;
                    }

                } else if (ch == '/') {
                    int sIdx = i * m + j + 1;
                    int p1 = findPar(sIdx);

                    int eIdx = (i + 1) * m + j;
                    int p2 = findPar(eIdx);

                    if (p1 != p2) {
                        par[p2] = p1;
                    } else {
                        count++;
                    }
                }

            }
        }
        return count;
    }

    /** Leetcode 990. Satisfiability of Equality Equations */

    public int[] par1 = new int[26];

    public int findPar1(int u) {
        if (par1[u] == u)
            return u;
        return par1[u] = findPar1(par1[u]);
    }

    public boolean isSimilar(String a) {
        if (a.charAt(1) == '=')
            return true;
        else
            return false;
    }

    public boolean equationsPossible(String[] equations) {

        for (int i = 0; i < 26; i++) {
            par1[i] = i;
        }

        for (int i = 0; i < equations.length; i++) {

            if (isSimilar(equations[i])) {
                int p1 = findPar1(equations[i].charAt(0) - 'a');
                int p2 = findPar1(equations[i].charAt(3) - 'a');

                par1[p2] = p1;
            }
        }

        for (int i = 0; i < equations.length; i++) {
            if (!isSimilar(equations[i])) {
                int p1 = findPar1(equations[i].charAt(0) - 'a');
                int p2 = findPar1(equations[i].charAt(3) - 'a');

                if (p1 == p2)
                    return false;
            }
        }

        return true;
    }

    /** Leetcode 684. Redundant Connection */

    class UnionFind {
        int[] par;
        int[] rank;

        UnionFind(int n) {
            par = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                par[i] = i;
            }

            rank = new int[n + 1];
            Arrays.fill(rank, 1);
        }

        public int find(int x) {
            if (x == par[x])
                return x;
            return par[x] = find(par[x]);
        }

        public boolean union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);

            if (p1 == p2)
                return false;
            else {
                if (rank[p1] > rank[p2])
                    par[p2] = p1;
                else if (rank[p2] > rank[p1])
                    par[p1] = p2;
                else {
                    par[p1] = p2;
                    rank[p2]++;
                }
            }
            return true;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {

        if (edges.length <= 2)
            return new int[2];

        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1]))
                return edge;
        }

        return new int[2];
    }

    /** Leetcode 685. Redundant Connection II */

    class UnionFind1 {

        int[] par;

        UnionFind1(int n) {
            par = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                par[i] = i;
            }
        }

        public int find(int x) {
            if (x == par[x])
                return x;
            return par[x] = find(par[x]);
        }

        public boolean union(int x, int y) {
            int par1 = find(x);
            int par2 = find(y);

            if (par1 != par2) {
                par[par1] = par2;
            } else {
                return true;
            }
            return false;
        }

    }

    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;

        if (n == 0)
            return new int[2];

        UnionFind1 uf = new UnionFind1(n);

        int[] indegree = new int[n + 1];
        Arrays.fill(indegree, -1);

        int candidate1 = -1;
        int candidate2 = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[edges[i][1]] == -1) {
                indegree[edges[i][1]] = i;
            } else {
                candidate1 = i;
                candidate2 = indegree[edges[i][1]];
            }
        }

        int[] ans = new int[2];

        if (candidate1 == -1) {
            for (int i = 0; i < n; i++) {
                if (uf.union(edges[i][0], edges[i][1]))
                    ans = edges[i];
            }
        } else {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (i != candidate1) {
                    if (uf.union(edges[i][0], edges[i][1])) {
                        found = true;
                        ans = edges[candidate2];
                        break;
                    }
                }
            }
            if (!found) {
                ans = edges[candidate1];
            }
        }
        return ans;
    }

    /** GFG: Castle Run */

    public int isPossible(int[][] paths) {
        int even = 0;

        for (int i = 0; i < paths.length; i++) {
            int count = 0;
            for (int j = 0; j < paths.length; j++) {
                if (paths[i][j] == 1)
                    count++;
            }

            if (count % 2 == 0)
                even++;
        }

        if (even == paths.length)
            return 1;
        return 0;
    }


    
}