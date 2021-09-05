/**
 * Keypad
 */

import java.util.*;

public class Keypad {

    class Solution {
    
        //     String[] words = {"", "", "abc", "def", "ghi", "jkl", "mno",
        //                             "pqrs", "tuv", "wxyz"};
            
        //     List<String> combinations = new ArrayList<String>();
            
            public List<String> letterCombinations(String digits) {
                
        //         if(digits.length() == 0) return combinations;
                
        //         solve(digits, 0, new StringBuilder());
                
        //         return combinations;
                
                LinkedList<String> ans = new LinkedList<String>();
                
                if(digits.isEmpty()) return ans;
                
                String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
                
                ans.add("");
                
                for(int i =0; i<digits.length();i++){
                    int x = Character.getNumericValue(digits.charAt(i));
                    while(ans.peek().length()==i){
                        String t = ans.remove();
                        for(char s : mapping[x].toCharArray())
                            ans.add(t+s);
                    }
                }
                
                return ans;
            }
            
        //     private void solve(String digits, int i, StringBuilder w){
                
        //         if(i == digits.length()){
        //             combinations.add(w.toString());
        //             return ;
        //         }
                
        //         int digit = digits.charAt(i) - '0';
                
        //         String word = words[digit];
                
        //         for(int j = 0; j < word.length(); j++){
        //             w.append(word.charAt(j));
        //             solve(digits, i + 1, w);
        //             w.deleteCharAt(w.length() - 1);
        //         }
                
        //     }
        }
}