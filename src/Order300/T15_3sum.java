package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T15_3sum {

  public static void main(String[] args) {
    for (List<Integer> tuple : new Solution().threeSum(new int[] { 1, 1, 1 })) {
      // for (List<Integer> tuple : new Solution().threeSum(new int[] { -1, 0, 1, 2, -1, -4 })) {
      // for (List<Integer> tuple : new Solution().threeSum(new int[] { -2, 0, 1, 1, 2 })) {
      for (int nums : tuple) {
        System.out.printf("%d\t", nums);
      }
      System.out.println();
    }
  }

  static class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
      /* 答案中不可以包含重复的三元组,那么 排序+遍历时跳过重复对象 */
      Arrays.sort(nums);

      List<List<Integer>> out = new ArrayList<List<Integer>>(16);
      /* a + b + c = 0 => a+c = -b*/
      int iMax = nums.length;
      for (int ai = 0; ai < iMax; ai++) {
        /* a 跳过重复对象 */
        if (ai > 0 && nums[ai - 1] == nums[ai]) {
          continue;
        }
        int ci = iMax - 1;
        // int bi = ai + 1;
        int target = -nums[ai];/* b+c = -a */
        for (int bi = ai + 1; bi < ci; bi++) {
          /* b 跳过重复对象 */
          if (bi > ai + 1 && nums[bi - 1] == nums[bi]) {
            continue;
          }
          while (bi < ci && nums[bi] + nums[ci] > target) {
            ci--;
          }
          /**
           * 如果 bi == ci
           * 则，首先，bi ci 重复使用了同一元素，因此不能作为答案
           * 其次，下一次循环中 bi++，则 ci-while 循环动弹不得。
           * 也就是说，这样的 bi ≥ bi_now 都不会再有答案了，可以终止这样的 bi-for。
           */
          if (bi == ci) break;
          if (nums[bi] + nums[ci] == target) {
            List<Integer> ans = new ArrayList<>(4);
            ans.add(nums[ai]);
            ans.add(nums[bi]);
            ans.add(nums[ci]);
            out.add(ans);
          }
        }
      }
      return out;
    }
  }
}
