import java.util.*;

public class ConcatenatedWords {
    class Solution {
    
        class Node{
            Node[] nodes;
            String str;
            // boolean isAdded;
            
            Node(){
                nodes = new Node[26];
            }
        }
        
        class Trie{
            Node root;
            
            Trie(){
                root = new Node();
            }
            
            void insert(String word){
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
            
            void search(Node curr){
                
                if(curr.str != null){
                    search(curr, root);
                }
                
                for(Node child: curr.nodes){
                    if(child != null){
                        search(child);
                    }
                }
            }
            
            
            void search(Node curr, Node newWord){
                if(curr.str != null && newWord.str != null){
                    ans.add(curr.str);
                    curr.str = null;
                }
                
                if(newWord.str != null){
                    search(curr, root);
                }
                
                for(int i = 0; i < 26; i++){
                    if(curr.nodes[i] != null && newWord.nodes[i] != null){
                        search(curr.nodes[i], newWord.nodes[i]);
                    }
                }
            }
        }
        
        public List<String> ans;
        
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            
            Trie trie = new Trie();
            
            ans = new ArrayList<>();
            
            if(words.length <= 1) return ans;
            
            for(String word: words){
                trie.insert(word);
            }
            
            trie.search(trie.root);
            
            return ans;
        }
    }

}
