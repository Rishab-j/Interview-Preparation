import java.util.*;

class questions {

    public class TreeNode { // Leetcode Tree Definition
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /** Leetcode 94 Binary Tree Inorder Traversal */

    public List<Integer> inorderTraversal(TreeNode root) { // Using Stack Time : O(N) and Space : O(N)
        if (root == null)
            return new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        TreeNode curr = root;
        while (curr != null || stack.size() > 0) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            ans.add(curr.val);
            curr = curr.right;
        }

        return ans;
    }

    public TreeNode rightMost(TreeNode node, TreeNode curr) { // Using Morris Inorder Traversal
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public List<Integer> inorderTraversal_2(TreeNode root) { // Time : O(N) and Space : O(1)

        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {

            TreeNode leftNode = curr.left;

            if (leftNode == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode last = rightMost(leftNode, curr);

                if (last.right == null) {
                    last.right = curr;
                    curr = curr.left;
                } else if (last.right == curr) {
                    last.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    /** Leetcode 144 Binary Tree Preorder Traversal */

    public List<Integer> preorderTraversal(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null || stack.size() != 0) {

            while (curr != null) {
                ans.add(curr.val);
                stack.add(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            curr = curr.right;

        }

        return ans;

    }

    public List<Integer> preorderTraversal_2(TreeNode node) { // Time : O(N) and Space : O(1) (Morris Traversal)

        List<Integer> ans = new ArrayList<Integer>();

        TreeNode curr = node;
        while (curr != null) {
            TreeNode leftNode = curr.left;
            if (leftNode == null) { // left null
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rmost = rightMost(leftNode, curr);
                if (rmost.right == null) { // thread Creation
                    ans.add(curr.val);
                    rmost.right = curr;
                    curr = curr.left;
                } else { // thread Break

                    rmost.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    /** Leetcode 145 Binary Tree Postorder Traversal */

    public List<Integer> postorderTraversal(TreeNode root) { // Time : O(N) and Space : O(N) (Morris traversal is not
                                                             // possible for postorder)

        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();

        TreeNode curr = root;
        while (curr != null || stack.size() != 0) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.pop();
                    ans.add(temp.val);
                    while (stack.size() != 0 && stack.peek().right == temp) {
                        temp = stack.pop();
                        ans.add(temp.val);
                    }
                } else {
                    curr = temp;
                }
            }
        }

        return ans;
    }

    /** Leetcode 102 Level Order Traversal */

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null)
            return new ArrayList<>();

        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (queue.size() != 0) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode t = queue.remove();
                list.add(t.val);
                if (t.left != null)
                    queue.add(t.left);
                if (t.right != null)
                    queue.add(t.right);
                size--;
            }
            ans.add(list);
        }
        return ans;
    }

    /** 1038. Binary Search Tree to Greater Sum Tree */

    int sum = 0;

    public void gst(TreeNode node) {
        if (node == null)
            return;
        gst(node.right);
        sum += node.val;
        node.val = sum;
        gst(node.left);
    }

    public TreeNode bstToGst(TreeNode root) {
        if (root == null)
            return null;
        gst(root);
        return root;
    }

    /** Leetcode 116 Populating Next Right Pointers in Each Node */

    class Node { // Node Definition
        public int data;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            data = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            data = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) { // Using level order traversal
        if (root == null)
            return null;

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (queue.size() != 0) {
            int size = queue.size();
            while (size > 0) {
                Node t = queue.remove();
                if (size > 1)
                    t.next = queue.peek();
                if (t.left != null)
                    queue.add(t.left);
                if (t.right != null)
                    queue.add(t.right);
                size--;
            }
        }
        return root;
    }

    public Node connect_2(Node root) { // Without using Level order
        Node curr = root;
        while (curr != null) {
            Node temp = curr;
            while (temp != null) {
                if (temp.left != null && temp.right != null)
                    temp.left.next = temp.right;
                if (temp.next != null) {
                    if (temp.right != null)
                        temp.right.next = temp.next.left;
                }
                temp = temp.next;
            }
            curr = curr.left;
        }
        return root;
    }

    /** Leetcode 834: Sum of Distances in Tree */

    List<List<Integer>> tree;
    int[] res, count;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<>();
        res = new int[N];
        count = new int[N];
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        postDFS(2, -1, 1, 2);
        preDFS(2, -1);
        return res;
    }

    private void postDFS(int root, int parent, int level, int point) {
        for (int i : tree.get(root)) {
            if (i != parent) {
                postDFS(i, root, level + 1, point);
                count[root] += count[i]; // nodes in subtree root
                // res[root] += res[i] + count[i]; // sum distance at root
                res[point] += level;
            }
        }
        count[root]++; // the root node itself
    }

    // private int levelOrder(int root,int n){

    // int[] visited = new int[n];

    // int dist = 0;
    // Queue<Integer> que = new LinkedList<Integer>();

    // que.add(root);
    // visited[root] = 1;

    // int level = 0;

    // while(que.size() != 0){
    // int size = que.size();
    // level++;
    // while(size > 0){
    // int r = que.remove();
    // for(int node : tree.get(r)){
    // if(visited[node] == 0){
    // visited[node] = 1;
    // que.add(node);
    // dist += level;
    // }
    // }
    // size--;
    // }

    // }
    // return dist;
    // }

    private void preDFS(int root, int parent) {
        for (int i : tree.get(root)) {
            if (i != parent) {
                res[i] = res[root] - count[i] + count.length - count[i];
                preDFS(i, root);
            }
        }
    }

    /** Leetcode 199 : Binary Tree Right Side View */
    public List<Integer> rightSideView(TreeNode root) {

        if (root == null)
            return new ArrayList<Integer>();

        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> que = new LinkedList<TreeNode>();

        que.add(root);

        while (que.size() != 0) {
            int size = que.size();
            while (size > 0) {
                TreeNode rn = que.remove();
                if (size == 1)
                    list.add(rn.val);
                if (rn.left != null)
                    que.add(rn.left);
                if (rn.right != null)
                    que.add(rn.right);
                size--;
            }
        }

        return list;
    }

    /** GFG: Left View of Binary Tree */

    public ArrayList<Integer> leftView(Node root) {
        // Your code here
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (root == null)
            return ans;

        Queue<Node> que = new LinkedList<Node>();

        que.add(root);

        while (que.size() != 0) {

            int size = que.size();

            ans.add(que.peek().data);

            while (size > 0) {
                Node rn = que.remove();

                if (rn.left != null)
                    que.add(rn.left);
                if (rn.right != null)
                    que.add(rn.right);

                size--;
            }
        }

        return ans;
    }

    /** GFG: Top View */

    class pair {
        Node node;
        int level;

        pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    void horizontal(Node node, int[] minMax, int level) {

        if (node == null)
            return;
        minMax[0] = Math.min(minMax[0], level);
        minMax[1] = Math.max(minMax[1], level);

        horizontal(node.left, minMax, level - 1);
        horizontal(node.right, minMax, level + 1);
    }

    // Function to return a list of nodes visible from the top view
    // from left to right in Binary Tree.
    ArrayList<Integer> topView(Node root) {
        // add your code

        // if(root == null) return new ArrayList<Integer>();
        int[] minMax = new int[2];
        horizontal(root, minMax, 0);

        int width = minMax[1] - minMax[0] + 1;
        Node[] levels = new Node[width];

        Queue<pair> que = new LinkedList<pair>();

        que.add(new pair(root, -minMax[0]));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                pair rp = que.remove();
                if (levels[rp.level] == null) {
                    levels[rp.level] = rp.node;
                }
                if (rp.node.left != null)
                    que.add(new pair(rp.node.left, rp.level - 1));
                if (rp.node.right != null)
                    que.add(new pair(rp.node.right, rp.level + 1));
            }
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (Node n : levels) {
            ans.add(n.data);
        }

        return ans;
    }

    /** GFG: Bottom View */

    public ArrayList<Integer> bottomView(Node root) {
        // Code here

        int[] minMax = new int[2];
        horizontal(root, minMax, 0);

        int width = minMax[1] - minMax[0] + 1;
        Node[] levels = new Node[width];

        Queue<pair> que = new LinkedList<pair>();

        que.add(new pair(root, -minMax[0]));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                pair rp = que.remove();
                levels[rp.level] = rp.node;

                if (rp.node.left != null)
                    que.add(new pair(rp.node.left, rp.level - 1));
                if (rp.node.right != null)
                    que.add(new pair(rp.node.right, rp.level + 1));
            }
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (Node n : levels) {
            ans.add(n.data);
        }

        return ans;

    }

    /** GFG: Reverse Level Order */

    void reverseLevelOrder(Node node) {
        Stack<Node> S = new Stack<>();
        Queue<Node> Q = new LinkedList<>();
        Q.add(node);

        while (Q.isEmpty() == false) {

            node = Q.peek();
            Q.remove();
            S.push(node);

            if (node.right != null)
                Q.add(node.right);

            if (node.left != null)
                Q.add(node.left);
        }
        while (S.empty() == false) {
            node = S.peek();
            System.out.print(node.data + " ");
            S.pop();
        }
    }

    /** Leetcode 987 : Vertical Order Traversal of a Binary Tree */

    int min = 0, max = 0;
    Map<Integer, List<Integer>> map = new HashMap<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> qt = new LinkedList<>();
        Queue<Integer> qi = new LinkedList<>();
        qt.add(root);
        qi.add(0);// not root.val
        while (!qt.isEmpty()) {
            int size = qt.size();
            Map<Integer, List<Integer>> tmp = new HashMap<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = qt.poll();
                int idx = qi.poll();
                if (!tmp.containsKey(idx))
                    tmp.put(idx, new ArrayList<Integer>());
                tmp.get(idx).add(cur.val);
                if (idx < min)
                    min = idx;
                if (idx > max)
                    max = idx;
                if (cur.left != null) {
                    qt.add(cur.left);
                    qi.add(idx - 1);
                }
                if (cur.right != null) {
                    qt.add(cur.right);
                    qi.add(idx + 1);
                }
            }
            for (int key : tmp.keySet()) {
                if (!map.containsKey(key))
                    map.put(key, new ArrayList<Integer>());
                List<Integer> list = tmp.get(key);
                Collections.sort(list);
                map.get(key).addAll(list);
            }

        }
        for (int i = min; i <= max; i++) {
            List<Integer> list = map.get(i);
            res.add(list);
        }
        return res;
    }

    // Using comaparable :
    // https://massivealgorithms.blogspot.com/2019/02/leetcode-987-vertical-order-traversal.html

    /** GFG: Diagonal View */

    void diagonalPrintUtil(Node root, int d, HashMap<Integer, Vector<Integer>> diagonalPrint) {

        if (root == null)
            return;
        Vector<Integer> k = diagonalPrint.get(d);
        if (k == null) {
            k = new Vector<>();
            k.add(root.data);
        }

        else {
            k.add(root.data);
        }

        diagonalPrint.put(d, k);

        diagonalPrintUtil(root.left, d + 1, diagonalPrint);
        diagonalPrintUtil(root.right, d, diagonalPrint);
    }

    // void diagonalPrint(Node root) {

    // HashMap<Integer, Vector<Integer>> diagonalPrint = new HashMap<>();
    // diagonalPrintUtil(root, 0, diagonalPrint);
    // for (Entry<Integer, Vector<Integer>> entry : diagonalPrint.entrySet()) {
    // System.out.println(entry.getValue());
    // }
    // }

    /** GFG: Boundary Traversal */

    ArrayList<Integer> ans = new ArrayList<>();

    void addLeft(Node node) {
        if (node == null)
            return;

        if (node.left != null) {
            ans.add(node.data);
            addLeft(node.left);
        } else if (node.right != null) {
            ans.add(node.data);
            addLeft(node.right);
        }
    }

    void addLeaves(Node node) {
        if (node == null)
            return;

        addLeaves(node.left);
        if (node.left == null && node.right == null) {
            ans.add(node.data);
        }
        addLeaves(node.right);
    }

    void addRight(Node node) {
        if (node == null)
            return;

        if (node.right != null) {
            addRight(node.right);
            ans.add(node.data);

        } else if (node.left != null) {
            addRight(node.left);
            ans.add(node.data);

        }
    }

    ArrayList<Integer> printBoundary(Node node) {

        if (node == null)
            return ans;

        ans.add(node.data);

        addLeft(node.left);
        addLeaves(node);
        addRight(node.right);

        return ans;
    }

    /** Leetcode 1145 : Binary Tree Coloring Game */

    boolean win = false;

    private int winning(TreeNode root, int n, int x) {
        if (root == null)
            return 0;

        int left = winning(root.left, n, x);
        int right = winning(root.right, n, x);

        if (root.val == x) {
            if (left > n / 2 || right > n / 2 || (n - left - right - 1) > n / 2)
                win = true;
        }

        return left + right + 1;
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        winning(root, n, x);
        return win;
    }

    /** GFG : Image Multiplication */

    long mod = (long) 1e9 + 7;
    long mul = 0;

    public void multiply(Node l, Node r) {

        if (l == null || r == null)
            return;

        mul += ((l.data % mod) * (r.data % mod)) % mod;

        multiply(l.left, r.right);
        multiply(l.right, r.left);
    }

    public long imgMultiply(Node root) {
        // code here
        if (root.left != null && root.right != null) {
            multiply(root.left, root.right);
        }

        mul += ((root.data % mod) * (root.data % mod)) % mod;

        return mul % mod;

    }

    /** Leetcode Inorder Succesor */

    public static Node findMinimum(Node root) {
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Recursive
    public static Node findSuccessor(Node root, Node succ, int key) { // Time : O(h)
        if (root == null) {
            return null;
        }
        if (root.data == key) {
            if (root.right != null) {
                return findMinimum(root.right);
            }
        } else if (key < root.data) {
            succ = root;
            return findSuccessor(root.left, succ, key);
        } else {
            return findSuccessor(root.right, succ, key);
        }

        return succ;
    }

    // Iterative
    public static Node findSuccessor(Node root, int key) { // Time : O(h)
        Node succ = null;

        while (true) {
            if (key < root.data) {
                succ = root;
                root = root.left;
            } else if (key > root.data) {
                root = root.right;
            } else {
                if (root.right != null) {
                    succ = findMinimum(root.right);
                }
                break;
            }

            // if the key doesn't exist in the binary tree
            if (root == null) {
                return null;
            }
        }
        return succ;
    }

    /** Leetcode 1339: Maximum Product of Splitted Binary Tree */

    long ans1 = Long.MIN_VALUE;
    long mod1 = (long) 1e9 + 7;

    public void ansFunc(TreeNode node, HashMap<TreeNode, Long> map, long total) {
        if (node == null)
            return;

        if (node.left != null) {
            ans1 = Math.max(ans1, map.get(node.left) * (total - map.get(node.left)));
        }
        if (node.right != null) {
            ans1 = Math.max(ans1, map.get(node.right) * (total - map.get(node.right)));
        }

        ansFunc(node.left, map, total);
        ansFunc(node.right, map, total);
    }

    public void count(TreeNode node, HashMap<TreeNode, Long> map) {
        if (node == null)
            return;

        count(node.left, map);
        count(node.right, map);

        if (!map.containsKey(node))
            map.put(node, (long) node.val);

        long left = 0;
        long right = 0;
        if (node.left != null)
            left = map.get(node.left);
        if (node.right != null)
            right = map.get(node.right);

        map.put(node, left + right + (long) node.val);

    }

    public int maxProduct(TreeNode root) { // Space : O(N) and Time : O(N)

        HashMap<TreeNode, Long> map = new HashMap<>();
        count(root, map);
        // for(TreeNode key: map.keySet()){
        // System.out.println(key.val + "->" + map.get(key));
        // }
        ansFunc(root, map, map.get(root));

        return (int) (ans1 % mod1);
    }

    // Constant Space
    long max2 = 0;

    public int maxProduct_2(TreeNode root) { // Space : O(1) and Time : O(N)
        add(root);
        compute(root, root.val);
        return (int) (max % (1e9 + 7));
    }

    public int add(TreeNode root) {
        if (root == null)
            return 0;
        root.val += add(root.left) + add(root.right);
        return root.val;
    }

    public void compute(TreeNode node, long sum) {
        if (node == null)
            return;
        max2 = Math.max(max2, (node.val * (sum - node.val)));

        compute(node.left, sum);
        compute(node.right, sum);

    }

    /** Leetcode 235 Lowest Common Ancestor of a Binary Search Tree */

    // Recursive
    public TreeNode lowestCommonAncestorBST_1(TreeNode root, TreeNode p, TreeNode q) {

        int parentVal = root.val;

        int pVal = p.val;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    // Iterative
    public TreeNode lowestCommonAncestor_BST2(TreeNode root, TreeNode p, TreeNode q) {

        int pVal = p.val;
        int qVal = q.val;

        TreeNode node = root;

        while (node != null) {

            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    /** Leetcode 236 Lowest Common Ancestor of a Binary Tree */

    private TreeNode LCA;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // Time : O(N) worst case

        // Terminate early if LCA was already found in another branch
        if (LCA != null)
            return null;

        if (root == null)
            return root;

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both nodes lie in the left or right then their LCA is in the left or right
        // Otherwise root is their LCA
        if (left != null && right != null) {
            LCA = root;
            return LCA;
        }
        ;

        return (left != null) ? left : right;
    }
}