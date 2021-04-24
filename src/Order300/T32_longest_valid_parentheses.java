package Order300;

public class T32_longest_valid_parentheses {

  static class Solution {

    public int longestValidParentheses(String s) {
      int leftCnt = 0;
      int max = 0;
      int pair = 0;
      // int iMax = s.length();
      for (char x : s.toCharArray()) {
        if (x == '(') {
          leftCnt++;
        } else {
          if (pair < leftCnt) {
            pair++;
          } else {
            pair = 0;
            leftCnt = 0;
          }
        }
        max = Math.max(pair << 1, max);
      }

      return max;
    }
  }
}
