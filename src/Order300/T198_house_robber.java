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
}
