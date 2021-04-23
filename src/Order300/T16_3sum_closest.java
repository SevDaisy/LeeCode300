package Order300;

import java.util.Arrays;

public class T16_3sum_closest {

  public static void main(String[] args) {
    System.out.println(
      new Solution().threeSumClosest(new int[] { -1, 2, 1, -4 }, 1) // -> 2
    );
    System.out.println(
      new Solution().threeSumClosest(new int[] { 0, 0, 0 }, 1) // -> 0
    );
    System.out.println(
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
      int sum_3 = 0;
      for (int ai = 0; ai < iMax; ai++) {
        if (ai > 0 && nums[ai - 1] == nums[ai]) {
          continue;
        }
        int bi = ai + 1;
        int ci = iMax - 1;
        /* bi,ci 双指针 左右指针相向而行 */
        while (bi < ci) {
          int cur = nums[ai] + nums[bi] + nums[ci];
          /* 如果当前状态是最优解 直接返回 */
          if (cur == target) {
            return cur;
          }
          /* 如果当前状态值过大，则 ci左移 —— 可以跳过重复项 */
          /* 否则当前状态值过小，则 bi右移 —— 可以跳过重复项 */
          if (cur > target) {
            while (bi < ci && ci < iMax - 1 && nums[ci + 1] == nums[ci]) {
              ci--;
            }
          } else {
            while (bi > ai + 1 && nums[bi - 1] == nums[bi]) {
              bi++;
            }
          }
          /* 调整好 bi、ci 后，更新 sum_3  */
          if (abs(cur - target) < abs(sum_3 - target)) {
            sum_3 = cur;
          }
        }
      }
      return sum_3;
    }
  }
}
