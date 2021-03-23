import java.util.*;

class testing {
    
    public static int maximumSwap(int num) {
        String s = Integer.toString(num);
        int[] arr = new int[s.length()];

        for(int i = 0; i < s.length(); i++){
            arr[i] = s.charAt(i) - '0';
        }

        for(int i = 0; i < s.length(); i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int n = arr.length;
        int[] position = new int[10];
        Arrays.fill(position,-1);
        boolean flag = false;
        
        for(int i = n - 1; i >= 0; i--){
            position[arr[i]] = i;
        }
        
        for(int i = 0; i < 10; i++){
            System.out.print(position[i] + " ");
        }
        System.out.println();
        
        for(int i = 0; i < n; i++){
            int j = 9;
            while(j > arr[i]){
                System.out.println("yes");
                System.out.println(i);
                if(position[j] != -1 && position[j] > i){
                    System.out.println(arr[position[j]] + " -> " + position[j]);
                    int temp = arr[i];
                    arr[i] = arr[position[j]];
                    arr[position[j]] = temp;
                    flag = true;
                    break;
                }
              j--;  
            }
            if(flag) break;
        }
        
        int ans = 0;
        int mul = 1;
        for(int i = n - 1; i >= 0; i--){
            ans += arr[i]*mul;
            mul *= 10;
        }
        return ans;
    }


    public static void main(String arg[]) {
        
        System.out.println(maximumSwap(19));
    }
}
// 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199. 