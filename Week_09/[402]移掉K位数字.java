//给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。 
//
// 注意: 
//
// 
// num 的长度小于 10002 且 ≥ k。 
// num 不会包含任何前导零。 
// 
//
// 示例 1 : 
//
// 
//输入: num = "1432219", k = 3
//输出: "1219"
//解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
// 
//
// 示例 2 : 
//
// 
//输入: num = "10200", k = 1
//输出: "200"
//解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
// 
//
// 示例 3 : 
//
// 
//输入: num = "10", k = 2
//输出: "0"
//解释: 从原数字移除所有的数字，剩余为空就是0。
// 
// Related Topics 栈 贪心算法 
// 👍 403 👎 0


import java.util.ArrayDeque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) return "0";
        Deque<Character> deque= new LinkedList<Character>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (k > 0 && !deque.isEmpty() && c < deque.peekLast()) {
                deque.pollLast();
                k--;
            }
            deque.addLast(c);
        }
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            char c = deque.poll();
            if (sb.length() != 0 || c != '0') {
                sb.append(c);
            }
        }
        if (sb.length() == 0) {
            sb.append('0');
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
