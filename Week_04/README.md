# 第四周作业心得
这种的内容设计BFS和DFS，主要的应用还是在树这一数据结构，可见树的内容很重要，三种遍历、层序遍历已经通过两种遍历还原树这些问题都很经典，必须熟练掌握。二分查找的问题则有一个很好的模板，优先记住三要素：递增、有边界和可通过索引访问。至于贪心算法，着重于单步骤的最优化，适用性不大，很多贪心的题可以尝试记住。另外本周周赛答出了两道题，虽然稍稍超时但已经有进步了，下周希望可以在限时内答出两题。
# 本周做过的题目（部分优秀代码总结）
## 1.全排列 permute
依然是使用回溯法，不太容易想，必须牢记。
track这个数组标记每个数字是否遍历过。
```
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] track = new int[nums.length];
        if (nums.length == 0) return res;
        backtrack(res, nums, track, new ArrayList<Integer>());
        return res;
    }
    public void backtrack(List<List<Integer>> list, int[] nums, int[] track, ArrayList<Integer> temp) {
        //terminator
        if (temp.size() == nums.length) {
            list.add(new ArrayList<>(temp));
            return;
        }
        //process
        for (int i = 0; i < nums.length; i++) {
            if (track[i] == 1) continue;
            temp.add(nums[i]);
            track[i] = 1;
            backtrack(list, nums, track, temp);
            track[i] = 0;
            temp.remove(temp.size() - 1);
        }
    }
}
```
## 2.买卖股票的最佳时机
不断更新最小买入值和最大的收益。
```
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return profit;
    }
}
```
## 3.二叉树的层序遍历
BFS的经典应用，使用一个队列来完成。每次循环将当前队列poll出来的node的值放入数组，然后将其左右子节点pop进队列即可。
```
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
               List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> dk = new ArrayDeque<>();
        if (root == null) return res;
        dk.add(root);
        while (!dk.isEmpty()) {
            int count = dk.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = dk.poll();
                temp.add(node.val);
                if (node.left != null) {
                    dk.add(node.left);
                }
                if (node.right != null) {
                    dk.add(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
}
```
## 4.岛屿数量
思路是首先二重循环遍历所有字符串，一旦检测到’1‘，就岛屿计数加一，且讲其置为’0‘。同时把这一字符串上下左右都置为’0‘。直到完成所有检测，这是一种典型的DFS问题。
```
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                 dfs(grid, i, j);
                 count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
```
## 5.分发饼干
贪心算法。两个数组分别使用一个指针进行匹配，饼干尺寸满足就计数加一，否则饼干数组指针加一。
```
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        int count = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
                count++;
            }
            else {
                j++;
            }
        }
        return count;
    }
}
```
## 6.搜索排序旋转数组
二分查找，思路比较巧妙。需要分析左右两侧那一边是递增数组，再判断target在哪一侧。
```
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;


        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[0] <= nums[mid] && (target > nums[mid] || target < nums[0])) {
                left = mid + 1;
            }
            else if (target > nums[mid] && target < nums[0]) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return right == left && nums[left] == target ? left : -1;
    }
}
```
