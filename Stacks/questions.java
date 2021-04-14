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

    // GFG: First negative integer in every window of size k

    public void printFirstNegativeInteger(int arr[], int n, int k) { // O(nk)
        // flag to check whether window contains
        // a negative integer or not
        boolean flag;

        // Loop for each subarray(window) of size k
        for (int i = 0; i < (n - k + 1); i++) {
            flag = false;

            // traverse through the current window
            for (int j = 0; j < k; j++) {
                // if a negative integer is found, then
                // it is the first negative integer for
                // current window. Print it, set the flag
                // and break
                if (arr[i + j] < 0) {
                    System.out.print((arr[i + j]) + " ");
                    flag = true;
                    break;
                }
            }

            // if the current window does not
            // contain a negative integer
            if (!flag)
                System.out.print("0" + " ");
        }
    }

    public void printFirstNegativeInteger_2(int arr[], int n, int k) { // using Queue Time and Space: O(n)
        // A Double Ended Queue, Di that will
        // store indexes of useful array elements
        // for the current window of size k.
        // The useful elements are all negative integers.
        LinkedList<Integer> Di = new LinkedList<>();

        // Process first k (or first window)
        // elements of array
        int i;
        for (i = 0; i < k; i++)

            // Add current element at the rear of Di
            // if it is a negative integer
            if (arr[i] < 0)
                Di.add(i);

        // Process rest of the elements,
        // i.e., from arr[k] to arr[n-1]
        for (; i < n; i++) {
            // if Di is not empty then the element
            // at the front of the queue is the first
            // negative integer of the previous window
            if (!Di.isEmpty())
                System.out.print(arr[Di.peek()] + " ");

            // else the window does not have a
            // negative integer
            else
                System.out.print("0" + " ");

            // Remove the elements which are
            // out of this window
            while ((!Di.isEmpty()) && Di.peek() < (i - k + 1))
                Di.remove(); // Remove from front of queue

            // Add current element at the rear of Di
            // if it is a negative integer
            if (arr[i] < 0)
                Di.add(i);
        }

        // Print the first negative
        // integer of last window
        if (!Di.isEmpty())
            System.out.print(arr[Di.peek()] + " ");
        else
            System.out.print("0" + " ");
    }

    public void printFirstNegativeInteger_3(int arr[], int k, int n) { // Time : O(n) Space : O(1)
        int firstNegativeIndex = 0;
        int firstNegativeElement;

        for (int i = k - 1; i < n; i++) {

            // Skip out of window and positive elements
            while ((firstNegativeIndex < i) && (firstNegativeIndex <= i - k || arr[firstNegativeIndex] > 0)) {
                firstNegativeIndex++;
            }

            // Check if a negative element is
            // found, otherwise use 0
            if (arr[firstNegativeIndex] < 0) {
                firstNegativeElement = arr[firstNegativeIndex];
            } else {
                firstNegativeElement = 0;
            }
            System.out.print(firstNegativeElement + " ");
        }
    }

    // GFG : Maximum sum of smallest and second smallest in an array

    public int pairWithMaxSum(int[] arr, int N) {
        if (N < 2)
            return -1;

        // Find two consecutive elements with maximum
        // sum.
        int res = arr[0] + arr[1];
        for (int i = 1; i < N - 1; i++)
            res = Math.max(res, arr[i] + arr[i + 1]);

        return res;
    }

    // GFG : Generate Binary Numbers from 1 to n

    public void generatePrintBinary(int n) {
        Queue<String> q = new LinkedList<String>();
        q.add("1");
        while (n-- > 0) {
            // print the front of queue
            String s1 = q.peek();
            q.remove();
            System.out.println(s1);

            q.add(s1 + "0");
            q.add(s1 + "1");
        }
    }

    // Leetcode 361: Remove Duplicate Letters ->
    // https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

    // Leetcode 844 Backspace String Compare

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) { // Find position of next possible char in (S)
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else
                    break;
            }
            while (j >= 0) { // Find position of next possible char in (T)
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else
                    break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;

            i--;
            j--;
        }
        return true;
    }

    // Leetcode 946 Validate Stack Sequences

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        int i = 0;
        int j = 0;

        Stack<Integer> stack = new Stack<>();

        while (i < pushed.length && j < popped.length) {
            while (stack.size() != 0 && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
            stack.push(pushed[i]);
            i++;
        }

        while (stack.size() != 0 && stack.peek() == popped[j]) {
            stack.pop();
            j++;
        }

        if (stack.size() == 0)
            return true;
        return false;
    }

    public boolean validateStackSequences_2(int[] pushed, int[] popped) {
        int n = pushed.length;
        int j = 0;

        Stack<Integer> stack = new Stack<>();

        for (int ele : pushed) {
            stack.push(ele);
            while (stack.size() != 0 && j < n && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        if (stack.size() == 0)
            return true;
        return false;
    }

    // Leetcode 895 Maximum Frequency Stack

    class FreqStack {
        Map<Integer, Integer> freq;
        Map<Integer, Stack<Integer>> group;
        int maxfreq;

        public FreqStack() {
            freq = new HashMap<>();
            group = new HashMap<>();
            maxfreq = 0;
        }

        public void push(int x) {
            int f = freq.getOrDefault(x, 0) + 1;
            freq.put(x, f);
            if (f > maxfreq)
                maxfreq = f;

            // group.computeIfAbsent(f, z-> new Stack()).push(x);

            Stack<Integer> newStack = group.getOrDefault(f, new Stack<>());
            newStack.push(x);
            group.put(f, newStack);
        }

        public int pop() {
            int x = group.get(maxfreq).pop();
            freq.put(x, freq.get(x) - 1);
            if (group.get(maxfreq).size() == 0)
                maxfreq--;
            return x;
        }
    }

    // Leetcode 155 Min Stack

    class MinStack {

        Stack<Long> stack;
        long min;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int val) {
            long vals = (long) val;
            if (stack.size() == 0) {
                stack.push((long) 0);
                min = vals;
            } else {
                stack.push(vals - min);
                if (vals < min) {
                    min = vals;
                }
            }
        }

        public void pop() {
            long temp = stack.pop();
            if (temp < 0) {
                long val = min;
                min = val - temp;
            }
        }

        public int top() {
            long temp = stack.peek();
            if (temp < 0)
                return (int) min;
            else
                return (int) (temp + min);
        }

        public int getMin() {
            return (int) min;
        }
    }

    class MinStack2 {

        /** initialize your data structure here. */
        private Stack<Integer> stack;
        private int min;

        public MinStack2() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.pop() == min)
                min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    // Reverse First K Elements of a Queue

    public void reverseQueueFirstKElements(Queue<Integer> queue, int k) {
        if (queue.isEmpty() == true || k > queue.size())
            return;
        if (k <= 0)
            return;

        Stack<Integer> stack = new Stack<Integer>();

        // Push the first K elements into a Stack
        for (int i = 0; i < k; i++) {
            stack.push(queue.peek());
            queue.remove();
        }

        // Enqueue the contents of stack
        // at the back of the queue
        while (!stack.empty()) {
            queue.add(stack.peek());
            stack.pop();
        }

        // Remove the remaining elements and enqueue
        // them at the end of the Queue
        for (int i = 0; i < queue.size() - k; i++) {
            queue.add(queue.peek());
            queue.remove();
        }
    }

    // Leetcode 1130 Minimum Cost Tree From Leaf Values
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> st = new Stack<>();
        st.push(Integer.MAX_VALUE);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            while (st.peek() < arr[i]) {
                int t = st.peek();
                st.pop();
                ans += t * Math.min(st.peek(), arr[i]);
            }
            st.push(arr[i]);
        }
        while (st.size() > 2) {
            int t = st.peek();
            st.pop();
            ans += t * st.peek();
        }
        return ans;
    }

    // Leetcode 853: Car Fleet 1

    class Car {
        int position;
        double time;

        Car(int p, double t) {
            position = p;
            time = t;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) { // Time: O(nlogn) and Space: O(n)

        int N = position.length;
        if (N == 0)
            return 0;

        Car[] cars = new Car[N];

        for (int i = 0; i < N; ++i)
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);

        Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));

        Stack<Double> stack = new Stack<>();
        stack.push(cars[0].time);

        for (int i = 1; i < N; i++) {
            while (stack.size() != 0 && stack.peek() <= cars[i].time) {
                stack.pop();
            }
            stack.push(cars[i].time);
        }

        return stack.size();
    }

    public int carFleet_2(int target, int[] position, int[] speed) { // Time: O(nlogn) and Space: O(1)

        int N = position.length;
        if (N == 0)
            return 0;

        Car[] cars = new Car[N];

        for (int i = 0; i < N; ++i)
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);

        Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));

        int cnt = 1;
        for (int i = N - 2; i >= 0; i--) {
            if (cars[i].time > cars[i + 1].time) {
                // found a new separated car fleet
                cnt += 1;
            } else // merge cars
                cars[i].time = cars[i + 1].time;
        }
        return cnt;
    }

    // Leetcode 134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {   // Time: O(2N)

        if (gas.length == 0) {
            return -1;
        }

        if (gas.length == 1) {
            return gas[0] - cost[0] < 0 ? -1 : 0;
        }

        int start = 0;
        int end = 1;

        int curr = gas[0] - cost[0];

        while (start != end) {

            while (curr < 0 && start != end) {
                curr = curr - (gas[start] - cost[start]);
                start = (start + 1) % gas.length;
                if (start == 0)
                    return -1;
            }
            curr += gas[end] - cost[end];

            end = (end + 1) % gas.length;
        }

        if (curr < 0)
            return -1;
        return start;
    }


    public int canCompleteCircuit_2(int[] gas, int[] cost) {
        int currentGaining = 0;
		int totalGaining = 0;
		int candidate = 0;
		for (int i = 0; i < gas.length; i++) {
			currentGaining += gas[i] - cost[i];
			totalGaining += gas[i] - cost[i];
			if (currentGaining < 0) {
				candidate = i + 1;
				currentGaining = 0;
			}
		}
		return totalGaining >= 0 ? candidate : -1;
    }
}