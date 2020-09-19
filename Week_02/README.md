# 第二周学习心得  
 第二周继续学习了一些经典的二维数据结构，包括树、堆和图。以及使用频率很高的哈希表等等。同时每天也有在刷题狂魔组里跟进刷题，总体掌握还可以，同时培养二楼出现问题自己解决的习惯，善用google搜索进入oracle的网页看不同的类的使用方法讲解和对应的各种method的调用。在每次刷题之后学会记录下好的代码，便于以后返回来理解。 
# 这一周做过的题目 (整理部分优秀代码)
## 删除最外层的括号  
用计数的方法，过滤最开始的左括号和右括号。记住Stringbuilder的用法，包括append。  
class Solution {  
    public String removeOuterParentheses(String S) {  
        StringBuilder sb = new StringBuilder();  
        int level = 0;  
        for (char c : S.toCharArray()) {  
            if (c == ')') --level;  
            if (level >= 1) sb.append(c);  
            if (c == '(') ++level;  
        }  
        return sb.toString();  
    }  
}  
## 有效的字母异位词  
可用辅助栈，但耗时较长，直接toCharArray,sort再用Arrays.equal时间较短。  
class Solution {  
    public boolean isAnagram(String s, String t) {  
        char[] s1 = s.toCharArray();  
        char[] t1 = t.toCharArray();  
        Arrays.sort(s1);  
        Arrays.sort(t1);  
        if (Arrays.equals(s1,t1)) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }  
}  
## 字母异位词分组  
同样是转为字符串数组，sort。再用Hashmap，key为排序后的字符串，value为字符串数组。  
最后使用.value()函数。  
class Solution {  
    public List<List<String>> groupAnagrams(String[] strs) {  
        HashMap<String,List<String>> temp = new HashMap<>();  
        for (String i : strs) {   
            char[] s = i.toCharArray();   
            Arrays.sort(s);   
            String key = String.valueOf(s);  
            if (!temp.containsKey(key)) {  
                temp.put(key,new ArrayList());  
            }   
            temp.get(key).add(i);  
        }  
        return new ArrayList<>(temp.values());  
    }           
}        
  
##  二叉树的中序遍历  
递归和迭代（维护一个deque）的两种方法  
deque直接使用pop和push即可，特殊情况使用pollFirst和addFirst      
class Solution {      
    public List<Integer> inorderTraversal(TreeNode root) {   
        List<Integer> res = new ArrayList<>();     
        traversal(root,res);    
        return res;    
    }  
    public void traversal(TreeNode a, List<Integer> b) {   
        if (a == null) {     
            return;     
        }       
        traversal(a.left,b);     
        b.add(a.val);     
        traversal(a.right,b);     
    }     
}     

class Solution {        
    public List<Integer> inorderTraversal(TreeNode root) {        
        List<Integer> res = new ArrayList<Integer>();       
        Deque<TreeNode> stk = new LinkedList<TreeNode>();        
        while (root != null || !stk.isEmpty()) {       
            while (root != null) {       
                stk.push(root);       
                root = root.left;       
            }      
            root = stk.pop();         
            res.add(root.val);       
            root = root.right;      
        }     
        return res;     
    }      
}    
  
##   前k个高频元素    
要注意maxHeap，即PriorityQueue的用法和定义。本题出现频率也较高，后续讲到排序问题会再涉及。  
class Solution {     
    public int[] topKFrequent(int[] nums, int k) {     
        int[] res = new int[k];       
        HashMap<Integer,Integer> temp = new HashMap<>();       
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));      
        for (int n : nums) {      
            temp.put(n, temp.getOrDefault(n,0) + 1);       
        }       
        for (Map.Entry<Integer, Integer> entry : temp.entrySet()) {        
            maxHeap.add(entry);      
        }        
        for (int i = 0; i < k; i++) {     
            Map.Entry<Integer, Integer> entry = maxHeap.poll();    
            res[i] = entry.getKey();      
        }       
        return res;      
    }       
}       
