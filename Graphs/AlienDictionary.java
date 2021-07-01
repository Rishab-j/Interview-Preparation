import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {

        if(words == null || words.length == 0) 
            return "";
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree = new HashMap<Character, Integer>();
        StringBuilder sb = new StringBuilder();

        // put all word in-degree 0
        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }

        // compare each word and its pre-word char by char, 
        // if different, since c1 is in front of c2, put c2 into c1's next set, c2 in-dgree + 1
        for(int i=0; i < words.length-1; i++){
            String cur = words[i];
            String next = words[i+1];
            // using longer one
            int length = Math.min(cur.length(), next.length());
            for(int j=0; j<length; j++){
                char c1=cur.charAt(j);
                char c2=next.charAt(j);
                if(c1!=c2){
                    Set<Character> set = map.getOrDefault(c1, new HashSet());
                    if(!set.contains(c2)){
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        // topological sort via BFS
        Queue<Character> q = new LinkedList();
        // put all 0 in-degree into queue
        for(char c: degree.keySet()){
            if(degree.get(c) == 0) q.add(c);
        }
        while(!q.isEmpty()){
            char c = q.poll();
            sb.append(c);
            if(map.containsKey(c)){
                for(char c2: map.get(c)){
                    // all next chars' in-degree abstract 1
                    degree.put(c2,degree.get(c2) - 1);
                    if(degree.get(c2) == 0) q.add(c2);
                }
            }
        }
        if(sb.length() != degree.size()) return "";

        return sb.toString();
    }
}
