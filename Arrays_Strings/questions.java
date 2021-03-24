import java.util.*;

class questions {

    // Leetcode 925
    public boolean isLongPressedName(String name, String typed) {
        int p1 = 0, p2 = 0;
        while (p1 < name.length() && p2 < typed.length()) {
            if (name.charAt(p1) == typed.charAt(p2)) {// both char matches
                p1++;
                p2++;
            } else {
                if (p2 > 0 && typed.charAt(p2 - 1) == typed.charAt(p2)) {// extra typed
                    while (p2 < typed.length() && typed.charAt(p2 - 1) == typed.charAt(p2))// skip while extra typed
                        p2++;
                } else {// not match
                    return false;
                }
            }
        }
        if (p2 == typed.length() && p1 != name.length())// typed end while name not
            return false;
        while (p2 != typed.length()) {// name end while typed not
            if (p2 > 0 && typed.charAt(p2) == typed.charAt(p2 - 1))
                p2++;
            else
                return false;
        }
        return true;
    }

    // Lintcode 901 Range addition
    public int[] RangeAddition(int[][] queries, int n) {
        int[] arr = new int[n];

        for (int i = 0; i < queries.length; i++) {
            arr[queries[i][0]] = queries[i][2];
            if (queries[i][1] < n - 1)
                arr[queries[i][1] + 1] = -queries[i][2];
        }

        for (int i = 1; i < n; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
        return arr;
    }

    // Leetcode 899
    public String orderlyQueue(String S, int K) {
        if (K == 1) {
            String ans = S;
            for (int i = 0; i < S.length(); ++i) {
                String T = S.substring(i) + S.substring(0, i);
                if (T.compareTo(ans) < 0)
                    ans = T;
            }
            return ans;
        } else {
            char[] ca = S.toCharArray();
            Arrays.sort(ca);
            return new String(ca);
        }
    }

    // Codechef - Max Range queries
    // Leetcode 11 - Container with most water

    // Leetoce 189 Rotate Array

    public void rotate_01(int[] nums, int k) {
        // speed up the rotation
        k %= nums.length;
        if (k < 0)
            k = nums.length + k;
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    public void rotate_02(int[] nums, int k) {
        k %= nums.length;
        if (k < 0)
            k = nums.length + k;
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    public void rotate_03(int[] nums, int k) {
        k %= nums.length;
        if (k < 0)
            k = nums.length + k;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // Leetcode 977
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = 0, s = 0;
        int n = nums.length;

        int[] arr = new int[n];

        for (int k = 0; k < n; k++) {
            if (nums[k] < 0)
                j++;
            nums[k] = nums[k] * nums[k];
        }

        i = j - 1;
        while (i >= 0 && j < n) {
            if (nums[i] < nums[j]) {
                arr[s] = nums[i];
                i--;
                s++;
            } else {
                arr[s] = nums[j];
                j++;
                s++;
            }
        }

        while (i >= 0) {
            arr[s] = nums[i];
            i--;
            s++;
        }

        while (j < n) {
            arr[s] = nums[j];
            j++;
            s++;
        }

        return arr;
    }

    // Napolean Cake (CodeForces)
    public int[] NapoleanCake(int[] cream, int n) {
        int[] arr = new int[n];

        for (int i = 0; i < cream.length; i++) {
            if (cream[i] > 0) {
                if (i - cream[i] >= 0) {
                    arr[i] += cream[i];
                    arr[i - cream[i]] -= cream[i];
                } else {
                    arr[i] += cream[i];
                }
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            arr[i] = arr[i] + arr[i + 1];
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                arr[i] = 1;
            }
        }

        return arr;
    }

    // Leetcode 556 Next Greater Element 3

    public int nextGreaterElement(int n) {
        // 1.convert integer into array
        String s = Integer.toString(n);
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = (int) (s.charAt(i) - '0');
        }

        // 2. first job is to find the value with smaller value than of its right
        int idx = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                idx = i;
                break;
            }
        }

        // 3.It means that next greater doesn't exist eg:21
        if (idx == -1)
            return -1;

        int val = arr[idx];
        int j = idx + 1;
        int jm = arr[idx + 1]; // just max

        // 4. find just greater value in right most (<=jm 444 will choose rightmost 4)
        for (int i = idx + 1; i < arr.length; i++) {
            if (arr[i] > val && arr[i] <= jm) {
                jm = arr[i];
                j = i;
            }
        }

        swap(arr, idx, j);
        // 5. number from idx+1 are in increasing order reverse them
        reverse2(arr, idx + 1, arr.length - 1);

        // int ans=0;

        long a = 0;
        int mult = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            a += (long) arr[i] * mult;
            mult = mult * 10;
        }

