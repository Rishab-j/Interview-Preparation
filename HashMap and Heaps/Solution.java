

/* 
 * Enter your code here. Read input from STDIN. Print your output to STDOUT. 
 * Your class should be named CandidateCode.
*/

import java.io.*;
import java.util.*;
public class Solution{

    public static boolean check(String virus, String result){
        int n = virus.length();
        int m = result.length();

        // System.out.println(n + "," + m);

        int j = 0;
        for(int i = 0; i < n; i++){
            if(j < m && virus.charAt(i) == result.charAt(j))
                j++;
        }

        // System.out.println(j==m);
        return j==m;
    }

    public static void main(String args[] ) throws Exception {

    	//Write code here
        Scanner scn = new Scanner(System.in);
        String virus = scn.nextLine();
        int t = scn.nextInt();
        scn.nextLine();
        // System.out.println(t);

        while(t > 0){
            String result = scn.next();
            // scn.nextLine();
            if(check(virus,result)){
                System.out.println("POSITIVE");
            }else{
                System.out.println("NEGATIVE");
            }
            t--;
        }

   }
}
