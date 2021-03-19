import java.util.*;

public class testing {

    public static void ans(int[] arr){
        
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while(j < k){
            if(arr[j] == 1){
                j++;
            }else if(arr[j] == 2){
                swap(arr,j,k);
                k--;
            }else{
                swap(arr,i,j);
                i++;
                j++;
            }
        }

        for(int h = 0; h < arr.length; h++){
            System.out.print(arr[h] + " ");
        }

    }

    public static void swap(int[] arr, int s, int e){
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {0,1,2,1,0,0,1,2,2,1,0,0,1,2,2,2,0,1,0,2,2,2};
        ans(arr);
    }
    
}
