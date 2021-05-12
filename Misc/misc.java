import java.util.*;

class misc {

    /** 1647. Minimum Deletions to Make Character Frequencies Unique */

    public int minDeletions(String s) {
        if (s == null)
            return 0;
        if (s.length() == 0)
            return 0;
        int[] c = new int[26];
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int a : c) {
            int j = 0;
            if (a != 0) {
                while (set.contains(a) && a != 0) {
                    a--;
                    j++;
                }
                set.add(a);
                ans += j;
            }
        }
        return ans;
    }

    /** Largest K such that both K and -K exist in array */

    public int largestNum(int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(-nums[i]);
            if (set.contains(nums[i])) {
                res = Math.max(res, Math.abs(nums[i]));
            }
        }
        return res;
    }

    public int largestNum2(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == 0) {
                res = Math.max(res, Math.max(nums[l], nums[r]));
                l++;
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

    /** Min Steps to Make Piles Equal Height */

    public static int steps(int[] piles) {

        int res = 0;
        int n = piles.length;
        Arrays.sort(piles);

        for (int i = n - 2; i >= 0; i--) {
            if (piles[i] != piles[i + 1]) {
                res += n - i - 1;
            }
        }
        return res;
    }

    /** Min Adj Swaps to make given String Palindrome */

    public int getNoOfSwaps(String s) {
        if (s == null || s.length() == 0)
            return -1;
        int totalSwaps = 0;

        if (isShuffledPalindrome(s)) {
            char[] chars = s.toCharArray();
            int p1 = 0, p2 = chars.length - 1;

            while (p2 > p1) {
                if (chars[p1] != chars[p2]) {
                    int k = p2;
                    while (k > p1 && chars[k] != chars[p1])
                        k--;

                    if (k == p1) { // When no matching character found
                        swap(chars, p1, p1 + 1);
                        totalSwaps++;

                    } else { // When Matching character found swap until K reaches p2 position
                        while (k < p2) {
                            swap(chars, k, k + 1);
                            totalSwaps++;
                            k++;
                        }
                        p1++;
                        p2--;
                    }
                } else {
                    p1++;
                    p2--; // When the characters are equal move on
                }
            }
            return totalSwaps;
        } else
            return -1;
    }

    public static void swap(char[] chars, int k, int i) {
        char temp = chars[k];
        chars[k] = chars[i];
        chars[i] = temp;
    }

    public boolean isShuffledPalindrome(String s) {
        int[] occurrence = new int[26];
        int oddCount = 0;

        for (int i = 0; i < s.length(); i++)
            occurrence[s.charAt(i) - 'a']++;
        for (int value : occurrence)
            if (value % 2 != 0)
                oddCount++;
        return oddCount <= 1;
    }



    /** Find the smallest positive integer value that cannot be represented as sum of any subset of a given array */

    public int FindMinImpossible(int[] a) {

        int maxPossible = 0;

        if (a.length == 0 || a[0] != 1) {
            return maxPossible + 1;
        }

        // we have verified that 1 exists in the array.
        maxPossible = 1;

        for (int i = 1; i < a.length; i++) {
            // if the current element is greater than (maxPossible + 1)
            // that leaves a gap at: maxPossible + 1
            if (a[i] > maxPossible + 1) {
                break;
            }

            maxPossible += a[i];
        }

        return maxPossible + 1;
    }

    /** Leetcode 430 Flatten a Multilevel Doubly Linked List */

    public Node flatten(Node head) {
        if( head == null) return head;
        Node p = head; 
        while( p!= null) {
            if(p.child != null){
                Node temp = p.child;
                // Find the tail of the child
                while( temp.next != null ) 
                    temp = temp.next;
                // Connect tail with p.next, if it is not null
                temp.next = p.next;  
                if( p.next != null )  p.next.prev = temp;
                // Connect p with p.child, and remove p.child
                p.next = p.child; 
                p.child.prev = p;
                p.child = null;
            }
            p = p.next;
        }
        return head;
    }
}