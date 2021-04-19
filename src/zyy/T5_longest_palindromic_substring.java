package zyy;

import java.util.ArrayList;
import java.util.List;

/* URL：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/ */
public class T5_longest_palindromic_substring {

  public static void main(String[] args) {
    System.out.println(new me_中心扩展法().longestPalindrome("hello"));
  }

  static class me_动态规划 {

    /**
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2)
     */
    public String longestPalindrome(String s) {
      int strLength = s.length();
      /* 若字符串长度小于2，则返回自身 */
      if (strLength < 2) {
        return s;
      }
      /* 否则，执行 DP 演算 */
      int maxLen = 1;
      int begin = 0;
      /* dp[i][j] 表示 s[i..j] 是否是回文串 */
      boolean[][] dp = new boolean[strLength][strLength];
      /* 初始化：所有长度为 1 的子串都是回文串 */
      for (int i = 0; i < strLength; i++) {
        dp[i][i] = true;
      }

      char[] charArray = s.toCharArray();
      /* 递推开始 */
      /* 先枚举子串长度 */
      for (int subLenLimit = 2; subLenLimit <= strLength; subLenLimit++) {
        /* 枚举左边界，左边界的上限设置可以宽松一些 */
        for (int left = 0; left < strLength; left++) {
          /* 对于固定的子串长度、自增的左索引，可以计算得对应的右索引 */
          int right = subLenLimit + left - 1;
          /* 如果右索引越界了，则退出当前循环 */
          if (right >= strLength) {
            break;
          }
          /* 如果对于当前状态，不满足 s[left] == s[right]，则dp数组[left][right]标记为 false */
          if (charArray[left] != charArray[right]) {
            dp[left][right] = false;
          } else {
            if (right - left < 3) {
              /* 在满足 s[left] == s[right] 时，若 s.subString().length() = 0～2 则，直接标记dp数组[left][right]=true */
              dp[left][right] = true;
            } else {
              /* 在满足 s[left] == s[right] 时，若 s.subString().length() >=3 则 需要 dp[left][right] = true&&dp[left + 1][right - 1] */
              dp[left][right] = dp[left + 1][right - 1];
            }
          }

          /* 更新：最长回文子串的长度 和 左索引 */
          if (dp[left][right] && right - left + 1 > maxLen) {
            maxLen = right - left + 1;
            begin = left;
          }
        }
      }
      return s.substring(begin, begin + maxLen);
    }
  }

  static class me_中心扩展法 {

    /* 最贴近我原生逻辑的写法 */

    /**
     * 时间复杂度 O(n^2) 其中 n 是字符串的长度。长度为 1 和 2 的回文中心分别有 n 和 n-1 个，每个回文中心最多会向外扩展 O(n) 次。
     * 空间复杂度 O(1)
     */
    public String longestPalindrome(String s) {
      /* 若 s.length() < 2, 则 return s */
      if (s == null || s.length() < 2) {
        return s;
      }
      int subLenMax = 1;
      int left = 0;
      for (int center = 0; center < s.length(); center++) {
        int subLenByCenter = Math.max(
          expandAroundCenter(s, center, center),
          expandAroundCenter(s, center, center + 1)
        );
        if (subLenByCenter > subLenMax) {
          subLenMax = subLenByCenter;
          left = center - subLenByCenter / 2;
        }
      }
      return s.substring(left, subLenMax);
    }

    /* 这个函数名字起的真合适，我就想不到 */
    public int expandAroundCenter(String s, int left, int right) {
      while (true) {
        if (
          s != null &&
          left <= right &&
          left >= 0 &&
          right < s.length() &&
          s.charAt(left) == s.charAt(right)
        ) {
          left--;
          right++;
        } else {
          break;
        }
      }
      return right - left + 1;
    }
  }

  static class Solution_动态规划 {

