package Order300;

import java.util.HashSet;
import java.util.Set;

public class T1306_jump_game_iii {

  public static void main(String[] args) {
    int[] nums;
    int start;

    nums = new int[] { 4, 2, 3, 0, 3, 1, 2 };
    start = 5;
    System.out.println(new Solution().canReach(nums, start)); // -> true

    nums = new int[] { 4, 2, 3, 0, 3, 1, 2 };
    start = 0;
    System.out.println(new Solution().canReach(nums, start)); // -> true

    nums = new int[] { 3, 0, 2, 1, 2 };
    start = 2;
    System.out.println(new Solution().canReach(nums, start)); // -> false
  }

  static class Solution {

    private boolean step(int[] nums, int cur, Set<Integer> walked) {
      if (cur < 0 || cur >= nums.length) {
        return false;
      }
      if (nums[cur] == 0) {
        return true;
      }
      /* 如果不是第一次加入 walked，则直接退出 */
      if (!walked.add(cur)) {
        return false;
      }
      return (
        step(nums, cur - nums[cur], walked) ||
        step(nums, cur + nums[cur], walked)
      );
    }

    public boolean canReach(int[] arr, int start) {
      if (arr == null || arr.length < 1) {
        return false;
      }
      return step(arr, start, new HashSet<Integer>(arr.length));
    }
  }
}
