package Order300;

public class T55_jump_game {

  public static void main(String[] args) {
    System.out.println(new Solution().canJump(new int[] { 2, 3, 1, 1, 4 }));
    System.out.println(new Solution().canJump(new int[] { 3, 2, 1, 0, 4 }));
  }

  static class Solution {

    public boolean canJump(int[] nums) {
      if (nums == null || nums.length < 1) {
        return false;
      }
      int iMax = nums.length;
      int rightMax = 0;

      for (int i = 0; i < iMax; i++) {
        if (i <= rightMax) {
          rightMax = Math.max(rightMax, i + nums[i]);
          if (rightMax + 1 >= iMax) return true;
        }
      }

      return false;
    }
  }
}
