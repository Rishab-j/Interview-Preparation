import java.util.*;

public class UniqueBST2 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        return solve(1, n);
    }

    private List<TreeNode> solve(int start, int end) {

        List<TreeNode> list = new ArrayList<>();

        if (start > end) {
            list.add(null);
            return list;
        }

        if (start == end) {

            list.add(new TreeNode(start));
            return list;

        }

        List<TreeNode> left, right;

        for (int i = start; i <= end; i++) {

            left = solve(start, i - 1);
            right = solve(i + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {

                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;

                    list.add(node);
                }
            }

        }

        return list;
    }
}