    /**
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2)
     */
    public String longestPalindrome(String s) {
      int strLength = s.length();
      /* 若字符串长度小于2，则返回自身 */
      if (strLength < 2) {
        return s;
      }
      /* 否则，执行 DP 演算 */
      int maxLen = 1;
      int begin = 0;
      /* dp[i][j] 表示 s[i..j] 是否是回文串 */
      boolean[][] dp = new boolean[strLength][strLength];
      /* 初始化：所有长度为 1 的子串都是回文串 */
      for (int i = 0; i < strLength; i++) {
        dp[i][i] = true;
      }

      char[] charArray = s.toCharArray();
      /* 递推开始 */
      /* 先枚举子串长度 */
      for (int subLenLimit = 2; subLenLimit <= strLength; subLenLimit++) {
        /* 枚举左边界，左边界的上限设置可以宽松一些 */
        for (int left = 0; left < strLength; left++) {
          /* 对于固定的子串长度、自增的左索引，可以计算得对应的右索引 */
          int right = subLenLimit + left - 1;
          /* 如果右索引越界了，则退出当前循环 */
          if (right >= strLength) {
            break;
          }
          /* 如果对于当前状态，不满足 s[left] == s[right]，则dp数组[left][right]标记为 false */
          if (charArray[left] != charArray[right]) {
            dp[left][right] = false;
          } else {
            if (right - left < 3) {
              /* 在满足 s[left] == s[right] 时，若 s.subString().length() = 0～2 则，直接标记dp数组[left][right]=true */
              dp[left][right] = true;
            } else {
              /* 在满足 s[left] == s[right] 时，若 s.subString().length() >=3 则 需要 dp[left][right] = true&&dp[left + 1][right - 1] */
              dp[left][right] = dp[left + 1][right - 1];
            }
          }

          /* 更新：最长回文子串的长度 和 左索引 */
          if (dp[left][right] && right - left + 1 > maxLen) {
            maxLen = right - left + 1;
            begin = left;
          }
        }
      }
      return s.substring(begin, begin + maxLen);
    }
  }

  static class Solution_中心扩展法 {

    /* 最贴近我原生逻辑的写法 */

    /**
     * 时间复杂度 O(n^2) 其中 n 是字符串的长度。长度为 1 和 2 的回文中心分别有 n 和 n-1 个，每个回文中心最多会向外扩展 O(n) 次。
     * 空间复杂度 O(1)
     */
    public String longestPalindrome(String s) {
      if (s == null || s.length() < 1) {
        return "";
      }
      int start = 0, end = 0;
      for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
          start = i - (len - 1) / 2;
          end = i + len / 2;
        }
      }
      return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
      while (
        left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)
      ) {
        --left;
        ++right;
      }
      return right - left - 1;
    }
  }

  static class Solution_Manacher {

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */

    public String longestPalindrome(String s) {
      int start = 0, end = -1;
      StringBuffer t = new StringBuffer("#");
      for (int i = 0; i < s.length(); ++i) {
        t.append(s.charAt(i));
        t.append('#');
      }
      t.append('#');
      s = t.toString();

      List<Integer> arm_len = new ArrayList<Integer>();
      int right = -1, j = -1;
      for (int i = 0; i < s.length(); ++i) {
        int cur_arm_len;
        if (right >= i) {
          int i_sym = j * 2 - i;
          int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
          cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
        } else {
          cur_arm_len = expand(s, i, i);
        }
        arm_len.add(cur_arm_len);
        if (i + cur_arm_len > right) {
          j = i;
          right = i + cur_arm_len;
        }
        if (cur_arm_len * 2 + 1 > end - start) {
          start = i - cur_arm_len;
          end = i + cur_arm_len;
        }
      }

      StringBuffer ans = new StringBuffer();
      for (int i = start; i <= end; ++i) {
        if (s.charAt(i) != '#') {
          ans.append(s.charAt(i));
        }
      }
      return ans.toString();
    }

    public int expand(String s, int left, int right) {
      while (
        left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)
      ) {
        --left;
        ++right;
      }
      return (right - left - 2) / 2;
    }
  }
}
