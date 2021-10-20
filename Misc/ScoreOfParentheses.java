import java.util.*;

public class ScoreOfParentheses {
    public int scoreOfParentheses_0(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // The score of the current frame
    
        for (char c: S.toCharArray()) {
            if (c == '(')
                stack.push(0);
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }
    
        return stack.pop();
    }

    public int scoreOfParentheses(String S) {
        int bal = 0, ans = 0;
        
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i-1) == '(')
                    ans += 1 << bal;
            }
        }
        
        return ans;
    }
}
