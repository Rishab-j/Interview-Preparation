/**
 * ReorganiseString
 */
import java.util.*;

public class ReorganiseString {

    class Solution {
    
        class Pair{
            char c;
            int f;
            
            Pair(char c, int f){
                this.c = c;
                this.f = f;
            }
        }
        
        public String reorganizeString(String s) {
            if(s.length() <= 1) return s;
            
            int n = s.length();
            
            int[] freq = new int[26];
            
            for(int i = 0; i < n; i++){
                freq[s.charAt(i) - 'a']++;
            }
            
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
               return b.f - a.f; 
            });
            
            for(int i = 0; i < 26; i++){
                if(freq[i] > 0){
                    pq.add(new Pair((char) (i + 'a'), freq[i]));
                }
            }
            
            Pair prev = null;
            StringBuilder sb = new StringBuilder();
            
            while(pq.size() != 0){
                
                Pair r = pq.remove();
                
                if(pq.size() == 0 && prev == null && r.f > 1) return ""; 
                
                sb.append(r.c);
                r.f--;
                
                if(prev != null) pq.add(prev);
                
                if(prev == null && r.f != 0){
                    prev = r;
                }else if(r.f != 0){
                    prev = r;
                }else{
                    prev = null;
                }
                
            }
            
            return sb.toString();
        }
    }
}