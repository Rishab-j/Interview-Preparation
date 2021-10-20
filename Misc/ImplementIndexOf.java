public class ImplementIndexOf {
    public int strStr(String haystack, String needle) {

        if (needle.length() == 0)
            return 0;
        if (haystack.length() == 0)
            return -1;

        String search = needle + "#" + haystack;

        int[] lps = new int[search.length()];

        int len = 0;
        int i = 1;

        lps[0] = 0;

        while (i < search.length()) {
            if (search.charAt(i) == search.charAt(len)) {
                len++;
                lps[i] = len;

                if (lps[i] == needle.length()) {
                    return i - (2 * needle.length());
                }
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    len = 0;
                    lps[i] = 0;
                    i++;
                }
            }

        }

        return -1;
    }
}
