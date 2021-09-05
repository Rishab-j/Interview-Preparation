class test{

    public int solve(int n, int m, int c, int[] a){
        
        int count = 0;
        int min = a[0];
        int max = a[1]; 

        for(int i = 0; i < m; i++){
            min = Math.min(a[i], min);
            max = Math.max(a[i], max);
        }

        if(max - min <= c) count++; 

        for(int i = m; i < n; i++){
            int j = i - m;
            int min1 = a[0];
            int max1 = a[1]; 
            for(int k = j + 1; j < i; j++){
                min1 = Math.min(a[k], min1);
                max1 = Math.max(a[k], max1);
            }

            if(max1 - min1 <= c) count++; 
        }

        return count;
    }


}