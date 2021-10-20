import java.util.*;

/**
 * SubarrayswithKDifferentIntegers
 */
public class SubarrayswithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] A, int K) {

        int res = 0, count = 0, lo = 0, hi = 0, max = 0, n;
        Map<Integer, Integer> dp = new HashMap<>();

        while (hi < A.length) {

            // define how many ranges starting with lo, and has K distinct numbers
            while (count == K) {
                res += max - hi + 1;
                n = dp.get(A[lo]);
                if (n == 1) {
                    count--;
                    hi++;
                }
                dp.put(A[lo], n - 1);
                lo++;
            }

            // find the first range starting with lo, and has K distinct numbers
            while (hi < A.length) {
                n = dp.getOrDefault(A[hi], 0);
                if (n == 0)
                    count++;
                dp.put(A[hi], n + 1);

                // if found hi, then define max
                if (count == K) {
                    max = hi;
                    while (max + 1 < A.length) {
                        if (dp.getOrDefault(A[max + 1], 0) == 0)
                            break;
                        max++;
                    }
                    break;
                }
                hi++;
            }
        }

        return res;

    }

    // Method 2

    public int subarraysWithKDistinct_2(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0)
                K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0)
                    K++;
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}