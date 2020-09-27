//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 339 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.HashMap;

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length < 0 || postorder.length < 0 || inorder.length != postorder.length) {
            return null;
        }
        HashMap
        return recur(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }
    public TreeNode recur(int[] inorder, int start, int end, int[] postorder, int index) {
        //terminator
        if (start > end || index < 0) {
            return null;
        }
        //process
        TreeNode rt = new TreeNode(index);
        for (int i = start; i < end; i++) {
            if (inorder[i] == rt.val){
                break;
            }
        }
        //drill down
        TreeNode ri = recur(inorder, i + 1, end, postorder, index - 1);
        TreeNode le = recur(inorder, start, i - 1, postorder, index - (end - i) - 1);
        rt.left = le;
        rt.right = ri;

        return rt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
