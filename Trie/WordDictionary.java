public class WordDictionary {

    private class Node {
        Node[] nodes;
        boolean isEnd;

        Node() {
            nodes = new Node[26];
        }
    }

    private class Trie {

        Node root;

        Trie() {
            root = new Node();
        }

        void insert(String word) {
            Node curr = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (curr.nodes[ch - 'a'] == null) {
                    curr.nodes[ch - 'a'] = new Node();
                }
                curr = curr.nodes[ch - 'a'];
            }

            curr.isEnd = true;
        }

        boolean search(String word, int i, Node curr) {

            if (i == word.length()) {
                return curr.isEnd;
            }

            char ch = word.charAt(i);

            boolean isFound = false;

            if (ch == '.') {
                for (int j = 0; j < 26; j++) {
                    Node node = curr.nodes[j];
                    if (!isFound)
                        if (node != null) {
                            if (search(word, i + 1, node)) {
                                isFound = true;
                                return true;
                            }
                        }
                }
                return false;
            } else {
                if (curr.nodes[ch - 'a'] == null)
                    return false;
                return search(word, i + 1, curr.nodes[ch - 'a']);
            }

            // return false;
        }

    }

    Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word, 0, trie.root);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary(); obj.addWord(word); boolean param_2
 * = obj.search(word);
 */
