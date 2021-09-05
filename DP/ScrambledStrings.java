import java.util.HashMap;

public class ScrambledStrings {
    HashMap<String, Boolean> dp = new HashMap<>();
    
    public boolean isScramble(String s1, String s2) {
        
        if(s1.equals(s2)){
            dp.put(s1 + s2, true);
            return true;
        }
        
        String key = s1 + s2;
        
        if(dp.containsKey(key)){
            return dp.get(key);
        }
        
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for(int i = 0; i < s1.length(); i++){
            freq1[s1.charAt(i) - 'a']++;
        }
        
        for(int i = 0; i < s2.length(); i++){
            freq2[s2.charAt(i) - 'a']++;
        }
        
        for(int i = 0; i < 26; i++){
            if(freq1[i] != freq2[i]){
                dp.put(key, false);
                return false;
            }
        }
        
        
        for(int cut = 1; cut < s1.length(); cut++){
            
            // as it is
            boolean temp1 = isScramble(s1.substring(0, cut), s2.substring(0, cut));
            boolean temp2 = isScramble(s1.substring(cut), s2.substring(cut));
            
            if(temp1 && temp2){
                dp.put(key, true);
                return true;
            }
            
            // swap
            boolean temp3 = isScramble(s1.substring(0, cut), s2.substring(s2.length() - cut));
            boolean temp4 = isScramble(s1.substring(cut), s2.substring(0, s2.length() - cut));
            
            if(temp3 && temp4){
                dp.put(key, true);
                return true;
            }
        }
        
        dp.put(key, false);
        return false;
    }
}
