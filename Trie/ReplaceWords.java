import java.util.*;

public class ReplaceWords{
    class Solution {
    
        class Node{
            
            Node[] nodes = new Node[26];
            String str;
            
        }
        
        private void insert(String word, Node root){
            Node curr = root;
            
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                
                if(curr.nodes[ch - 'a'] == null){
                    curr.nodes[ch - 'a'] = new Node();
                }
                
                curr = curr.nodes[ch - 'a'];
                
                if(curr.str != null) return;
            }
            
            curr.str = word;
        }
        
        private String find(Node root, String word){
            
            Node curr = root;
            
            for(int i = 0; i < word.length(); i++){
                
                char ch = word.charAt(i);
                
                if(curr.nodes[ch - 'a'] == null) return null;
                
                curr = curr.nodes[ch - 'a'];
                
                if(curr.str != null) return curr.str;
            }
            
            return null;
        }
        
        public String replaceWords(List<String> dictionary, String sentence) {
            Node root = new Node();
            
            for(String word: dictionary){
                insert(word, root);
            }
            
            String[] str = sentence.split(" ");
            
            StringBuilder sb = new StringBuilder();
            
            for(String s: str){
                String add = find(root, s);
                
                if(add == null){
                    sb.append(s);
                }else{
                    sb.append(add);
                }
                
                sb.append(" ");
            }
            
            sb.deleteCharAt(sb.length() - 1);
            
            return sb.toString();
        }
    }
}
