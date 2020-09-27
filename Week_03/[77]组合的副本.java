//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 398 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, n, k, 1, new ArrayList<Integer>());
        return res;
    }
    public void backtrack(List<List<Integer>> list, int n, int k, int index, List<Integer> temp) {
        //terminator
        if (temp.size() == k) {
            list.add(new ArrayList<>(temp));
            return;
        }
       //process drill down
        for (int i = index; i <= n; i++) {
            temp.add(i);
            backtrack(list, n, k, i + 1, temp);
            //reverse the state
            temp.remove(temp.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
