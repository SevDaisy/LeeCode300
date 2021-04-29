package Order300;

public class T55_jump_game {

  public static void main(String[] args) {
    System.out.println(new Solution().canJump(new int[] { 2, 3, 1, 1, 4 }));
    System.out.println(new Solution().canJump(new int[] { 3, 2, 1, 0, 4 }));
  }

  static class Solution {

    public boolean canJump(int[] nums) {
      if (nums == null || nums.length < 1) return false;
      int iMax = nums.length;
      int rightMax = 0;

      for (int i = 0; i < iMax && i <= rightMax; i++) {
        /* 更新最远可达的位置 */
        rightMax = Math.max(rightMax, i + nums[i]);
        /* 如果最远已经达到或者超过终点了，返回成功 */
        if (rightMax + 1 >= iMax) return true;
      }

      return false;
    }
  }
}
