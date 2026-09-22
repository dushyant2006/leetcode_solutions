class Solution {

    class Node {
        int product;
        int[] count = new int[5];

        Node(int product) {
            this.product = product;
        }
    }

    int n, k;
    Node[] tree;

    Node merge(Node left, Node right) {

        Node res = new Node((left.product * right.product) % k);

        for (int i = 0; i < k; i++) {
            res.count[i] = left.count[i];
        }

        for (int i = 0; i < k; i++) {
            int newRemainder = (left.product * i) % k;
            res.count[newRemainder] += right.count[i];
        }

        return res;
    }

    Node createNode(int value) {

        int p = value % k;

        Node node = new Node(p);
        node.count[p] = 1;

        return node;
    }

    void build(int index, int l, int r, int[] nums) {

        if (l == r) {
            tree[index] = createNode(nums[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(index * 2, l, mid, nums);
        build(index * 2 + 1, mid + 1, r, nums);

        tree[index] = merge(tree[index * 2], tree[index * 2 + 1]);
    }

    void update(int index, int l, int r, int pos, int value) {

        if (l == r) {
            tree[index] = createNode(value);
            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {
            update(index * 2, l, mid, pos, value);
        } else {
            update(index * 2 + 1, mid + 1, r, pos, value);
        }

        tree[index] = merge(tree[index * 2], tree[index * 2 + 1]);
    }

    Node query(int index, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[index];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(index * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(index * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(index * 2, l, mid, ql, qr);
        Node right = query(index * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node result = query(1, 0, n - 1, start, n - 1);

            ans[i] = result.count[x];
        }

        return ans;
    }
}