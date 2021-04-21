package Order300;

import java.util.HashSet;
import java.util.Set;

public class T3_longest_substring_without_repeating_characters {

  public static void main(String[] args) {
    System.out.println(new Solution().lengthOfLongestSubstring("aaa")); // -> 1
    System.out.println(new Solution().lengthOfLongestSubstring("abcde")); // -> 5
    System.out.println(new Solution().lengthOfLongestSubstring("")); // -> 0
    System.out.println(new Solution().lengthOfLongestSubstring("pwwkew")); // -> 3
  }

  static class Solution {

    /**
     * 滑动窗口类问题
     * 双指针 - 左右指针
     *
     * 判断重复字符 HashSet<Character>
     */
    public int lengthOfLongestSubstring(String s) {
      /* 字符串转字符数组以方便遍历 */
      char[] line = s.toCharArray();
      /* 左右指针 */
      int left = -1; // line[left]不在窗口中
      int right = -1; // line[right]在窗口中
      int out = 0; // 保存子串的最大长度。子串长度 = right-left
      /* 窗口数据 —— 需求是去重，所以用HashSet */
      Set<Character> window = new HashSet<>();

      while (left < line.length && right + 1 < line.length) {
        /* 窗口向右生长1位 => 右指针++ */
        right++;
        /**
         * 若 新元素line[right]未重复，则加入窗口，并进入下一个循环
         * 否则，窗口左边界向右收缩 => left++，直到当前元素不在窗口用有重复对象。
         */
        char cur = line[right]; // 这个元素只是为了程序的可读性。可以完全用line[right]替代。
        while (window.contains(cur)) {
          left++;
          window.remove(line[left]);
        }
        window.add(cur);
        out = Math.max(out, right - left);
      }
      return out;
    }
  }
}
