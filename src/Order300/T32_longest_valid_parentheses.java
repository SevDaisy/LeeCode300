package Order300;

public class T32_longest_valid_parentheses {

  public static void main(String[] args) {
    System.out.println(new Solution().longestValidParentheses("()")); // -> 2
    System.out.println(new Solution().longestValidParentheses("(")); // -> 0
    System.out.println(new Solution().longestValidParentheses(")")); // -> 0
    System.out.println(new Solution().longestValidParentheses("(()")); // -> 2
    System.out.println(new Solution().longestValidParentheses("(())))")); // -> 4
    System.out.println(new Solution().longestValidParentheses("(())()))")); // -> 6
    System.out.println(new Solution().longestValidParentheses("(()())()))")); // -> 8
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
              // 如果是 ....((..))
              dp[i] = preLen + 2 + dp[i - (dp[i - 1] + 2)];
            } else {
              // 否则是 ((..))
              dp[i] = preLen + 2;
            }
          } else {
            /* 考虑形如 ....)(..)) */
            dp[i] = 0;
          }
        }
        maxAnswer = Math.max(maxAnswer, dp[i]);
      }
      return maxAnswer;
    }
  }
}
