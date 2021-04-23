package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T18_4sum {

  public static void main(String[] args) {
    System.out.println(
      new Solution().fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0)
    ); // -> [-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]
    System.out.println(new Solution().fourSum(new int[] { 2, 2, 2, 2, 2 }, 8)); // -> [2, 2, 2, 2]
    System.out.println(
      new Solution().fourSum(new int[] { -2, -1, -1, 1, 1, 2, 2 }, 0)
    ); // -> [-2, -1, 1, 2], [-1, -1, 1, 1]
    System.out.println(
      new Solution().fourSum(new int[] { -3, -2, -1, 0, 0, 1, 2, 3 }, 0)
    ); // -> [-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]
  }

  static class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
      List<List<Integer>> answers = new ArrayList<List<Integer>>();
      Arrays.sort(nums);
      int iMax = nums.length;
      for (int ai = 0; ai < iMax; ai++) {
        if (ai > 0 && nums[ai - 1] == nums[ai]) {
          continue;
        }
        for (int bi = ai + 1; bi < iMax; bi++) {
          if (bi > ai + 1 && nums[bi - 1] == nums[bi]) {
            continue;
          }
          int ci = bi + 1;
          int di = iMax - 1;
          while (ci < di) {
            int cur = nums[ai] + nums[bi] + nums[ci] + nums[di];
            if (cur == target) {
              ArrayList<Integer> ans = new ArrayList<>(4);
              ans.add(nums[ai]);
              ans.add(nums[bi]);
              ans.add(nums[ci]);
              ans.add(nums[di]);
              answers.add(ans);
              /* 可能在这个 while 内还有 ci,di 的其他可行解，因此answers.add()后，不能break也不能continue，而应该是重排 ci,di */
              int tmp_ci = ci + 1;
              while (tmp_ci < di && nums[ci] == nums[tmp_ci]) {
                tmp_ci++;
              }
              ci = tmp_ci;
              int tmp_di = di - 1;
              while (tmp_di > ci && nums[di] == nums[tmp_di]) {
                tmp_di--;
              }
              di = tmp_di;
            } else {
              if (cur < target) {
                int tmp_ci = ci + 1;
                while (tmp_ci < di && nums[ci] == nums[tmp_ci]) {
                  tmp_ci++;
                }
                ci = tmp_ci;
              } else {
                int tmp_di = di - 1;
                while (tmp_di > ci && nums[di] == nums[tmp_di]) {
                  tmp_di--;
                }
                di = tmp_di;
              }
            }
          }
        }
      }
      return answers;
    }
  }
}
