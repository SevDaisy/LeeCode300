package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T40_combination_sum_ii {

  public static void main(String[] args) {
    for (List<Integer> list : new Solution()
    .combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8)) {
      for (int x : list) {
        System.out.printf(" %d", x);
      }
      System.out.println();
    }
    System.out.println();
    for (List<Integer> list : new Solution()
    .combinationSum2(new int[] { 2, 5, 2, 1, 2 }, 5)) {
      for (int x : list) {
        System.out.printf(" %d", x);
      }
      System.out.println();
    }
  }

  static class Solution {

    int[] nums;
    List<List<Integer>> answers = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      this.nums = candidates;
      Arrays.sort(this.nums);
      /* 健壮性考虑 */
      if (candidates == null || candidates.length == 0) return answers;
      backtrace(target, 1, new ArrayList<Integer>());
      backtrace(
        target - nums[0],
        1,
        new ArrayList<Integer>() {
          {
            add(nums[0]);
          }
        }
      );
      return answers;
    }

    private void backtrace(int target, int pos, ArrayList<Integer> path) {
      if (target == 0) {
        answers.add(new ArrayList<Integer>(path));
        return;
      }
      if (pos == nums.length) return;
      backtrace(target, pos + 1, path);
      if (target - nums[pos] >= 0) {
        path.add(nums[pos]);
        backtrace(target - nums[pos], pos + 1, path);
        path.remove(path.size() - 1);
      }
    }
  }
}
