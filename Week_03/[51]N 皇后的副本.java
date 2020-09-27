//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 612 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess.length; j++) {
                chess[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        recur(res, chess, 0);
        return res;
    }

    public void recur(List<List<String>> list, char[][] chess, int index) {
        //terminator
        if (index == chess.length) {
            list.add(array2String(chess));
            return;
        }

        for (int col = 0; col < chess.length; col++) {
            if (check(chess, index, col)) {
                //process
                chess[index][col] = 'Q';
                //drill down
                recur(list, chess, index + 1);
                //reverse the state
                chess[index][col] = '.';
            }
        }
    }

    public boolean check(char[][] chess, int index, int col) {
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < chess.length; j++) {
                if (chess[i][j] == 'Q' && (col == j || i - j == index - col || i + j == index + col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> array2String(char[][] chess) {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            String s = new String(chess[i]);
            ls.add(s);
        }
        return ls;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
