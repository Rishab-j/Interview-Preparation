import java.util.*;

class questions {

    // Next Greater Element On Right

    public static long[] nextLargerElement(long[] arr, int n) {
        
        long[] ans = new long[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        stack.push(0);
        for (int i = 1; i < n; i++) {
            while (stack.peek() != -1 && arr[i] > arr[stack.peek()]) {
                ans[stack.peek()] = arr[i];
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }

    // Leetcode 503 Next Greater Element II

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && nums[i] > nums[st.peek()]) {
                ans[st.peek()] = nums[i];
                st.pop();
            }
            st.push(i);
        }

        for (int i = 0; i < n - 1; i++) {
            while (st.peek() != -1 && nums[i] > nums[st.peek()]) {
                ans[st.peek()] = nums[i];
                st.pop();
            }
        }

        return ans;
    }

    public int[] nextGreaterElements_2(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);
        st.push(-1);
        int n = arr.length;
        for (int i = 0; i < (n * 2) - 1; i++) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i % n]) {
                ans[st.peek()] = arr[i % n];
                st.pop();
            }
            st.push(i % n);
        }
        return ans;
    }

    // Leetcode 739 Daily Temperatures

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && T[i] > T[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }

    // GFG: Stock Span Problem
    public static int[] calculateSpan(int arr[], int n) {

        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = n - 1; i >= 0; i--) {
            while (stack.peek() != -1 && arr[stack.peek()] < arr[i]) {
                ans[stack.peek()] = stack.peek() - i;
                stack.pop();
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            ans[stack.peek()] = stack.peek() + 1;
            stack.pop();
        }

        return ans;
    }

    // GFG : Find maximum difference between nearest left and right smaller elements

    public int findMaxDiff(int arr[], int n){

        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        // stack.push(-1);
        
        for(int i = 0; i < n; i++){
            while(stack.size() > 1 && stack.peek() >= arr[i]){
                int prev = stack.pop();
                if(prev != arr[i]){
                    ans = Math.max(ans,Math.abs(arr[i] - stack.peek()));
                }
            }
            stack.push(arr[i]);
        }
        
        while(stack.size() > 1){
            stack.pop();
            ans = Math.max(ans,Math.abs(stack.peek()));
        }
        
        return ans;
    	
    }

    // Leetcode 84 Largest Rectangle in Histogram

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        int max = 0;
        for(int i = 0; i < n ; i++){
            while(stack.peek()!=-1 && heights[stack.peek()] >= heights[i]){
                int h = heights[stack.peek()];
                stack.pop();
                int w = (i - stack.peek() - 1);
                max = Math.max(max,h * w);
                
            }
            stack.push(i);
        }
        
        while(stack.peek() != -1){
            int h = heights[stack.peek()];
            stack.pop();
            int w = n - stack.peek() - 1;
            max = Math.max(max,h*w);
        }
        return max;
    }


    // Leetcode 85 Maximal Rectangle
    
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 ) return 0;
        int n = matrix.length;
        int ans = 0;
        int[] heights = new int[matrix[0].length];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < heights.length; j++){
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] +1;
            }
            ans = Math.max(ans,largestRectangleArea(heights));
        }
        return ans;
    }

    // Leetcode 735 Asteroid Collision

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<Integer>();
        for(int ele : asteroids){
            if(ele > 0) st.push(ele);
            else{
                while(st.size() != 0 && st.peek() > 0 && st.peek() <= -ele) 
                    st.pop();
                
                if(st.size() != 0 && st.peek() == -ele)
                    st.pop();
                else if(st.size() == 0 || st.peek() < 0)
                    st.push(ele);
                else if(st.size() != 0 && st.peek() > -ele){
                    // do nothing
                }
            }
        }
        
        int[] ans = new int[st.size()];
        int i = ans.length-1;
        while(st.size()!=0){
            ans[i--] = st.peek();
            st.pop();
        }
        
        return ans;
    }



    public int[] asteroidCollision_2(int[] arr) {  // SIMPLER CODE
        Stack<Integer> stack = new Stack<>();
        
        int j = 0;
        while(j < arr.length){
            if(stack.size() == 0 || arr[j] > 0 || stack.peek() < 0){
                stack.push(arr[j]);
                j++;
            }else if(-arr[j] < stack.peek()){
                j++;
            }else if(-arr[j] >= stack.peek()){
                if(-arr[j] > stack.peek()){
                    stack.pop();
                }else{
                    stack.pop();
                    j++;
                }
            }
        }
        
        int[] ans = new int[stack.size()];
        
        for(int i = ans.length - 1; i >=0; i--){
            ans[i] = stack.pop();
        }
        
        return ans;
    }
}