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
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
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

}