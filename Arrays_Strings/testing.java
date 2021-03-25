import java.util.*;

class testing {
    
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static int firstMissingPositive(int[] arr) {  // {1,4,5,7,6,2}
        int n = arr.length;                              //  0,1,2,3,4,5
        int i = 0;
        
        while(i < n){
            int a = i + 1;
            // System.out.println(a);
            if(arr[i] != a && arr[i] < n && arr[arr[i]-1] != a && arr[i] > 0){
                swap(arr,i,arr[i]-1);
            }else{
                i++;
            }
        }

        for(int j = 0; j < n; j++){
            System.out.print(arr[j] + " ");
        }

        System.out.println();
        
        for(int j = 0; j < n; j++){
            int b = j + 1;
            if(arr[j] != b) return b;
        }
        return n;
    }


    public static void main(String arg[]) {
        
        int[] arr = {1,4,5,7,6,2};
        System.out.println(firstMissingPositive(arr));

        
    }
}
// 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199. 