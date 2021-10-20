import java.util.*;

public class SumOfSubArrayMin {
    class Solution {
        public int sumSubarrayMins(int[] arr) {
            long sum = 0, mod = (int) 1e9 + 7;

            Stack<Integer> stack = new Stack<Integer>();
            stack.push(-1);

            int n = arr.length;
            // int sum = 0;
            int i = 0;

            while (i < n) {

                while (stack.peek() != -1 && arr[stack.peek()] >= arr[i]) {

                    int num = arr[stack.peek()];

                    int right = i - stack.peek();

                    int idx = stack.pop();

                    int left = idx - stack.peek();

                    long toAdd = ((left % mod) * (right % mod)) % mod;

                    toAdd = ((toAdd % mod) * (num % mod)) % mod;

                    sum = (sum + toAdd) % mod;
                }

                stack.push(i);

                i++;
            }

            while (stack.peek() != -1) {
                int num = arr[stack.peek()];

                int right = n - stack.peek();

                int idx = stack.pop();

                int left = idx - stack.peek();

                long toAdd = ((left % mod) * (right % mod)) % mod;

                toAdd = ((toAdd % mod) * (num % mod)) % mod;

                sum = (sum + toAdd) % mod;
            }

            return (int) sum;
        }
    }
}
