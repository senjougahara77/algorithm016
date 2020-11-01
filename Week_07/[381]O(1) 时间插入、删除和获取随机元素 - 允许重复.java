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
