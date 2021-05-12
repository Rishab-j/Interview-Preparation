import java.util.*;

public class Main {

    public static int steps(int[] piles){
        int res = 0;

        int n = piles.length;

        Arrays.sort(piles);

        for(int i = n - 2; i >= 0; i--){
            if(piles[i] != piles[i+1]){
                res += n - i -1;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr = {5,2,1};
        System.out.println(steps(arr));
    }
    
}
