package Order300;

public class T213_house_robber_ii {

  public static void main(String[] args) {
    System.out.println(new Solution().rob(new int[] { 1, 2, 1, 1 })); // -> 3
  }

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
      /* 鲁棒性 特殊条件 仅有两间房 */
      if (iMax == 2) {
        return Math.max(nums[0], nums[1]);
      }

      int dp_a[] = new int[iMax]; // 0~n-2
      int dp_b[] = new int[iMax]; // 1~n-1
      dp_a[0] = nums[0];
      dp_a[1] = Math.max(nums[0], nums[1]);
      for (int i = 2; i < iMax - 1; i++) {
        dp_a[i] = Math.max(dp_a[i - 2] + nums[i], dp_a[i - 1]);
      }
      dp_b[1] = nums[1];
      dp_b[2] = Math.max(nums[1], nums[2]);
      for (int i = 3; i < iMax; i++) {
        dp_b[i] = Math.max(dp_b[i - 2] + nums[i], dp_b[i - 1]);
      }
      return Math.max(dp_a[iMax - 2], dp_b[iMax - 1]);
    }
  }
}
