public class CelebrityProblem {
    int celebrity(int M[][], int n) {
        // code here
        int i = 0;
        int j = n - 1;

        while (i < j) {

            if (M[j][i] == 1) {
                j--;
            } else {
                i++;
            }

        }

        int cand = i;

        for (int k = 0; k < n; k++) {

            if (k != cand) {
                if (M[cand][k] == 1 || M[k][cand] == 0) {
                    return -1;
                }
            }

        }

        return cand;
    }
}
