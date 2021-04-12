package zyy;

import java.util.ArrayList;
import java.util.List;

/* URL：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/ */
public class T5_longest_palindromic_substring {

  public static void main(String[] args) {
    System.out.println(new Solution_Manacher().longestPalindrome("hello"));
  }

  static class Solution_动态规划 {

    /**
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2)
     */
    public String longestPalindrome(String s) {
      int len = s.length();
      if (len < 2) {
        return s;
      }

      int maxLen = 1;
      int begin = 0;
      // dp[i][j] 表示 s[i..j] 是否是回文串
      boolean[][] dp = new boolean[len][len];
      // 初始化：所有长度为 1 的子串都是回文串
      for (int i = 0; i < len; i++) {
        dp[i][i] = true;
      }

      char[] charArray = s.toCharArray();
      // 递推开始
      // 先枚举子串长度
      for (int L = 2; L <= len; L++) {
        // 枚举左边界，左边界的上限设置可以宽松一些
        for (int i = 0; i < len; i++) {
          // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
          int j = L + i - 1;
          // 如果右边界越界，就可以退出当前循环
          if (j >= len) {
            break;
          }

          if (charArray[i] != charArray[j]) {
            dp[i][j] = false;
          } else {
            if (j - i < 3) {
              dp[i][j] = true;
            } else {
              dp[i][j] = dp[i + 1][j - 1];
            }
          }

          // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
          if (dp[i][j] && j - i + 1 > maxLen) {
            maxLen = j - i + 1;
            begin = i;
          }
        }
      }
      return s.substring(begin, begin + maxLen);
    }
  }

  static class Solution_中心扩展法 {

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
