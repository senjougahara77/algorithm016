# 第三周作业心得
上周末第一次尝试周赛，然而深感技术实力有限，题没做出来。不过学到了很多，尝试在有限时间内解决问题也很有挑战性，对于一些题目也有了新的理解。这周课程的内容重点在于分治和回溯，我个人的理解是，这些问题本质上都是递归问题，我们需要找到那个最小的子问题，再利用超哥给的递归代码模板解决，效果很好。而一般来说，排列组合问题、树相关的问题等等基本上都是用递归可以解决，回溯则是在递归时，不单单只向下逐层深入，有时碰到条件还需要返回之前的状态，删除一些状态变量。
# 本周做过的题目（部分优秀代码总结）
## 1.周赛第一题 重新排列单词间的空格  
重点在于如何得到单词的总数，string类问题要想到使用stringbuilder！！可以用append函数，最后用toString转化为字符串。 
```
class Solution {  
    public String reorderSpaces(String text) {  
        // get total spaces     --- n  
        int n = 0;  
        for (char c : text.toCharArray()) {  
            if (c == ' ') n++;   
        }      
          
        // split text, get total words --- m    
        String[] words = text.trim().split("\\s+");  
        int m = words.length;    
              
        // n/(m - 1) spaces per interval  n%(m-1) spaces in end(could be 0)  
        int midSpaces = 0, endSpaces = 0;  
        if (m == 1) {  
            endSpaces = n;  
        } else {  
            midSpaces = n / (m - 1);  
            endSpaces = n % (m - 1);  
        }  
          
		StringBuilder sb = new StringBuilder();  
		// sb append words and midSpaces.When m = 1 automatically skip this part  
        for (int i = 0; i < m - 1; i++) {  
            sb.append(words[i]);  
            for (int j = 0; j < midSpaces; j++) sb.append(" ");  
        }  
        
        // sb append last word and endSpaces   
        sb.append(words[m - 1]);    
        for (int j = 0; j < endSpaces; j++) sb.append(" ");  
          
		return sb.toString();  
    }  
}  
```
## 2.验证二叉搜索树 中序遍历递归判断是否递增  
```
class Solution {  
    long temp = Long.MIN_VALUE;  
    public boolean isValidBST(TreeNode root) {  
        if(root == null) return true;  
        if(!isValidBST(root.left)){  
            return false;  
        }  
        if(temp >= root.val){  
            return false;  
        }  
        temp = root.val;  
        return isValidBST(root.right);  
    }  
}  
```

## 3.从尾到头打印链表
递归或者辅助栈都可以。  
```
class Solution {  
    public int[] reversePrint(ListNode head) {  
        Deque<Integer> dq = new ArrayDeque<>();  
        print(head, dq);  
        int[] res = new int[dq.size()];  
        for (int i = 0; i < res.length; i++) {  
            res[i] = dq.pollFirst();  
        }  
        return res;  
    }   
    public void print(ListNode head, Deque<Integer> dq) {  
        if (head == null) return;  
        print(head.next, dq);  
        dq.addLast(head.val);  
        return;  
    }  
}  
```

## 4.子集
子集、排列组合等问题都是用回溯算法。条件不满足就返回上一层。  
```
class Solution {  
    public List<List<Integer>> subsets(int[] nums) {  
        List<List<Integer>> res = new ArrayList<>();  
        backtrack(0, nums, res, new ArrayList<Integer>());  
        return res;  
    }    

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {  
        res.add(new ArrayList<>(tmp));  
        for (int j = i; j < nums.length; j++) {  
            tmp.add(nums[j]);  
            backtrack(j + 1, nums, res, tmp);  
            tmp.remove(tmp.size() - 1);  
        }  
    }  
}   
```
第二种迭代法，很好理解，两层循环。每一次把新的元素加到之前所有的子集上，构成一些新的子集，放到res中。   
```
class Solution {    
    pub lic List<List<Integer>> subsets(int[] nums) {    
        List<List<Integer>> res = new ArrayList<>();  
        res.add(new ArrayList<>());  
        for (int i = 0; i < nums.length; i++) {  
           int all = res.size();  
            for (int j = 0; j < all; j++) {  
                List<Integer> tmp = new ArrayList<>(res.get(j));  
                tmp.add(nums[i]);  
                res.add(tmp);  
            }  
        }  
        return res;  
    }  
}   
```  
## 5.电话号码的字母组合（高频）
经典的递归方法，与放括号的题目类似。要记住写法！首先需要使用哈希表构建字母与数字的组合。

## 6.二叉树的最近公共祖先  
依然是二叉树最常见的递归方法。  
```
class Solution {   
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {  
        if (root == null) return null;   
        //terminator  
        if (p == root || q == root) return root;  
        //process  
        TreeNode le = lowestCommonAncestor(root.left, p, q);  
        TreeNode ri = lowestCommonAncestor(root.right, p, q);  
        if (le != null && ri != null) return root;  
        else if (le != null) {  
            return le;  
        }  
        else {  
            return ri;  
        }  
    }  
}  
```
## 7.N皇后问题  
回溯问题就是一种递归问题，但是要注意必须在每层结束之后清空参数。   
利用char的二维数组构建棋盘。其中每一行都可以利用  
String s = new String(chess[i]);  
直接转换为string。  
