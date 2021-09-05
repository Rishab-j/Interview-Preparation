import java.util.*;

public class GrayCode {


    // Recursion

    private ArrayList<String> code(int n) {
        if (n == 1) {
            ArrayList<String> base = new ArrayList<String>();
            base.add("0");
            base.add("1");

            return base;
        }

        ArrayList<String> recAns = code(n - 1);

        ArrayList<String> myAns = new ArrayList<>();

        for (int i = 0; i < recAns.size(); i++) {
            myAns.add("0" + recAns.get(i));
        }

        for (int i = recAns.size() - 1; i >= 0; i--) {
            myAns.add("1" + recAns.get(i));
        }

        return myAns;
    }


    // Dp
    public List<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<>();

        ArrayList<String> ans = code(n);

        for (String bin : ans) {
            res.add(Integer.parseInt(bin, 2));
        }

        return res;

    }


    // Bits
    public List<Integer> grayCode_1(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0); // n=0 {0}
        if (n == 0)
            return list;
        int increase = 1;
        for (int i = 0; i < n; i++) { // n=2
            int len = list.size(); // len = 2
            for (int j = len - 1; j >= 0; j--) { // {0,1,3,2}
                list.add(list.get(j) + increase); // n=1 {0,1}
            }
            increase *= 2;
        }
        return list;
    }

    public List<Integer> grayCode_2(int n) {
        List<Integer> rs = new ArrayList<Integer>();
        rs.add(0);
        for (int i = 0; i < n; i++) {
            int size = rs.size();
            for (int k = size - 1; k >= 0; k--)
                rs.add(rs.get(k) | 1 << i);
        }
        return rs;
    }
}
