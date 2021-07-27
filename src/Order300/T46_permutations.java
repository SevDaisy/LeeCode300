package Order300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T46_permutations {

  static class Solution {

    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> out = new ArrayList<>();
      backtrack(
        nums,
        0,
        out,
        new ArrayList<Integer>() {
          {
            for (int x : nums) {
              add(x);
            }
          }
        }
      );
      return out;
    }

    private void backtrack(
      int[] nums,
      int pos,
      List<List<Integer>> ans,
      List<Integer> path
    ) {
      if (pos == nums.length) {
        ans.add(new ArrayList<>(path));
        return;
      }
      for (int i = pos; i < nums.length; i++) {
        // 动态维护数组
        Collections.swap(path, pos, i);
        // 继续递归填下一个数
        backtrack(nums, pos + 1, ans, path);
        // 撤销操作
        Collections.swap(path, pos, i);
      }
    }
  }
}
