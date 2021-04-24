package Order300;

public class T44_wildcard_matching {

  public static void main(String[] args) {
    System.out.println(new Solution().isMatch("adceb", "*a*b"));
  }

  static class Solution {

    public boolean isMatch(String s, String p) {
      int sMax = s.length();
      int pMax = p.length();

      /* 特别的，在 Java 中 boolean[] 初始化，元素的值是 false */
      /* dp[si][pi] 意为 s[:si) 与 p[:pi) 是否匹配成功 */
      boolean[][] dp = new boolean[sMax + 1][pMax + 1];

      /**
       * dp起点 dp[0][1:pMax+1) := false 且 dp[1:sMax+1)[0] := false
       * 由于在 Java 中 boolean[] 初始化，元素的值是 false，所以这一起点赋值可以在代码中省略
       *
       * dp起点 空s 和 空p 匹配成功 —— dp[0][0] = true;
       * dp起点 p 的开头如果有连续的 n 个 '*' 那么要设置这些 '*' 都匹配空作为起点
       **/
      dp[0][0] = true;
      for (int pi = 1; pi <= pMax; pi++) {
        if (p.charAt(pi - 1) == '*') dp[0][pi] = true; else break;
      }

      /* dp递推开始 */
      for (int si = 1; si <= sMax; si++) {
        for (int pi = 1; pi <= pMax; pi++) {
          if (p.charAt(pi - 1) == '*') {
            /* 这个 * 可以用于匹配空 也可以用于匹配s中一个字符 */
            dp[si][pi] =
              /* 如果这个 '*' 匹配了空 那么，当前状态就和没用过这个 pi 位的 pi-1 一样 */
              dp[si][pi - 1] ||
              /* 如果这个 '*' 匹配了当前s 那么，当前状态就和不需要匹配这个 si 位的 si-1 一样 */
              dp[si - 1][pi];
          } else if (
            p.charAt(pi - 1) == '?' || s.charAt(si - 1) == p.charAt(pi - 1)
          ) {
            /* 如果是 p[pi-1] 是 '?' 或者 s[si-1]==p[pi-1] 的话，那么当前状态就和不需要用这个 pi 位 匹配这个 si 位的 dp[si-1][pi-1] 一样 */
            dp[si][pi] = dp[si - 1][pi - 1];
          }
        }
      }

      return dp[sMax][pMax];
    }
  }
}
