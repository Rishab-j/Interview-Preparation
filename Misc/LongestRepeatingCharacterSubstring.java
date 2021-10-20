/**
 * LongestRepeatingCharacterSubstring
 */
public class LongestRepeatingCharacterSubstring {

    class Solution {
        public int characterReplacement(String str, int k) {
            
            int[] counts = new int[26];
            
            char[] s = str.toCharArray();
            
            int start = 0;
            int maxCharCount = 0;
            int n = s.length;
            
            int result = 0;
            
            for(int end = 0; end < n; end++){
                
                counts[s[end]-'A']++;
                
                if(maxCharCount < counts[s[end]-'A']){
                    maxCharCount = counts[s[end]-'A'];
                }
                
                while(end-start-maxCharCount+1 > k){
                    counts[s[start]-'A']--;
                    start++;
                    for(int i = 0; i < 26; i++){
                        if(maxCharCount < counts[i]){
                            maxCharCount = counts[i];
                        }
                    }
                }
                
                result = Math.max(result, end-start+1);
            }
            
            return result;
        }
    }
}