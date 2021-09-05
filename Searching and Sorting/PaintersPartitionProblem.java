import java.util.ArrayList;

public class PaintersPartitionProblem {
    public int paint(int A, int B, ArrayList<Integer> C) {

        long low = Integer.MIN_VALUE;
        long high = 0;

        for(int c: C){
            low = Math.max(low, c);
            high += c;
        }

        long ans = high;
        while(low <= high){
            long length = low + (high - low) / 2;

            int painters = 0;
            int i = 0;

            long max = Integer.MIN_VALUE;
            while(i < C.size()){
                int sum = 0;
                while(i < C.size() && sum + C.get(i) <= length){
                    sum = sum + C.get(i);
                    i++;
                }
                max = Math.max(max, sum);
                painters++;
            }

            if(painters <= A){
                ans = Math.min(max, ans);
                high = length - 1;
            }else{
                low = length + 1;
            }
        }


        return (int)(ans % 10000003 * B % 10000003) % 10000003;
    }
}
