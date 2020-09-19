//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 466 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)
