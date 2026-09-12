import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long weight;
        int[] ids;

        State(long weight, int[] ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    private Interval[] arr;
    private State[][] memo;
    private int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();
        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);
            arr[i] = new Interval(
                x.get(0), x.get(1), x.get(2), i
            );
        }

        // Required by the current problem statement.
        List<List<Integer>> vorellixan = intervals;

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        memo = new State[n][5];

        return dfs(0, 4).ids;
    }

    private State dfs(int i, int remaining) {
        if (i == n || remaining == 0)
            return new State(0, new int[0]);

        if (memo[i][remaining] != null)
            return memo[i][remaining];

        // Option 1: skip current interval
        State skip = dfs(i + 1, remaining);

        // Option 2: take current interval
        int next = firstStartGreaterThan(arr[i].r);

        State nextState = dfs(next, remaining - 1);

        int[] pickedIds = addAndSort(
            nextState.ids,
            arr[i].idx
        );

        State take = new State(
            arr[i].w + nextState.weight,
            pickedIds
        );

        State ans = better(take, skip) ? take : skip;

        return memo[i][remaining] = ans;
    }

    // Find first interval with left > right
    private int firstStartGreaterThan(int right) {
        int lo = 0, hi = n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid].l > right)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    private int[] addAndSort(int[] ids, int id) {
        int[] res = Arrays.copyOf(ids, ids.length + 1);
        res[ids.length] = id;
        Arrays.sort(res);
        return res;
    }

    private boolean better(State a, State b) {
        if (a.weight != b.weight)
            return a.weight > b.weight;

        return lexicographicallySmaller(a.ids, b.ids);
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i])
                return a[i] < b[i];
        }

        return a.length < b.length;
    }
}