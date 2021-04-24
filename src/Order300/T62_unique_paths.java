package Order300;

public class T62_unique_paths {

  public static void main(String[] args) {
    System.out.println(new Solution().uniquePaths(7, 3)); // -> 28
  }

  static class Solution {

    public int uniquePaths(int m, int n) {
      int dp[][] = new int[m][n];
      // dp[0][0] = 0;
      for (int mi = 0; mi < m; mi++) {
        dp[mi][0] = 1;
      }
      for (int ni = 0; ni < n; ni++) {
        dp[0][ni] = 1;
      }

      for (int mi = 1; mi < m; mi++) {
        for (int ni = 1; ni < n; ni++) {
          dp[mi][ni] = dp[mi - 1][ni] + dp[mi][ni - 1];
        }
      }
      return dp[m - 1][n - 1];
    }
  }
}
