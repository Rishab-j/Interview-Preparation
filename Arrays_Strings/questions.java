import java.util.*;

class questions{

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

	//Lintcode 901 Range addition
	public int[] RangeAddition(int[][] queries, int n){
        int[] arr = new int[n];

        for(int i = 0 ;  i < queries.length ; i++){
            arr[queries[i][0]] =  queries[i][2];
            if(queries[i][1] < n -1) arr[queries[i][1] + 1] = - queries[i][2]; 
        }

        for(int i = 1 ; i<n ;i++){
            arr[i] = arr[i] + arr[i-1];
        }
        return arr;
    }

	// Leetcode 899
	public String orderlyQueue(String S, int K) {
        if (K == 1) {
            String ans = S;
            for (int i = 0; i < S.length(); ++i) {
                String T = S.substring(i) + S.substring(0, i);
                if (T.compareTo(ans) < 0) ans = T;
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
		if(k<0) k = nums.length + k;
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
		if(k<0) k = nums.length + k;
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
        if(k<0) k = nums.length +k;
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
        
        for(int k = 0; k < n; k++){
            if(nums[k] < 0) j++;
            nums[k] = nums[k] * nums[k];
        }
        
        i = j-1;
        while(i>=0 && j < n){
            if(nums[i] < nums[j]){
                arr[s] = nums[i];
                i--;
                s++;
            }else{
                arr[s] = nums[j];
                j++;
                s++;
            }
        }
        
        while(i >= 0){
            arr[s] = nums[i];
            i--;
            s++;
        }
        
        while(j < n){
            arr[s] = nums[j];
            j++;
            s++;
        }
        
        return arr;
    }

	//Napolean Cake (CodeForces)
	public int[] NapoleanCake(int[] cream, int n){
        int[] arr = new int[n];

        for(int i = 0 ;  i < cream.length ; i++){
            if(cream[i] > 0){
                if(i-cream[i] >= 0){
                    arr[i] += cream[i];
                    arr[i-cream[i]] -= cream[i]; 
                }
                else{ 
                    arr[i] += cream[i];
                }
            }
        }

        for(int i = n - 2; i >= 0; i--){
            arr[i] = arr[i] + arr[i+1];
        }

        for(int i = 0; i < n; i++){
            if(arr[i] > 0){
                arr[i] = 1;
            }
        }

        return arr;
    }

    // Leetcode 556 Next Greater Element 3

    public int nextGreaterElement(int n) {
        //1.convert integer into array
        String s=Integer.toString(n);
        int[] arr=new int[s.length()];
        for(int i=0;i<s.length();i++){
            arr[i]=(int)(s.charAt(i)-'0');
        }
        
        //2. first job is to find the value with smaller value than of its right
        int idx=-1;
        for(int i=arr.length-2;i>=0;i--){
            if(arr[i]<arr[i+1]){
                idx=i;
                break;
            }
        }
        
        //3.It means that next greater doesn't exist eg:21
        if(idx==-1) return -1;
        
        int val=arr[idx];
        int j=idx+1;
        int jm=arr[idx+1]; //just max
        
        //4. find just greater value in right most (<=jm 444 will choose rightmost 4) 
        for(int i=idx+1;i<arr.length;i++){
            if(arr[i]>val && arr[i]<=jm){
                jm=arr[i];
                j=i;
            }
        }
         
        
        swap(arr,idx,j);
        //5. number from idx+1 are in increasing order reverse them
        reverse2(arr,idx+1,arr.length-1);
        
        // int ans=0;
    
        long a=0;
        int mult=1;
        for(int i=arr.length-1;i>=0;i--){
            a+=(long)arr[i]*mult;
            mult=mult*10;
        }
        
         //6.final check have we croosed the integer max value or not
          return a>Integer.MAX_VALUE?-1:(int)a;
    }
    
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    
    public static void reverse2(int[] arr,int l,int r){
        while(l<r){
            swap(arr,l,r);
            l++;
            r--;
        }
    }


    // Leetcode 169 Majority Element (n/2)
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int c = nums[0];
        int v = 1;
        for(int i = 1; i < n; i++){
            if(nums[i] == c){
                v++;
            }else{
                if(v > 0){
                    v--;
                }else{
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
        
        int c1 = 0;   // counters
        int c2 = 0;
        
        for(int ele: arr){
            
            if(ele == n1) c1++;
            else if (ele == n2) c2 ++;
            else if (c1 == 0){
                n1 = ele;
                c1++;
            }else if (c2 == 0){
                n2 = ele;
                c2++;
            }else{
                c2--;
                c1--;
            }
        }
        
        int count1 = 0;
        int count2 = 0;
        int m = n/3 + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(arr[i] == n1){
                count1++;
                if(count1 == m){
                    ans.add(n1);
                }
            }else if(arr[i] == n2){
                count2++;
                if(count2 == m){
                    ans.add(n2);
                }
            }
        }
        
        return ans;
    }


    // Majority Element n/k

        // Simply use hashmap

        

    //Leetcode 769 Max Chunk To Make Sorted 1

    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunk = 0;
        
        for(int i = 0; i < arr.length; i++){
            max = Math.max(arr[i],max);
            if(i == max){
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
        
        for(int i = 1; i < n; i++){
            if(arr[i] > maxLR[i-1]){
                maxLR[i] = arr[i];
            }else{
                maxLR[i] = maxLR[i-1];
            }
        }
        
        minRL[n-1] = arr[n-1];
        for(int i = n-2; i >= 0; i--){
            if(arr[i] < minRL[i+1]){
                minRL[i] = arr[i];
            }else{
                minRL[i] = minRL[i+1];
            }
        }
        
        int chunk = 0;
        
        for(int i = 0; i < n-1; i++){
            if(maxLR[i] <= minRL[i+1]){
                chunk++;
            }
        }
        
        return chunk + 1;
    }

    //Leetcode 628 Maximum Product of Three Numbers

    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
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
        
        for(int i = 0; i < n;i++){
            if(arr[i] > max1){
                idx1 = i;
                max2 = max1;
                max1 = arr[i];
            }else if(arr[i] > max2){
                max2 = arr[i];
            }
        }
        
        return max1 >= 2*max2 ? idx1 : -1;
    }

    // Leetcode 238 Product of Array Except Self

    public int[] productExceptSelf(int[] arr) {  // Time - O(n) Space - O(n)
        int n = arr.length;
        int[] pre = new int[n];
        int[] suf = new int[n];
        
        pre[0] = arr[0];
        
        for(int i = 1; i < n; i++){
            pre[i] = pre[i-1] * arr[i];
        }
        
        suf[n-1] = arr[n-1];
        
        for(int i = n-2; i >= 0; i--){
            suf[i] = suf[i+1] * arr[i];
        }
        
        int[] ans = new int[n];
        
        for(int i = 0; i < n; i++){
            if(i == 0){
                ans[i] = suf[i+1];
            }else if(i==n-1){
                ans[i] = pre[i-1];
            }else{
                ans[i] = suf[i+1] * pre[i-1];
            }
        }
        return ans;
    }

    public int[] productExceptSelf_2(int[] arr) {  // Time - O(n) Space - O(1)
        int n = arr.length;
        int[] pre = new int[n];
        
        pre[0] = arr[0];
        
        for(int i = 1; i < n; i++){
            pre[i] = pre[i-1] * arr[i];
        }
        
        int suf = 1;
        
        for(int i = n-1; i >= 0; i--){
            if(i==0){
                pre[i] = suf;
            }else{
                pre[i] = pre[i-1] * suf;
                suf = suf * arr[i];
            }
        }
        return pre;
    }


    // Leetcode 795 Number of Subarrays with Bounded Maximum

    public int numSubarrayBoundedMax(int[] arr, int L, int R) {  // Time -> O(n^2) (worst case)
        int i = 0;
        int ans = 0;
        int n = arr.length;
        for(int j = 0; j < n; j++){
            if(arr[j] >= L && arr[j] <= R){
                ans += j - i + 1;
            }else if(arr[j] < L){
                int k = j - 1;
                while(k >= i){
                    if(arr[k] >= L && arr[k] <= R){
                        ans += k - i + 1;
                        break;
                    }
                    k--;
                }
            }else{
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
        for(int j = 0; j < n; j++){
            if(arr[j] >= L && arr[j] <= R){
                ans += j - i + 1;
                prev = j - i + 1;
            }else if(arr[j] < L){
                ans += prev;
            }else{
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
        for(int i = 0; i < nums.length; i++){
            if(csum >= 0){
                csum += nums[i];
                cei = i;
            }else{
                csum = nums[i];
                csi = i;
                cei = i;
            }
            if(omax < csum){
                omax = csum;
                osi = csi;
                oei = cei;
            }
            
        }  
        for(int i = osi; i <= oei; i++){
            System.out.print(nums[i] + " ");
        }
        return omax;
    }

    // Dutch Flag (Segregate 0, 1 and 2)

    public static void dutchFlag(int[] arr){
        
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

    // Leetcode 763 Partition Labels

    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;
        
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        int max = 0;
        int first = 0;
        
        for (int i = 0; i < S.length(); i++){
            max = Math.max(last[S.charAt(i) - 'a'],max);
            if(i == max){
                ans.add(i - first + 1);
                first = i + 1;
            }
        }
        
        return ans;
    }

    // Leetcode 905 Sort by Parity (LIKE SEGREGATE 0s AND 1s)

    public int[] sortArrayByParity(int[] A) {
        int odd = 0;  // position of first odd no.
        int t = 0;     // traversal
        while(t < A.length){
            if(A[t] % 2 != 0){
                t++;
            }else{
                int temp = A[odd];
                A[odd] = A[t];
                A[t] = temp;
                t++;
                odd++;
            }
        }
        return A;
    }

}