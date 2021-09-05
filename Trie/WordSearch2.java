import java.util.*;

public class WordSearch2 {

    class Node{
        Node[] nodes;
        int count;
        String str;
        
        Node(){
            nodes = new Node[26];
        }
    }
    
    private void insert(String word, Node root){
        Node curr = root;
        
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            
            if(curr.nodes[ch - 'a'] == null){
                curr.nodes[ch - 'a'] = new Node();
                curr.count++;
            }
            
            curr = curr.nodes[ch - 'a'];
        }
        
        curr.str = word;
    }
    
    private void dfs(char[][] board, boolean[][] vis, Node curr, int i, int j, ArrayList<String> ans){
        
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || vis[i][j] || curr.count == 0){
            return;
        }
        
        Node child = curr.nodes[board[i][j] - 'a'];
        
        if(child == null) return;
        
        if(child.str != null){
            ans.add(child.str);
            child.str = null;
        }
        
        vis[i][j] =  true;
        
        dfs(board, vis, child, i + 1, j, ans);
        dfs(board, vis, child, i, j + 1, ans);
        dfs(board, vis, child, i - 1, j, ans);
        dfs(board, vis, child, i, j - 1, ans);
        
        if(child.count == 0){
            curr.count--;
        }
        
        vis[i][j] =  false;
        
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        
        Node root = new Node();
        
        for(String word: words) insert(word, root);
        
        int n = board.length;
        int m = board[0].length;
        
        boolean[][] vis = new boolean[n][m];
        
        ArrayList<String> ans = new ArrayList<String>();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dfs(board, vis, root, i, j, ans);
            }
        }
        
        
        return ans;
    }
}
