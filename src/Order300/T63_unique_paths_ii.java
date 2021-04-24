package Order300;

public class T63_unique_paths_ii {

  static class Solution {

    /**
     * m := obstacleGrid.length
     * n := obstacleGrid[i].length
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      /* 鲁棒性 特殊条件 输入太少 或者 起点或终点有障碍物 */
      if (
        obstacleGrid == null ||
        obstacleGrid.length < 1 ||
        obstacleGrid[0] == null ||
        obstacleGrid[0].length < 1 ||
        obstacleGrid[0][0] == 1 ||
        obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1
      ) {
        return 0;
      }
      int m = obstacleGrid.length;
      int n = obstacleGrid[0].length;

      int dp[][] = new int[m][n];
      for (int mi = 0; mi < m; mi++) {
        if (obstacleGrid[mi][0] != 1) {
          dp[mi][0] = 1;
        } else {
          break;
        }
      }
      for (int ni = 0; ni < n; ni++) {
        if (obstacleGrid[0][ni] != 1) {
          dp[0][ni] = 1;
        } else {
          break;
        }
      }

      for (int mi = 1; mi < m; mi++) {
        for (int ni = 1; ni < n; ni++) {
          if (obstacleGrid[mi][ni] == 0) {
            dp[mi][ni] = dp[mi - 1][ni] + dp[mi][ni - 1];
          }
        }
      }
      return dp[m - 1][n - 1];
    }
  }
}
