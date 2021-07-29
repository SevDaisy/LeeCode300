package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T49_group_anagrams {

  /* 第一次尝试 sort as Key in Map<String,List> 时间 6ms => 98.50% 空间 41.8MB => 21.72% */
  static class Solution {

    /**
     * 思路：
     * 用 Map<String,List<String>> 做映射
     * String 用 sort 后的字符串
     * 缺点：对每个字符串进行解构然后排序，然后还要用Map。在时间上和空间上都比较奢侈。不够精妙。
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
}
