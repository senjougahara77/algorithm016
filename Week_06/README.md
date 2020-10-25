# 第六周作业心得
这周依然打了周赛，因为有些事作业提交的匆忙。每天固定打卡刷题群已经每日一题收获很大，同时积累了一些好的代码。对于动态规划的理解更深了，学会如何寻找最优子结构和如何写dp方程。
# 本周做过的题目（部分优秀代码总结）
## 1.比较含退格的字符串
倒序遍历两个字符串，利用stringbuilder来完成重构，每次遇到退格字符#就计数，没遇到且计数为零就append。
最后直接利用toString转换，再用equals比较。
```
//给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。 
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ab#c", T = "ad#c"
//输出：true
//解释：S 和 T 都会变成 “ac”。
// 
//
// 示例 2： 
//
// 输入：S = "ab##", T = "c#d#"
//输出：true
//解释：S 和 T 都会变成 “”。
// 
//
// 示例 3： 
//
// 输入：S = "a##c", T = "#a#c"
//输出：true
//解释：S 和 T 都会变成 “c”。
// 
//
// 示例 4： 
//
// 输入：S = "a#c", T = "b"
//输出：false
//解释：S 会变成 “c”，但 T 仍然是 “b”。 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 200 
// 1 <= T.length <= 200 
// S 和 T 只含有小写字母以及字符 '#'。 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// 
// Related Topics 栈 双指针 
// 👍 206 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }
    public String build(String S) {
        StringBuilder sb = new StringBuilder();
        char[] c = S.toCharArray();
        int count = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == '#') {
                count++;
                continue;
            }
            else if (count > 0) {
                count--;
                continue;
            }
            else if (count == 0) {
                sb.append(c[i]);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
## 2.乘积最大子数组
类似最大子序和，但是每次要保存最大值和最小值，因为如果有负数进来，两者会互换。
```
class Solution {
    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = min;
                min = max;
                max = temp;
            }
            min = Math.min(min * nums[i], nums[i]);
            max = Math.max(max * nums[i], nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
```
## 3.重排链表
用快慢指针找到中间点，然后将两个子链表分开。
翻转后一个子链表，此处很重要！
再将两个子链表分别互相依次排列。
```
//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 426 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode pre = null;
        ListNode cur = mid, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ListNode l1 = head, l2 = pre;
        while(l1 != null && l2 != null)
        {
            cur = l1.next;
            pre = l2.next;
            l1.next = l2;
            l1 = cur;
            l2.next = l1;
            l2 = pre;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
## 4.划分字母区间
首先统计每个字母的最后出现位置，数组优于map。
再利用双指针，一旦某个字母达到了最后出现位置，且当前位置是遍历到此处的max值，就利用双指针进行切割，计入list。
```
//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心算法 双指针 
// 👍 351 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        char[] chars = S.toCharArray();
        int[] temp = new int[26];
        for (int i = 0; i < S.length(); i++) {
            temp[chars[i] - 'a'] = i;
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < S.length(); i++) {
            right = Math.max(right, temp[chars[i] - 'a']);
            if (i == right) {
                res.add(right - left + 1);
                left = right + 1;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
## 5.打家劫舍
某一个房子是否打劫，可以用二维dp来存储更多信息，但是空间复杂度变大。
一维dp也可以解决，但是当dp方程只出现两三项的时候可以借鉴斐波那契那道题，用pre和cur反复迭代完成。
```
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1127 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int pre = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = cur;
            cur = Math.max(cur, pre + nums[i]);
            pre = temp;
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
## 6.回文链表
用栈或者队列耗时很长，应当先用快慢指针找到中点，再反转后子链表，然后依次进行比较。
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;
        slow = reverse(slow);
        ListNode temp = head;
        while (slow != null) {
            if (temp.val != slow.val) {
                return false;
            }
            temp = temp.next;
            slow = slow.next;
        }
        return true;
    }


    public ListNode reverse(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
```
## 7.视频拼接
记录每秒可以到达的最大位置，类似跳跃游戏那道题。得到maxend数组。
再进行遍历maxend数组，跳到最远处last，如果i到达last，说明last没有更新，返回-1；
如果到达上次到达的点pre，则res++，pre更新为last。
```
//你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。 
//
// 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如
//片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。 
//
// 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。 
//
//
// 
//
// 示例 1： 
//
// 
//输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
//输出：3
//解释：
//我们选中 [0,2], [8,10], [1,9] 这三个片段。
//然后，按下面的方案重制比赛片段：
//将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
//现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
// 
//
// 示例 2： 
//
// 
//输入：clips = [[0,1],[1,2]], T = 5
//输出：-1
//解释：
//我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
// 
//
// 示例 3： 
//
// 
//输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],
//[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
//输出：3
//解释： 
//我们选取片段 [0,4], [4,7] 和 [6,9] 。
// 
//
// 示例 4： 
//
// 
//输入：clips = [[0,4],[2,8]], T = 5
//输出：2
//解释：
//注意，你可能录制超过比赛结束时间的视频。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= clips.length <= 100 
// 0 <= clips[i][0] <= clips[i][1] <= 100 
// 0 <= T <= 100 
// 
// Related Topics 动态规划 
// 👍 175 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int videoStitching(int[][] clips, int T) {
        int[] maxend = new int[T];
        for (int[] c : clips) {
            if (c[0] < T) {
                maxend[c[0]] = Math.max(maxend[c[0]], c[1]);
            }
        }
        int pre = 0;
        int last = 0;
        int res = 0;
        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxend[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                res++;
                pre = last;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
