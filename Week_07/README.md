# 第七周学习心得
感觉这周的课题难度明显上一个档次，比如字典树的题目，是第一次尝试写一个新的数据结构，要把它每一个函数都定义好。到了后面AVL树和红黑树这块，甚至已经没有题目了，只是要求把数据结构的原理理解清楚即可，可见这块的内容本身是有难度的，还是要继续坚持刷题和学习。另外，高阶搜索的内容，包括剪枝、双向BFS等等还需要把课程再看一遍，把这周的内容弄懂之后再开始下一周内容的学习。
# 本周做过的题目（部分优秀代码总结）
## 1.岛屿的周长
利用岛屿的封闭性，只计算左边和上边，然后乘二即可。
```
class Solution {
    public int islandPerimeter(int[][] grid) {
        int circum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i-1][j] == 0) {
                        circum += 2;
                    }
                    if (j == 0 || grid[i][j-1] == 0) {
                        circum += 2;
                    }
                }
            }
        }
        return circum;
    }
}
```
## 2.单词接龙
双向广度优先搜索，使用两个队列，每次交换来从两边进行搜索。同时需要两个HashSet来对visited的单词进行存储。
```
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        Deque<String> dk1 = new ArrayDeque<>();
        Deque<String> dk2 = new ArrayDeque<>();
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        Set<String> allword = new HashSet<>(wordList);
        int count = 0;
        dk1.add(beginWord);
        dk2.add(endWord);
        visited1.add(beginWord);
        visited2.add(endWord);
        while (!dk1.isEmpty() && !dk2.isEmpty()) {
            count++;
            if (dk1.size() > dk2.size()) {
                Deque<String> temp = dk1;
                dk1 = dk2;
                dk2 = temp;
                Set<String> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            int size1 = dk1.size();
            while (size1-- > 0) {
                String s = dk1.poll();
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c1 = chars[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        chars[i] = j;
                        String s1 = new String(chars);
                        if (visited1.contains(s1)) {
                            continue;
                        }
                        if (visited2.contains(s1)) {
                            return count + 1;
                        }
                        if (allword.contains(s1)) {
                            dk1.add(s1);
                            visited1.add(s1);
                        }
                    }
                    chars[i] = c1;
                }
            }
        }
        return 0;
    }
}
```
## 3.实现Trie树（前缀树）
利用哈希表实现，每一级都有一个哈希表，因为都是小写字母也可以用长度26的数组实现。
每一级如果有这个字母就继续下探，否则在数组中创建新的trie树节点。
```
//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 447 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Trie {
    private boolean is_string=false;
    private Trie next[]=new Trie[26];

    public Trie(){}

    public void insert(String word){//插入单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)root.next[w[i]-'a']=new Trie();
            root=root.next[w[i]-'a'];
        }
        root.is_string=true;
    }

    public boolean search(String word){//查找单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)return false;
            root=root.next[w[i]-'a'];
        }
        return root.is_string;
    }

    public boolean startsWith(String prefix){//查找前缀
        Trie root=this;
        char p[]=prefix.toCharArray();
        for(int i=0;i<p.length;++i){
            if(root.next[p[i]-'a']==null)return false;
            root=root.next[p[i]-'a'];
        }
        return true;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
```
## 4.独一无二的出现次数
map的计数方法要记住。
```
//给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。 
//
// 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,2,2,1,1,3]
//输出：true
//解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。 
//
// 示例 2： 
//
// 输入：arr = [1,2]
//输出：false
// 
//
// 示例 3： 
//
// 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 1000 
// -1000 <= arr[i] <= 1000 
// 
// Related Topics 哈希表 
// 👍 90 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return map.size() == new HashSet<>(map.values()).size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
## 5.O(1) 时间插入、删除和获取随机元素 - 允许重复.java
```
//设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。 
//
// 注意: 允许出现重复元素。 
//
// 
// insert(val)：向集合中插入元素 val。 
// remove(val)：当 val 存在时，从集合中移除一个 val。 
// getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。 
// 
//
// 示例: 
//
// // 初始化一个空的集合。
//RandomizedCollection collection = new RandomizedCollection();
//
//// 向集合中插入 1 。返回 true 表示集合不包含 1 。
//collection.insert(1);
//
//// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
//collection.insert(1);
//
//// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
//collection.insert(2);
//
//// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
//collection.getRandom();
//
//// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
//collection.remove(1);
//
//// getRandom 应有相同概率返回 1 和 2 。
//collection.getRandom();
// 
// Related Topics 设计 数组 哈希表 
// 👍 94 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class RandomizedCollection {
    private HashMap<Integer, Set<Integer>> map;
    private List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<Integer, Set<Integer>>();
        list = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        Set<Integer> temp = map.getOrDefault(val, new HashSet<Integer>());
        temp.add(list.size() - 1);
        map.put(val, temp);
        return temp.size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = map.get(val).iterator();
        int i = it.next();
        int lastNum = list.get(list.size() - 1);
        list.set(i, lastNum);
        map.get(val).remove(i);
        map.get(lastNum).remove(list.size() - 1);
        if (i < list.size() - 1) {
            map.get(lastNum).add(i);
        }
        if (map.get(val).size() == 0) {
            map.remove(val);
        }
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get( (int) Math.floor(Math.random() * list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)
```
## 6.数组的相对排序
```//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 84 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] temp = new int[1001];
        int idx = 0;
        for (int i : arr1) {
            temp[i]++;
        }
        for (int i : arr2) {
            while (temp[i] > 0) {
                arr1[idx++] = i;
                temp[i]--;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] > 0) {
                arr1[idx++] = i;
                temp[i]--;
            }
        }
        return arr1;›
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```
