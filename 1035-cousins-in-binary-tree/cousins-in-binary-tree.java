class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean isXexist = false;
            boolean isYexist = false;

            for (int i = 0; i < levelSize; i++) {
                TreeNode temp = queue.poll();
                boolean xSameParent = false;
                boolean ySameParent = false;

                if (temp.val == x)
                    isXexist = true;
                if (temp.val == y)
                    isYexist = true;

                if (temp.left != null) {
                    queue.add(temp.left);
                    if (temp.left.val == x)
                        xSameParent = true;
                    if (temp.left.val == y)
                        ySameParent = true;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    if (temp.right.val == x)
                        xSameParent = true;
                    if (temp.right.val == y)
                        ySameParent = true;
                }

                if (xSameParent && ySameParent)
                    return false;
            }
            if (isXexist && isYexist)
                return true;
        }

        return false;
    }
}