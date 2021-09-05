class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {

        int l = 1;
        int r = Integer.MIN_VALUE;

        for (int i = 0; i < piles.length; i++) {
            r = Math.max(r, piles[i]);
        }

        // int ans = r;
        while (l <= r) {

            int rate = l + (r - l) / 2;

            int hours = 0;
            int i = 0;

            while (i < piles.length) {

                // hours += piles[i] / rate; // method to find ceil
                // if (piles[i] % rate != 0)
                // hours++;
                // i++;

                // another way
                // hours += Math.ceil((piles[i] * 1.0)/ rate); // method to find ceil
                // i++;

                // another way
                hours += (piles[i] - 1) / rate + 1; // method to find ceil
                i++;
            }

            if (hours <= h) {
                // ans = Math.min(ans, rate);
                r = rate - 1;
            } else {
                l = rate + 1;
            }

        }

        // return ans; // this also give correct answer but takes more time due to
        // comparison
        return l;
    }
}