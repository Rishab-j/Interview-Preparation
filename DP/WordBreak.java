import java.util.*;

/**
 * WordBreak
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        
        int n = s.length();
        
        if(s == null || s.length() == 0) return true;
        
        Set<String> set = new HashSet<>();
        
        int maxLen = 0;
        for(String word : wordDict){
            set.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = i; i - j + 1 <= maxLen && j >= 1; j--){
                if(set.contains(s.substring(j - 1, i))){
                    dp[i] += dp[j - 1];
                }
            }
        }
        
        return dp[n] > 0;
    }

    // Using BFS

    public boolean wordBreak_2(String s, List<String> wordDict) {
        
        Set<String> set = new HashSet<String>();
        for(String word: wordDict){
            set.add(word);
        }         

        Queue<String> queue = new LinkedList<String>();
        queue.offer(s);

        Set<String> visited = new HashSet<String>();

        while(!queue.isEmpty()){
            String candidate = queue.poll();

            if(set.contains(candidate)) return true;

            for(int i = 0; i < candidate.length(); i++){
                String chop = candidate.substring(0,i);
                String next = candidate.substring(i, candidate.length());
                if(!visited.contains(next) && set.contains(chop)){
                    // next = candidate.substring(i, candidate.length());
                    queue.offer(next);
                    visited.add(next);
                }
            }
            
        }

        return false;
}
}