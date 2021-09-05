/**
 * RightMostBit
 */
public class RightMostBit {


    // Method 1
    public static int getFirstSetBitPos(int n) {
        return (int) ((Math.log10(n & -n)) / Math.log10(2)) + 1;
    }


    // Method 2
    static int Right_most_setbit(int num) {
        int pos = 1;
        // counting the position of first set bit
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) == 0)
                pos++;

            else
                break;
        }
        return pos;
    }

    public static void main(String[] args) {
        // System.out.println(1 << 2);
    }

}