package code;

public class Applications{
    int[] array;
    
    public Applications(int array[]){
        this.array = array;
    }

    /**
     * Find the length of the shortest subarray with a sum of equlas or greater than k
     * if not found, return -1
     */
    public int shortestSubarrayWithSum(int k){
            final int n = array.length;
            int ans = n + 1;
            BSTBasedPQ q = new BSTBasedPQ();
            long[] prefix = new long[n + 1];

            for (int i = 0; i < n; ++i)
            prefix[i + 1] = (long) A[i] + prefix[i];

            for (int i = 0; i < n + 1; ++i) {
            while (!q.isEmpty() && prefix[i] - prefix[(int)q.().getValue()] >= k)
                ans = Math.min(ans, i - (int)q.top().getValue());
            while (!q.isEmpty() && prefix[i] <= prefix[q.getLast()])
                q.pollLast();
            q.addLast(i);
}

return ans <= n ? ans : -1;

        return -1;
    }
}