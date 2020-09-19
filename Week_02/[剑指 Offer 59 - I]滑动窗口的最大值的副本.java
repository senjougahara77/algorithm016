//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 Sliding Window 
// 👍 113 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k<=0) {
            return new int[0];
        }
        int index = 0;
        int[] res1 = new int[nums.length - k + 1];
        Deque<Integer> res = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (!res.isEmpty() && res.peekFirst() <= i - k) {
                res.pollFirst();
            }
            while (!res.isEmpty() && nums[res.peekLast()] < nums[i]) {
                res.pollLast();
            }
            res.addLast(i);
            if (i >= k - 1) {
                res1[index] = nums[res.peekFirst()];
                index++;
            }
        }
        return res1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
