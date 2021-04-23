package Order300;

public class T198_house_robber {

  static class Solution {

    public int rob(int[] nums) {
      /* 鲁棒性 特殊条件 输入为空 */
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int iMax = nums.length;
      /* 鲁棒性 特殊条件 仅有一间房 */
      if (iMax == 1) {
        return nums[0];
      }

      int[] dp = new int[iMax];
      /* dp起点 */
      dp[0] = nums[0];
      dp[1] = Math.max(nums[0], nums[1]);
      for (int i = 2; i < iMax; i++) {
        /* dp递推式 */
        dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
      }
      return dp[iMax - 1];
    }
  }

  /**
   * 原题是：会提醒左右各一家报警
   * 如果改成：会提醒左右各两家报警
   * 则，算法变更如下
   */
  static class Solution_2 {

    public int rob(int[] nums) {
      /* 鲁棒性 特殊条件 输入为空 */
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int iMax = nums.length;
      /* 鲁棒性 特殊条件 仅有一间房 */
      if (iMax == 1) {
        return nums[0];
      }
      /* 鲁棒性 特殊条件 仅有两间房 */
      if (iMax == 2) {
        return Math.max(nums[0], nums[1]);
      }

      int[] dp = new int[iMax];
      /* dp起点 */
      dp[0] = nums[0];
      dp[1] = Math.max(nums[0], nums[1]);
      dp[2] = Math.max(dp[1], nums[2]);
      for (int i = 3; i < iMax; i++) {
        /* dp递推式 */
        dp[i] = Math.max(dp[i - 3] + nums[i], Math.max(dp[i - 1], dp[i - 2]));
      }
      return dp[iMax - 1];
    }
  }
}
