package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T49_group_anagrams {

  /* 第一次尝试 sort as Key in Map<String,List> 时间 6ms => 98.50% 空间 41.8MB => 21.72% */
  static class Solution_1 {

    /**
     * 思路：
     * 用 Map<String,List<String>> 做映射
     * String 用 sort 后的字符串
     * 缺点：对每个字符串进行解构然后排序，然后还要用Map。在时间上和空间上都比较奢侈。不够精妙。
     *
     * 仰仗 Java STL 本身的性能，最后在时间上击败 98.50% 还是很喜人的。
     * 不过空间上落后于人 —— 21.72% 到确实有点出乎我医疗。我只是额外申请了一个 O(n) 的 Map 作为辅助空间。
     * 去看看别人的写法。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
      Map<String, List<String>> table = new HashMap<String, List<String>>();
      for (String x : strs) {
        String sorted = sortString(x);
        if (table.containsKey(sorted)) {
          table.get(sorted).add(x);
        } else {
          table.put(
            sorted,
            new ArrayList<String>() {
              {
                add(x);
              }
            }
          );
        }
      }
      List<List<String>> ans = new ArrayList<>();
      for (List<String> group : table.values()) {
        ans.add(group);
      }
      return ans;
    }

    private String sortString(String x) {
      char[] arr = x.toCharArray();
      Arrays.sort(arr);
      StringBuilder builder = new StringBuilder();
      for (char c : arr) {
        builder.append(c);
      }
      return builder.toString();
    }
  }

  /* 第二次尝试 质数之积作为【hash】值 时间 4ms => 99.97% 空间 41.4MB => 55.70% */
  static class Solution {

    /**
     * 别人的解法
     * 自己实现了新的哈希函数
     * 已知待 Hash 者必由且仅由 26 个小写字母组成
     * 所以，只需要把 26 个小写字母映射为 26 个质数，则质数之乘积就是 Hash 值
     *
     * 注意 是【乘积】而不是【算术和】
     * 因此，这个方法的缺点在于，不能处理过长的单词串
     */
    public List<List<String>> groupAnagrams(String[] strs) {
      Map<Long, List<String>> table = new HashMap<Long, List<String>>();
      for (String x : strs) {
        long key = hash(x);
        if (table.containsKey(key)) {
          table.get(key).add(x);
        } else {
          table.put(
            key,
            new ArrayList<String>() {
              {
                add(x);
              }
            }
          );
        }
      }
      List<List<String>> ans = new ArrayList<>();
      for (List<String> group : table.values()) {
        ans.add(group);
      }
      return ans;
    }

    private long hash(String s) {
      int[] map = new int[] {
        2,
        3,
        5,
        7,
        11,
        13,
        17,
        19,
        23,
        29,
        31,
        37,
        41,
        43,
        47,
        53,
        59,
        61,
        67,
        71,
        73,
        79,
        83,
        89,
        97,
        101,
      };
      long out = 1;
      for (char c : s.toCharArray()) {
        out *= map[c - 'a'];
      }
      return out;
    }
  }
}
