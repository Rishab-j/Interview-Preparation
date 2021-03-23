import java.util.*;

class testing {
    static int array[];
    static int primes[];

    public static void calculate(int n, int m) {
        int j = 0;
        int sqt = (int) Math.sqrt(m);
        array = new int[sqt + 1];
        primes = new int[sqt + 1];

        // System.out.println(“sqt:” + sqt);

        initialise(sqt + 1);

        for (int i = 2; i*i <= sqt; i++) {
            if (array[i] == 1) {
                for (int k = i + i; k <= sqt; k += i) {
                    array[k] = 0;
                }
            }
        }

        for (int i = 2; i <= sqt; i++) {
            if (array[i] == 1) {
                primes[j] = i;
                j++;
            }
        }
        
        printPrimesArray(j);

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
        // for (int i = 0; i < diff; i++) {
        //     if (array[i] == 1 && (i + n) != 1)
        //         System.out.println(i + n);
        // }
    }

    
    public static void printPrimesArray(int j) { 
        for (int i = 0; i < j; i++) {
            System.out.print(primes[i]+",");
        } 
        System.out.println(); 
    }
     
    public static void initialise(int sqt) {
        for (int i = 0; i < sqt; i++) {
            array[i] = 1;
        }
    }

    public static void main(String arg[]) {
        int t, n, m;
        // Scanner in = new Scanner(System.in);
        t = 1;
        for (int i = 0; i < t; i++) {
            n = 105;
            m = 200;
            calculate(n, m);
            System.out.println();
        }
        
    }
}
// 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199.