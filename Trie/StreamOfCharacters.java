import java.util.*;

public class StreamOfCharacters {

    class StreamChecker {

        class Node {
            Node[] nodes = new Node[26];
            boolean isEnd;
        }

        private Node root;
        private ArrayList<Character> list;

        private void insert(String word) {

            Node curr = root;

            for (int i = word.length() - 1; i >= 0; i--) {
                if (curr.nodes[word.charAt(i) - 'a'] == null) {
                    curr.nodes[word.charAt(i) - 'a'] = new Node();
                }
                curr = curr.nodes[word.charAt(i) - 'a'];
            }

            curr.isEnd = true;
        }

        public StreamChecker(String[] words) {
            root = new Node();
            list = new ArrayList<>();

            for (String word : words)
                insert(word);
        }

        public boolean query(char letter) {
            Node curr = root;

            list.add(letter);

            int size = list.size();

            int j = size - 1;

            while (j >= 0) {
                char ch = list.get(j);

                curr = curr.nodes[ch - 'a'];

                if (curr == null)
                    return false;

                if (curr.isEnd)
                    return true;

                j--;
            }

            return false;
        }
    }
}
