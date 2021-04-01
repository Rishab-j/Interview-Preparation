import java.util.Scanner;
import java.util.Arrays;

public class testing {

    private static int solution(long[] arr, long[] copy) { // 0 0 3 2 1 4 5 6 7
        int n = arr.length;
        int l = 0, r = 0;
        int seg = 0;
        int count = 0;
        Arrays.sort(copy);
        for (int i = 0; i < n; i++) {
            if (copy[i] == arr[i])
                count++;
        }

        if (count == n) {
            System.out.println("yes");
            System.out.print("1 1");
            return 0;
        }

        for (int i = 0; i < n - 1;i++) {
            if (seg < 1) {
                if (arr[i] < arr[i + 1]) {
                    i++;
                }

                else {
                    l = i;
                    r = i;
                    while (i < n - 1 && arr[i] > arr[i + 1]) {
                        i++;
                        r++;
                    }

                    Arrays.sort(arr, l, r + 1);
                    seg++;
                }
            }

        }

        for (int i = 0; i < n; i++) {
            if (copy[i] != arr[i]) {
                System.out.println("no");
                return 0;
            }
        }

        System.out.println("yes");
        System.out.print((l + 1) + " " + (r + 1));
        return 0;
    }

    public static void main(String arg[]) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        long[] arr = new long[n];
        long[] copy = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextLong();
            copy[i] = arr[i];
        }
        // solution(new long[]{2,1,3,4},new long[]{2,1,3,4});
        solution(arr, copy);

        scn.close();

    }
}