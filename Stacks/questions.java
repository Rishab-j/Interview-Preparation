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

    public int findMaxDiff(int arr[], int n) {

        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        // stack.push(-1);

        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && stack.peek() >= arr[i]) {
                int prev = stack.pop();
                if (prev != arr[i]) {
                    ans = Math.max(ans, Math.abs(arr[i] - stack.peek()));
                }
            }
            stack.push(arr[i]);
        }

        while (stack.size() > 1) {
            stack.pop();
            ans = Math.max(ans, Math.abs(stack.peek()));
        }

        return ans;

    }

    // Leetcode 84 Largest Rectangle in Histogram

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int max = 0;
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                int h = heights[stack.peek()];
                stack.pop();
                int w = (i - stack.peek() - 1);
                max = Math.max(max, h * w);

            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int h = heights[stack.peek()];
            stack.pop();
            int w = n - stack.peek() - 1;
            max = Math.max(max, h * w);
        }
        return max;
    }

    // Leetcode 85 Maximal Rectangle

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int n = matrix.length;
        int ans = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < heights.length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }
        return ans;
    }

    // Leetcode 735 Asteroid Collision

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<Integer>();
        for (int ele : asteroids) {
            if (ele > 0)
                st.push(ele);
            else {
                while (st.size() != 0 && st.peek() > 0 && st.peek() <= -ele)
                    st.pop();

                if (st.size() != 0 && st.peek() == -ele)
                    st.pop();
                else if (st.size() == 0 || st.peek() < 0)
                    st.push(ele);
                else if (st.size() != 0 && st.peek() > -ele) {
                    // do nothing
                }
            }
        }

        int[] ans = new int[st.size()];
        int i = ans.length - 1;
        while (st.size() != 0) {
            ans[i--] = st.peek();
            st.pop();
        }

        return ans;
    }

    public int[] asteroidCollision_2(int[] arr) { // SIMPLER CODE
        Stack<Integer> stack = new Stack<>();

        int j = 0;
        while (j < arr.length) {
            if (stack.size() == 0 || arr[j] > 0 || stack.peek() < 0) {
                stack.push(arr[j]);
                j++;
            } else if (-arr[j] < stack.peek()) {
                j++;
            } else if (-arr[j] >= stack.peek()) {
                if (-arr[j] > stack.peek()) {
                    stack.pop();
                } else {
                    stack.pop();
                    j++;
                }
            }
        }

        int[] ans = new int[stack.size()];

        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }

    // Leetcode 20 Valid Parentheses

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty())
                    return false;
                else if (!stack.isEmpty() && ch == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (!stack.isEmpty() && ch == '}' && stack.peek() == '{') {
                    stack.pop();
                } else if (!stack.isEmpty() && ch == ']' && stack.peek() == '[') {
                    stack.pop();
                }
            }
        }

        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    // GFG : Longest Valid Substring

    public int findMaxLen(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int ans = 0;

        int i = 0;
        while (i < S.length()) {
            if (stack.peek() != -1 && S.charAt(stack.peek()) == '(' && S.charAt(i) == ')') {
                stack.pop();
                ans = Math.max(ans, i - stack.peek());
            } else {
                stack.push(i);
            }
            i++;
        }

        return ans;
    }

    // GFG: Find if an expression has duplicate parenthesis or not

    public boolean findDuplicateparenthesis(String s) {
        // create a stack of characters
        Stack<Character> Stack = new Stack<>();

        // Iterate through the given expression
        char[] str = s.toCharArray();
        for (char ch : str) {
            // if current character is close parenthesis ')'
            if (ch == ')') {
                // pop character from the stack
                char top = Stack.peek();
                Stack.pop();

                // stores the number of characters between a
                // closing and opening parenthesis
                // if this count is less than or equal to 1
                // then the brackets are redundant else not
                int elementsInside = 0;
                while (top != '(') {
                    elementsInside++;
                    top = Stack.peek();
                    Stack.pop();
                }
                if (elementsInside < 1) {
                    return true;
                }
            } // push open parenthesis '(', operators and
              // operands to stack
            else {
                Stack.push(ch);
            }
        }

        // No duplicates found
        return false;
    }

    // GFG : Minimum number of bracket reversals needed to make an expression
    // balanced

    public int minReversal(String str) { // O(n) time and space

        if (str.length() % 2 != 0) {
            return -1;
        }

        int cb = 0;
        int ob = 0;
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < str.length()) {

            char ch = str.charAt(i);
            if (ch == '{')
                ob++;
            else
                cb++;

            if (stack.size() == 0) {
                stack.push(ch);
            } else if (stack.peek() == '{' && ch == '}') {
                stack.pop();
                ob--;
                cb--;
            } else {
                stack.push(ch);
            }

            i++;
        }

        if (ob % 2 == 0) {
            return ob / 2 + cb / 2;
        } else {
            return ((ob - 1) / 2) + ((cb - 1) / 2) + 2;
        }
    }

    public int countMinReversals(String expr) { // Constant Space
        int len = expr.length();

        if (len % 2 != 0)
            return -1;

        int ans = 0;
        int i;
        int open = 0;
        int close = 0;

        for (i = 0; i < len; i++) {
            if (expr.charAt(i) == '{')
                open++;
            else {
                if (open == 0)
                    close++;
                else
                    open--;
            }
        }

        ans = (close / 2) + (open / 2);
        close %= 2;
        open %= 2;
        if (close != 0)
            ans += 2;

        return ans;
    }

    // Leetcode 921 Minimum Add to Make Parentheses Valid

    public int minAddToMakeValid(String str) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (st.size() != 0 && st.peek() == '(' && ch == ')') {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        return st.size();
    }

    public int minAddToMakeValid_2(String str) { // Constant space
        int open = 0;
        int close = 0;

        int i = 0;
        while (i < str.length()) {
            char ch = str.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
            i++;
        }

        return open + close;
    }

    // Leetcode 402 Remove K digits

    public String removeKdigits(String nums, int k) {

        if (nums.length() == 0 || nums.length() == k) {
            return "0";
        }

        int size = nums.length();
        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int i = 0;
        while (i < size) {

            while (k > 0 && !stack.isEmpty() && stack.peek() < nums.charAt(i)) {
                stack.pop();
                k--;
            }

            stack.push(nums.charAt(i));
            i++;
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        while (stack.size() != 0) {
            ans.append(stack.pop());
        }

        ans.reverse();

        // first number = 0
        while (ans.length() > 1 && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }

        System.out.println(ans.length());

        return ans.toString();
    }
}