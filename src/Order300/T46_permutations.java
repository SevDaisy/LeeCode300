package Order300;

import java.util.ArrayList;
import java.util.List;

public class T46_permutations {

  /* 第一次尝试 时间 1ms => 93.95% 空间 38.4MB => 90.16% */
  static class Solution {

    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> out = new ArrayList<>();
      backtrack(nums, 0, out, new ArrayList<Integer>());
      return out;
    }

    private void backtrack(
      int[] nums,
      int step,
      List<List<Integer>> ans,
      List<Integer> path
    ) {
      if (step == nums.length) {
        ans.add(new ArrayList<>(path));
        return;
      }
      for (int i = 0; i < nums.length; i++) {
        if (path.contains(nums[i])) continue;
        path.add(nums[i]);
        backtrack(nums, step + 1, ans, path);
        path.remove(path.size() - 1);
      }
    }
  }
}
