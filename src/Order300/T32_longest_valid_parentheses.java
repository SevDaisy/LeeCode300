package Order300;

public class T32_longest_valid_parentheses {

  /** 本地调试时用于打印 s 和 dp */
  static void printDP(String s, int[] dp) {
    StringBuilder sdp = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      System.out.print(s.charAt(i));
      System.out.print(" ");
      sdp.append(String.valueOf(dp[i]));
      sdp.append(" ");
    }
    System.out.println();
    System.out.println(sdp.toString());
  }

  public static void main(String[] args) {
    // System.out.println(new Solution().longestValidParentheses("()")); // -> 2
    // System.out.println(new Solution().longestValidParentheses("(")); // -> 0
    // System.out.println(new Solution().longestValidParentheses(")")); // -> 0
    // System.out.println(new Solution().longestValidParentheses("(()")); // -> 2
    // System.out.println(new Solution().longestValidParentheses("(())))")); // -> 4
    System.out.println(new Solution().longestValidParentheses(")(())()))")); // -> 6
    System.out.println(
      new Solution_leecode().longestValidParentheses(")(())()))")
    ); // -> 6
    // System.out.println(new Solution().longestValidParentheses("(()())()))")); // -> 8
  }

  static class Solution {

    public int longestValidParentheses(String s) {
      int iMax = s.length();
      /* 鲁棒性 特殊条件 输入太短 */
      if (s == null || iMax < 2) {
        return 0;
      }
      int[] dp = new int[iMax];
      dp[0] = 0;
      int maxAnswer = 0;
      if ("()".equals(s.substring(0, 2))) {
        maxAnswer = dp[1] = 2;
      } else {
        dp[1] = 0;
      }
      for (int i = 2; i < iMax; i++) {
        if (s.charAt(i) == '(') {
          /* ......( */
          dp[i] = 0;
        } else if (s.charAt(i - 1) == '(') {
          /* ......() */
          dp[i] = dp[i - 2] + 2;
        } else {
          /* ......)) */
          int preLen = dp[i - 1]; // prelen 是不是 0 都OK
          /* 考虑形如 ....((..)) */
          if (i - (preLen + 1) >= 0 && s.charAt(i - (preLen + 1)) == '(') {
            if (i - (preLen + 2) >= 0) {
              /* 如果是 ....((..)) */
              dp[i] = preLen + 2 + dp[i - (dp[i - 1] + 2)];
            } else {
              /* 否则是 ((..)) */
              dp[i] = preLen + 2;
            }
          } else {
            /* 考虑形如 ....)(..)) */
            dp[i] = 0;
          }
        }
        maxAnswer = Math.max(maxAnswer, dp[i]);
      }
      printDP(s, dp);
      return maxAnswer;
    }
  }

  /**
   * 官方的写法比我简洁好多。
   * 可以通过函数 printDP(s,dp) 看出，对于 ")(())()))" 我和官方写法，dp数组值是完全一样的。
   * 再仔细看看代码，感觉官方写法只是省略了 dp[i] = 0 的赋值。
   * 因为Java的数组默认值是0的，所以可以省略，但是没必要。我的代码中还是保留吧。
   **/
  static class Solution_leecode {

    public int longestValidParentheses(String s) {
      int maxans = 0;
      int[] dp = new int[s.length()];
      for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == ')') {
          if (s.charAt(i - 1) == '(') {
            dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
          } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
            dp[i] =
              dp[i - 1] +
              ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) +
              2;
          }
          maxans = Math.max(maxans, dp[i]);
        }
      }
      printDP(s, dp);
      return maxans;
    }
  }
}
