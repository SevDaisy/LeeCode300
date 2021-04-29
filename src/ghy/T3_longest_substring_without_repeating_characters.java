package ghy;

import java.util.HashMap;

public class T3_longest_substring_without_repeating_characters {

  static class Solution {

    /**
     * 滑动窗口的方式，用Hashmap记录每个存入值的位置，当前位置-去除重复字母的位置+1就是长度。
     * 要注意abba这种形式，两次start，后一次会小于前一次，应该保留最大的。
     * @param s 输入字符串
     * @return 输出最长的不重复字母的子串长度
     */
    public int lengthOfLongestSubstring(String s) {
      int max = 0;
      int start = 0;
      char[] chars = s.toCharArray();
      HashMap<Character, Integer> hashMap = new HashMap<>();
      for (int i = 0; i < chars.length; i++) {
        if (hashMap.containsKey(chars[i])) {
          if (hashMap.get(chars[i]) + 1 > start) {
            start = hashMap.get(chars[i]) + 1;
          }
        }
        hashMap.put(chars[i], i);
        if (i - start + 1 > max) {
          max = i - start + 1;
        }
      }
      return max;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().lengthOfLongestSubstring("abba"));
  }
}
