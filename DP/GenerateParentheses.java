import java.util.*;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n){
        
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));
        
        for (int i = 1; i <= n; ++i){
            List<String> list = new ArrayList<>();
            
            for (int j = 0; j < i; ++j){
                for (String first : lists.get(j)){
                    for (String second : lists.get(i - 1 - j)){
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            
            lists.add(list);
        }
        
        return lists.get(lists.size() - 1);
    }
}
