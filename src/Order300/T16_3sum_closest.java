package Order300;

import java.util.Arrays;

public class T16_3sum_closest {

  public static void main(String[] args) {
    System.out.println(
      // new Solution().threeSumClosest(new int[] { -1, 2, 1, -4 }, 1) // -> 2
      // new Solution().threeSumClosest(new int[] { 0, 0, 0 }, 1) // -> 0
      new Solution().threeSumClosest(new int[] { 0, 1, 2 }, 0) // -> 3
    );
  }

  static class Solution {

    int abs(int x) {
      return x > 0 ? x : -x;
    }

    public int threeSumClosest(int[] nums, int target) {
      Arrays.sort(nums);
      int iMax = nums.length;
      int distance = Integer.MAX_VALUE;
      int sum_3 = 0;
      for (int ai = 0; ai < iMax; ai++) {
        if (ai > 0 && nums[ai - 1] == nums[ai]) {
          continue;
        }
        int ci = iMax - 1;
        int goal = target - nums[ai];
        for (int bi = ai + 1; bi < ci; bi++) {
          if (bi > ai + 1 && nums[bi - 1] == nums[bi]) {
            continue;
          }
          while (bi < ci && nums[bi] + nums[ci - 1] > goal) {
            ci--;
          }
          if (bi == ci) {
            break;
          }
          if (distance > abs(nums[bi] + nums[ci] - goal)) {
            distance = abs(nums[bi] + nums[ci] - goal);
            sum_3 = nums[ai] + nums[bi] + nums[ci];
          }
          if (bi != ci - 1 && distance > abs(nums[bi] + nums[ci - 1] - goal)) {
            distance = abs(nums[bi] + nums[ci - 1] - goal);
            sum_3 = nums[ai] + nums[bi] + nums[ci - 1];
          }
          if (distance == 0) {
            return sum_3;
          }
        }
      }
      return sum_3;
    }
  }
}
