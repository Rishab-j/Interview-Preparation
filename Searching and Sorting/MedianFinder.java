import java.util.PriorityQueue;

class MedianFinder {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private boolean even;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        even = true;
    }

    public void addNum(int num) {
        // if(maxHeap.size() == 0 || num <= maxHeap.peek()) maxHeap.add(num);
        // else minHeap.add(num);

        // if(maxHeap.size() > minHeap.size() + 1){
        // minHeap.add(maxHeap.remove());
        // }else if(maxHeap.size() < minHeap.size()){
        // maxHeap.add(minHeap.remove());
        // }

        if (even) { // means size of both heap was equal
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        }

        even = !even;
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size() && maxHeap.size() != 0)
            return (maxHeap.peek() + minHeap.peek()) * 1.0 / 2.0;
        else if (maxHeap.size() != minHeap.size())
            return maxHeap.peek();

        return -1;
    }
}