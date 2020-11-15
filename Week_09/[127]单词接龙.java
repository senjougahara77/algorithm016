//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 637 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int endidx = wordList.indexOf(endWord);
        if (endidx == -1) {
            return 0;
        }
        wordList.add(endWord);
        Deque<String> dq1 = new ArrayDeque<>();
        Deque<String> dq2 = new ArrayDeque<>();
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        HashSet<String> all = new HashSet<>(wordList);
        int count = 0;
        dq1.add(beginWord);
        dq2.add(endWord);
        set1.add(beginWord);
        set2.add(endWord);
        while (!dq1.isEmpty() && !dq2.isEmpty()) {
            count++;
            if (set1.size() > set2.size()) {
                Deque<String> temp = dq1;
                dq1 = dq2;
                dq2 = temp;
                HashSet<String> tempset = set1;
                set1 = set2;
                set2 = tempset;
            }
            int size1 = dq1.size();
            while (size1-- > 0){
                String a = dq1.poll();
                char[] c = a.toCharArray();
                for (int i = 0; i < c.length; i++) {
                    char c1 = c[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        c[i] = j;
                        String s = new String(c);
                        if (set2.contains(s)) {
                            return count + 1;
                        }
                        if (set1.contains(s)) {
                            continue;
                        }
                        if (all.contains(s)) {
                            dq1.add(s);
                            set1.add(s);
                        }
                    }
                    c[i] = c1;
                }
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
