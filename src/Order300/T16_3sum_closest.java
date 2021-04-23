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
      int distance = Integer.MAX_VALUE;
      int sum_3 = 0;
      for (int ai = 0; ai < iMax; ai++) {
        if (ai > 0 && nums[ai - 1] == nums[ai]) {
          continue;
        }
        int bi = ai + 1;
        int ci = iMax - 1;
        /* bi,ci 双指针 左右指针相向而行 */
        while (bi < ci) {
          /* 对于当前 <ai>[bi,ci] 更新 cur distance sum_3  */
          int cur = nums[ai] + nums[bi] + nums[ci];
          if (abs(cur - target) < distance) {
            distance = abs(cur - target);
            sum_3 = cur;
          }
          /* 如果当前状态是最优解 直接返回, 否则，调整bi,ci */
          if (distance == 0) {
            return sum_3;
          }
          /* 如果当前状态值过大，则 ci左移 —— 可以跳过重复项 */
          /* 否则当前状态值过小，则 bi右移 —— 可以跳过重复项 */
          if (cur > target) {
            int tmp_ci = ci - 1;
            while (bi < tmp_ci && nums[ci] == nums[tmp_ci]) {
              tmp_ci--;
            }
            ci = tmp_ci;
          } else {
            int tmp_bi = bi + 1;
            while (tmp_bi < ci && nums[bi] == nums[tmp_bi]) {
              tmp_bi++;
            }
            bi = tmp_bi;
          }
        }
      }
      return sum_3;
    }
  }
}