        // 6.final check have we croosed the integer max value or not
        return a > Integer.MAX_VALUE ? -1 : (int) a;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse2(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l, r);
            l++;
            r--;
        }
    }

    // Leetcode 169 Majority Element (n/2)
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int c = nums[0];
        int v = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == c) {
                v++;
            } else {
                if (v > 0) {
                    v--;
                } else {
                    c = nums[i];
                    v = 1;
                }
            }
        }
        return c;
    }

    // Leetcode 229 Majority Element 2 (n/3)

    public List<Integer> majorityElement2(int[] arr) {
        int n = arr.length;

        int n1 = -1;
        int n2 = -1;

        int c1 = 0; // counters
        int c2 = 0;

        for (int ele : arr) {

            if (ele == n1)
                c1++;
            else if (ele == n2)
                c2++;
            else if (c1 == 0) {
                n1 = ele;
                c1++;
            } else if (c2 == 0) {
                n2 = ele;
                c2++;
            } else {
                c2--;
                c1--;
            }
        }

        int count1 = 0;
        int count2 = 0;
        int m = n / 3 + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == n1) {
                count1++;
                if (count1 == m) {
                    ans.add(n1);
                }
            } else if (arr[i] == n2) {
                count2++;
                if (count2 == m) {
                    ans.add(n2);
                }
            }
        }

        return ans;
    }

    // Majority Element n/k

    // Simply use hashmap

    // Leetcode 769 Max Chunk To Make Sorted 1

    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunk = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            if (i == max) {
                chunk++;
            }
        }

        return chunk;
    }

    // Leetcode 768 Max Chunks to Make Sorted 2

    public int maxChunksToSorted_2(int[] arr) {
        int n = arr.length;
        int[] minRL = new int[n];
        int[] maxLR = new int[n];

        maxLR[0] = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] > maxLR[i - 1]) {
                maxLR[i] = arr[i];
            } else {
                maxLR[i] = maxLR[i - 1];
            }
        }

        minRL[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < minRL[i + 1]) {
                minRL[i] = arr[i];
            } else {
                minRL[i] = minRL[i + 1];
            }
        }

        int chunk = 0;

        for (int i = 0; i < n - 1; i++) {
            if (maxLR[i] <= minRL[i + 1]) {
                chunk++;
            }
        }

        return chunk + 1;
    }

    // Leetcode 628 Maximum Product of Three Numbers

    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) { // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) { // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) { // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) { // n lies betwen max2 and max3
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    // Leetcode 747 Largest Number At Least Twice of Others

    public int dominantIndex(int[] arr) {
        int n = arr.length;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int idx1 = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] > max1) {
                idx1 = i;
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2) {
                max2 = arr[i];
            }
        }

        return max1 >= 2 * max2 ? idx1 : -1;
    }

    // Leetcode 238 Product of Array Except Self

    public int[] productExceptSelf(int[] arr) { // Time - O(n) Space - O(n)
        int n = arr.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = arr[0];

        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * arr[i];
        }

        suf[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * arr[i];
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans[i] = suf[i + 1];
            } else if (i == n - 1) {
                ans[i] = pre[i - 1];
            } else {
                ans[i] = suf[i + 1] * pre[i - 1];
            }
        }
        return ans;
    }

    public int[] productExceptSelf_2(int[] arr) { // Time - O(n) Space - O(1)
        int n = arr.length;
        int[] pre = new int[n];

        pre[0] = arr[0];

        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * arr[i];
        }

        int suf = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                pre[i] = suf;
            } else {
                pre[i] = pre[i - 1] * suf;
                suf = suf * arr[i];
            }
        }
        return pre;
    }

    // Leetcode 795 Number of Subarrays with Bounded Maximum

    public int numSubarrayBoundedMax(int[] arr, int L, int R) { // Time -> O(n^2) (worst case)
        int i = 0;
        int ans = 0;
        int n = arr.length;
        for (int j = 0; j < n; j++) {
            if (arr[j] >= L && arr[j] <= R) {
                ans += j - i + 1;
            } else if (arr[j] < L) {
                int k = j - 1;
                while (k >= i) {
                    if (arr[k] >= L && arr[k] <= R) {
                        ans += k - i + 1;
                        break;
                    }
                    k--;
                }
            } else {
                i = j + 1;
            }
        }
        return ans;
    }

    public int numSubarrayBoundedMax_2(int[] arr, int L, int R) { // Time -> O(n)
        int i = 0;
        int prev = 0;
        int ans = 0;
        int n = arr.length;
        for (int j = 0; j < n; j++) {
            if (arr[j] >= L && arr[j] <= R) {
                ans += j - i + 1;
                prev = j - i + 1;
            } else if (arr[j] < L) {
                ans += prev;
            } else {
                i = j + 1;
                prev = 0;
            }
        }
        return ans;
    }

    // Leetcode Maximum Subarray (KADANES ALGORITHM)
    public int maxSubArray(int[] nums) {
        int csum = 0;
        int csi = 0;
        int cei = 0;
        int omax = Integer.MIN_VALUE;
        int osi = 0;
        int oei = 0;
        for (int i = 0; i < nums.length; i++) {
            if (csum >= 0) {
                csum += nums[i];
                cei = i;
            } else {
                csum = nums[i];
                csi = i;
                cei = i;
            }
            if (omax < csum) {
                omax = csum;
                osi = csi;
                oei = cei;
            }

        }
        for (int i = osi; i <= oei; i++) {
            System.out.print(nums[i] + " ");
        }
        return omax;
    }

    // Dutch Flag (Segregate 0, 1 and 2)

    public static void dutchFlag(int[] arr) {

        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while (j < k) {
            if (arr[j] == 1) {
                j++;
            } else if (arr[j] == 2) {
                swap(arr, j, k);
                k--;
            } else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }

        for (int h = 0; h < arr.length; h++) {
            System.out.print(arr[h] + " ");
        }

    }

    // Leetcode 763 Partition Labels

    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        ArrayList<Integer> ans = new ArrayList<Integer>();

        int max = 0;
        int first = 0;

        for (int i = 0; i < S.length(); i++) {
            max = Math.max(last[S.charAt(i) - 'a'], max);
            if (i == max) {
                ans.add(i - first + 1);
                first = i + 1;
            }
        }

        return ans;
    }

    // Leetcode 905 Sort by Parity (LIKE SEGREGATE 0s AND 1s)

    public int[] sortArrayByParity(int[] A) {
        int odd = 0; // position of first odd no.
        int t = 0; // traversal
        while (t < A.length) {
            if (A[t] % 2 != 0) {
                t++;
            } else {
                int temp = A[odd];
                A[odd] = A[t];
                A[t] = temp;
                t++;
                odd++;
            }
        }
        return A;
    }

    // Leetcode 829 Consecutive Number Sum

    public int consecutiveNumbersSum(int N) { // Time : O(N) gives TLE
        if (N == 1)
            return 1;

        int half = (N - 1) / 2 + 1; // The max number in the series is bound by this
        int count = 1; // Signifies count of consecutive integers leading to the sum
        int left = 1;
        int right = 1;
        int runningSum = 1;

        // Sliding window from left to right to try and get consecutive numbers
        while (right <= half) {
            if (runningSum == N) {
                // Match found!
                count++;
                runningSum -= left;
                // Reduce the window by one since a match has been found already
                left++;
            } else if (runningSum > N) {
                // Current window has no chance of finding a match by expanding
                runningSum -= left;
                left++;
            } else {
                // Increase right side of the window
                right++;
                runningSum += right;
            }
        }

        return count;
    }

    public int consecutiveNumbersSum2(int N) { // Time : O(rootN)

        int ans = 0;

        for (int k = 1; k * k < 2 * N; k++) {

            if ((N - k * (k - 1) / 2) % k == 0)
                ans++;
        }
        return ans;
    }

    // FAST EXPONENTIATION -> in Notes

    // FIBONACCI
    public int fib(int N) { // Using fast exponentiation O(logn)
        if (N <= 1) {
            return N;
        }
        int[][] A = new int[][] { { 1, 1 }, { 1, 0 } };
        matrixPower(A, N - 1);

        return A[0][0];
    }

    public void matrixPower(int[][] A, int N) {
        if (N <= 1) {
            return;
        }
        matrixPower(A, N / 2);
        multiply(A, A);

        int[][] B = new int[][] { { 1, 1 }, { 1, 0 } };
        if (N % 2 != 0) {
            multiply(A, B);
        }
    }

    public void multiply(int[][] A, int[][] B) {
        int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];

        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }

    public int fibMath(int N) { // Using math O(1)
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(goldenRatio, N) / Math.sqrt(5));
    }

    // Sieve Of Eratosthenes
    public void sieveOfEratosthenes(int n) // O(log(log(n)))
    {
        // Create a boolean array
        // "prime[0..n]" and
        // initialize all entries
        // it as true. A value in
        // prime[i] will finally be
        // false if i is Not a
        // prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                System.out.print(i + " ");
        }
    }

    // Segmented Sieve Of Eratosthenes

    // Also read : https://www.geeksforgeeks.org/segmented-sieve/

    static int array[];
    static int primes[];

    public static void calculate(int n, int m) {
        int j = 0;
        int sqt = (int) Math.sqrt(m);
        array = new int[sqt + 1];
        primes = new int[sqt + 1];
        // System.out.println(“sqt:” + sqt);
        initialise(sqt + 1);

        for (int i = 2; i <= sqt; i++) {
            if (array[i] == 1) {
                primes[j] = i;
                j++;
                for (int k = i + i; k <= sqt; k += i) {
                    array[k] = 0;
                }
            }
        }

        /**
         * ABOVE FOR LOOP CAN ALSO BE WRITTEN AS
         * 
         * for (int i = 2; i*i <= sqt; i++) { // SIMPLE SIEVE if (array[i] == 1) { for
         * (int k = i + i; k <= sqt; k += i) { array[k] = 0; } } }
         * 
         * for (int i = 2; i <= sqt; i++) { if (array[i] == 1) { primes[j] = i; j++; } }
         */

        // printPrimesArray(j);
        int diff = (m - n + 1);
        array = new int[diff];
        initialise(diff);
        for (int k = 0; k < j; k++) {
            int div = n / primes[k];
            div *= primes[k];
            while (div <= m) {
                if (div >= n && primes[k] != div)
                    array[div - n] = 0;
                div += primes[k];
            }
        }

        for (int i = 0; i < diff; i++) {
            if (array[i] == 1 && (i + n) != 1)
                System.out.println(i + n);
        }
    }

    /*
     * public static void printPrimesArray(int j) { for (int i = 0; i < j; i++) {
     * System.out.print(“,” + primes[i]); } System.out.println(); }
     */
    public static void initialise(int sqt) {
        for (int i = 0; i < sqt; i++) {
            array[i] = 1;
        }
    }

    // Lintcode 508 Wiggle Sort
    public static void wiggle(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                if (arr[i] > arr[i - 1]) {
                    swap(arr, i, i - 1);
                }
            } else {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // GFG : Find the number of jumps to reach X in the number line from zero

    public static int countJumps(int n) { // O(root(N)) (where N is the 2*X)
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            if (sum == n) {
                return i;
            } else if (sum > n) {
                if ((sum - n) % 2 == 0) {
                    return i;
                } else {
                    if ((i + 1) % 2 == 0) {
                        return i + 2;
                    } else {
                        return i + 1;
                    }
                }
            }
        }

        return -1;
    }

    // Leetcode 670 Maximum Swap
    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }

    // Leetcode 1007 Minimum Domino Rotations For Equal Row
    public int minDominoRotations(int[] A, int[] B) {

        int Acount1 = 0;
        int Acount2 = 0;
        int Bcount1 = 0;
        int Bcount2 = 0;

        for (int i = 0; i < A.length; i++) {
            if (Acount1 != Integer.MAX_VALUE && Acount2 != Integer.MAX_VALUE) {
                if (A[i] == A[0]) {
                    if (B[i] != A[0]) {
                        Acount2++;
                    }
                } else if (B[i] == A[0]) {
                    if (A[i] != A[0]) {
                        Acount1++;
                    }
                } else {
                    Acount1 = Integer.MAX_VALUE;
                    Acount2 = Integer.MAX_VALUE;
                }
            }
            if (Bcount1 != Integer.MAX_VALUE && Bcount2 != Integer.MAX_VALUE) {
                if (B[i] == B[0]) {
                    if (A[i] != B[0]) {
                        Bcount2++;
                    }
                } else if (A[i] == B[0]) {
                    if (B[i] != B[0]) {
                        Bcount1++;
                    }
                } else {
                    Bcount1 = Integer.MAX_VALUE;
                    Bcount2 = Integer.MAX_VALUE;
                }
            }
        }

        if (Acount1 == Integer.MAX_VALUE && Bcount1 == Integer.MAX_VALUE) {
            return -1;
        } else {
            return Math.min(Acount1, Math.min(Acount2, Math.min(Bcount1, Bcount2)));
        }

    }

    // Leetcode 43 Multiply Strings

    public String multiply(String num1, String num2) {
        // 1. Taking care of Boundary Case
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        // 2.at max length of product will be n+m
        int[] ans = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {

                int valueIdx = i + j + 1;
                int remainderIdx = i + j;
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                // 3.Adding value of remainder if present earlier at valueidx
                product += ans[valueIdx];

                // 4.digit value
                ans[valueIdx] = product % 10;

                // 5.Remainder from me
                ans[remainderIdx] += product / 10;

            }
        }

        // boolean precedingZeros=true;
        // StringBuilder result=new StringBuilder("");

        // //6.As we have made a array of maximum length n*m=n+m So result will be less
        // than or equal to this. in less than case inital values will be zeros . So
        // have to Remove Preceding Zeroes.
        // for(int i=0;i<ans.length;i++){
        // int value=ans[i];
        // if(value!=0) precedingZeros=false;
        // if(!precedingZeros) result.append(value);
        // }

        // return result.toString();

        int index = 0;
        while (index < ans.length && ans[index] == 0) {
            ++index;
        }

        String result = "";
        for (; index < ans.length; index++) {
            result += String.valueOf(ans[index]);
        }
        return result;

    }

}