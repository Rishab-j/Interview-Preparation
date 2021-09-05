public class MaxXORofTwoNumbers {
    class Trie {

        class Node {
            Node zero;
            Node one;

            boolean isEnd;
        }

        Node root;

        Trie() {
            this.root = new Node();
        }

        void insert(int n) {
            Node curr = root;

            for (int i = 30; i >= 0; i--) {
                int a = 1 << i; // mask
                if ((a & n) != 0) {
                    if (curr.one == null)
                        curr.one = new Node();
                    curr = curr.one;
                } else {
                    if (curr.zero == null)
                        curr.zero = new Node();
                    curr = curr.zero;
                }
            }

            curr.isEnd = true;
        }

        int query(int n) {

            Node curr = root;
            int ans = 0;

            for (int i = 30; i >= 0; i--) {
                int a = 1 << i;

                if ((a & n) > 0) {
                    if (curr.one != null) {
                        ans |= a;
                        curr = curr.one;
                    } else {
                        ans |= 0;
                        curr = curr.zero;
                    }
                } else {
                    if (curr.zero != null) {
                        ans |= 0;
                        curr = curr.zero;
                    } else {
                        ans |= a;
                        curr = curr.one;
                    }
                }

            }

            return ans;
        }

    }

    public int findMaximumXOR(int[] nums) {

        int max = Integer.MIN_VALUE;

        Trie trie = new Trie();

        for (int num : nums) {
            trie.insert(num);
        }

        for (int num : nums) {
            int comp = ~num;
            int ans = trie.query(comp);

            max = Math.max(max, num ^ ans);
        }

        return max;
    }
}
