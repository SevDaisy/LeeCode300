package Order300;

import java.util.ArrayList;
import java.util.List;

public class T47_permutations_ii {

  public static void main(String[] args) {
    List<List<Integer>> out;
    out = new Solution().permuteUnique(new int[] { 1, 2, 3 });
    for (List<Integer> line : out) {
      BaseNode.util.errPrintList(
        line,
        String.format("\nline_%d", out.indexOf(line))
      );
    }
  }

  static class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
      // Arrays.sort(nums);
      int step = 0;
      List<List<Integer>> ans = new ArrayList<>();
      List<Integer> path = new ArrayList<>();
      List<Integer> visitedIndex = new ArrayList<>();
      backtrace(nums, step, ans, path, visitedIndex);
      return ans;
    }

    private void backtrace(
      int[] nums,
      int step,
      List<List<Integer>> ans,
      List<Integer> path,
      List<Integer> visitedIndex
    ) {
      if (step == nums.length) {
        ans.add(new ArrayList<>(path));
        // BaseNode.util.errPrintList(path, "path");
        // BaseNode.util.errPrintList(visitedIndex, "visitedIndex");
        return;
      }
      for (int i = 0; i < nums.length; i++) {
        if (visitedIndex.contains(i)) continue;

        visitedIndex.add(i);
        path.add(nums[i]);
        backtrace(nums, step + 1, ans, path, visitedIndex);
        visitedIndex.remove(visitedIndex.size() - 1);
        path.remove(path.size() - 1);
      }
    }
  }
}
