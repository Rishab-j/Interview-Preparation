import java.util.*;

public class WeightedJobScheduling {
    class Job {
        int start, finish, profit;

        public Job(int start, int finish, int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    };

    // Utility function to calculate sum of all
    // ArrayList elements
    int findSum(ArrayList<Job> arr) {
        int sum = 0;

        for (int i = 0; i < arr.size(); i++)
            sum += arr.get(i).profit;

        return sum;
    }

    // The main function that finds the maximum
    // possible profit from given array of jobs
    ArrayList<Job> findMaxProfit(ArrayList<Job> arr) {

        // Sort arr[] by start time.
        Collections.sort(arr, new Comparator<Job>() {
            @Override
            public int compare(Job x, Job y) {
                return x.start - y.start;
            }
        });

        // sort(arr.begin(), arr.end(), compare);

        // L[i] stores stores Weighted Job Scheduling of
        // job[0..i] that ends with job[i]
        ArrayList<ArrayList<Job>> L = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            L.add(new ArrayList<>());
        }

        // L[0] is equal to arr[0]
        L.get(0).add(arr.get(0));

        // Start from index 1
        for (int i = 1; i < arr.size(); i++) {

            // For every j less than i
            for (int j = 0; j < i; j++) {

                // L[i] = {MaxSum(L[j])} + arr[i] where j < i
                // and arr[j].finish <= arr[i].start
                if ((arr.get(j).finish <= arr.get(i).start) && (findSum(L.get(j)) > findSum(L.get(i)))) {
                    ArrayList<Job> copied = new ArrayList<>(L.get(j));
                    L.set(i, copied);
                }
            }
            L.get(i).add(arr.get(i));
        }

        ArrayList<Job> maxChain = new ArrayList<>();

        // Find one with max profit
        for (int i = 0; i < L.size(); i++)
            if (findSum(L.get(i)) > findSum(maxChain))
                maxChain = L.get(i);

        return maxChain;
    }
}
