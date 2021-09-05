import java.util.*;

public class NumberOfValidWordsInPuzzle {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        
        for(char c = 'a'; c <= 'z'; c++){
            map.put(c, new ArrayList<Integer>());
        }
        
        for(String word: words){
            int mask = 0;
            for(char ch: word.toCharArray()){
                int c = ch - 'a';
                mask = mask | (1 << c);
            }
            
            HashSet<Character> unique = new HashSet<>();
            for(char ch: word.toCharArray()){
                
                if(unique.contains(ch)) continue;
                
                unique.add(ch);
                map.get(ch).add(mask);
            }
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(String puzzle: puzzles){
            int mask = 0;
            for(char ch: puzzle.toCharArray()){
                int c = ch - 'a';
                mask = mask | (1 << c);
            }
            
            int count = 0;
            
            for(int a: map.get(puzzle.charAt(0))){
                if((a & mask) == a){
                    count++;
                }
            }
            
            list.add(count);
        }
        
        
        return list;
        
    }
}
