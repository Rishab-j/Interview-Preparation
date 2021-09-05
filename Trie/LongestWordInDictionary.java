public class LongestWordInDictionary {
    class Node {
        Node[] nodes = new Node[26];
        String str;
    }

    private String ans = "";

    private void insert(String word, Node root) {

        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (curr.nodes[ch - 'a'] == null) {
                curr.nodes[ch - 'a'] = new Node();
            }

            curr = curr.nodes[ch - 'a'];
        }

        curr.str = word;
    }

    private void dfs(Node root) {

        Node curr = root;

        for (int i = 0; i < 26; i++) {
            if (curr.nodes[i] != null && curr.nodes[i].str != null) {
                if (curr.nodes[i].str.length() > ans.length()) {
                    ans = curr.nodes[i].str;
                }
                dfs(curr.nodes[i]);
            }
        }

    }

    public String longestWord(String[] words) {

        Node root = new Node();

        for (String word : words)
            insert(word, root);

        dfs(root);

        return ans;
    }
}
