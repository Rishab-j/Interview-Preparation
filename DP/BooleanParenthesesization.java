public class BooleanParenthesesization {
    public int countParenth(char[] sym, char[] op) {
        // write your code here
        int n = sym.length;
        int[][] trueDP = new int[n][n];
        int[][] falseDP = new int[n][n];

        for (int i = 0; i < n; i++) {
            if (sym[i] == 'T') {
                trueDP[i][i] = 1;
            } else {
                falseDP[i][i] = 1;
            }
        }

        return count(sym, op, trueDP, falseDP);
    }

    private int count(char[] sym, char[] op, int[][] trueDP, int[][] falseDP) {

        for (int gap = 1; gap < sym.length; gap++) {
            for (int l = 0, r = gap; r < sym.length; l++, r++) {
                for (int i = l; i < r; i++) {
                    if (op[i] == '|') {
                        trueDP[l][r] += (trueDP[l][i] * falseDP[i + 1][r]) + (falseDP[l][i] * trueDP[i + 1][r])
                                + (trueDP[l][i] * trueDP[i + 1][r]);

                        falseDP[l][r] += (falseDP[l][i] * falseDP[i + 1][r]);
                    } else if (op[i] == '&') {
                        falseDP[l][r] += (trueDP[l][i] * falseDP[i + 1][r]) + (falseDP[l][i] * trueDP[i + 1][r])
                                + (falseDP[l][i] * falseDP[i + 1][r]);

                        trueDP[l][r] += (trueDP[l][i] * trueDP[i + 1][r]);
                    } else {
                        trueDP[l][r] += (trueDP[l][i] * falseDP[i + 1][r]) + (falseDP[l][i] * trueDP[i + 1][r]);

                        falseDP[l][r] += (trueDP[l][i] * trueDP[i + 1][r]) + (falseDP[l][i] * falseDP[i + 1][r]);
                    }
                }
            }
        }

        return trueDP[0][sym.length - 1];
    }
}
