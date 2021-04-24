package Order300;

public class T64_minimum_path_sum {

  public static void main(String[] args) {
    System.out.println(
      new Solution()
      .minPathSum(
          new int[][] {
            new int[] { 1, 3, 1 },
            new int[] { 1, 5, 1 },
            new int[] { 4, 2, 1 },
          }
        )
    ); // -> 7
  }

  static class Solution {

    public int minPathSum(int[][] grid) {
      /* 鲁棒性 特殊条件 输入太少 */
      if (
        grid == null || grid.length < 1 || grid[0] == null || grid[0].length < 1
      ) {
        return 0;
      }
      int m = grid.length;
      int n = grid[0].length;
      int dp[][] = new int[m][n];

      dp[0][0] = grid[0][0];
      for (int mi = 1; mi < m; mi++) {
        dp[mi][0] = dp[mi - 1][0] + grid[mi][0];
      }
      for (int ni = 1; ni < n; ni++) {
        dp[0][ni] = dp[0][ni - 1] + grid[0][ni];
      }

      for (int mi = 1; mi < m; mi++) {
        for (int ni = 1; ni < n; ni++) {
          dp[mi][ni] = grid[mi][ni] + Math.min(dp[mi - 1][ni], dp[mi][ni - 1]);
        }
      }
      return dp[m - 1][n - 1];
    }
  }
}
