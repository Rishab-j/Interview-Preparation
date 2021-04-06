import java.util.*;

public class testing {
    

    public static int func(String str){
        
        if(str.length() % 2 != 0){
            return -1;
        }
        
        
        int cb = 0;
        int ob = 0;
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < str.length()){
            
            char ch = str.charAt(i);
            if(ch == '{') ob++;
            else cb++;
            
            if(stack.size() == 0){
                stack.push(ch);
            }else if(stack.peek() == '{' && ch == '}'){
                stack.pop();
                ob--;
                cb--;
            }else{
                stack.push(ch);
            }
            
            i++;
        }
        
        if(ob%2 == 0){
            return ob/2 + cb/2;
        }else{
            return ((ob-1)/2) + ((cb-1)/2) + 2;
        }
    }
    
    
	public static void main (String[] args) {
		//code
		// Scanner scn = new Scanner(System.in);
		int t = 1;
		
		while(t-- > 0){
		    String str = "}{{}}{{{{{}}{{";
		    System.out.println(func(str));
		}
	}
}
