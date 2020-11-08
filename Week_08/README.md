# 第八周学习心得
本周的cache等内容对于之前学习过计算机组成原理的我来说还是比较熟悉的，布隆过滤器则更偏向于理解。所以重点就在于各种排序算法，特别是三种o(nlogn)的算法，必须再抽时间多写几次，其他的基础排序算法的思维方式要理解。另外本周时间原因没有打周赛，希望下周可以继续参加，作为练手很好。下周也即将接近课程结束了，我想再把之前写写过的老题目拿出来在写几次，熟能生巧。

# 本周做过的题目（部分优秀代码总结）
## 1. 求根到叶子节点数字之和
```
遇到树的问题要想到递归，这里的递归也很巧妙。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int sumNumbers(TreeNode root) {
        return recur (root, 0);
    }
    public int recur(TreeNode node, int sum) {
        if (node == null) return 0;
        int temp = 10 * sum + node.val;
        if (node.left == null && node.right == null) {
            return temp;
        }
        return recur(node.left, temp) + recur(node.right, temp);
    }
}
```
## 2.插入区间
```
首先把左边不重合的区间全部加入，然后遍历中间重叠的区间，更新最小值为左端点，最大值为右端点，最后加入新区间。然后把右边不重叠的区间全部加入。
//给出一个无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。 
// Related Topics 排序 数组 
// 👍 273 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0, i = 0;
        while (i < intervals.length && intervals[i][1] < left) {
            res[idx++] = intervals[i++];
        }
        while (i < intervals.length && intervals[i][0] <= right) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res[idx++] = newInterval;
        while (i < intervals.length && intervals[i][0] > right) {
            res[idx++] = intervals[i++];
        }
        return Arrays.copyOf(res, idx);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
## 3.最长上升子序列
```
dp方程: dp[i]是每次一以i结尾的最长子序列。
//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划 
// 👍 1129 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        int res = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

## 4.2的幂
每次排除最低位的1，然后计数加1.
```
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) { return false; }
        return ((n > 0) && ((n & (n - 1)) == 0)) ? true : false;
    }
}


