package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T47_permutations_ii {

  public static void main(String[] args) {
    List<List<Integer>> out;
    out = new Solution().permuteUnique(new int[] { 1, 2, 1 });
    for (List<Integer> line : out) {
      BaseNode.util.errPrintList(
        line,
        String.format("\nline_%d", out.indexOf(line))
      );
    }
  }

  /* 第一次尝试 频率表+回溯 时间 1ms => 99.82% 空间 38.6MB => 97.59% */
  static class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
      /* 排序 */
      Arrays.sort(nums);
      /* 生成频率表 */
      List<int[]> freqs = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i - 1] == nums[i]) {
          freqs.get(freqs.size() - 1)[1] += 1;
        } else {
          freqs.add(new int[] { nums[i], 1 });
        }
      }

      int goal = nums.length;
      int step = 0;
      List<List<Integer>> ans = new ArrayList<>();
      List<Integer> path = new ArrayList<>();

      backtrace(freqs, goal, step, ans, path);

      return ans;
    }

    private void backtrace(
      List<int[]> freqs,
      int goal,
      int step,
      List<List<Integer>> ans,
      List<Integer> path
    ) {
      if (step == goal) {
        ans.add(new ArrayList<>(path));
        return;
      }
      for (int i = 0; i < freqs.size(); i++) {
        int[] pair = freqs.get(i);
        if (pair[1] > 0) {
          pair[1] -= 1;
          path.add(pair[0]);
          backtrace(freqs, goal, step + 1, ans, path);
          pair[1] += 1;
          path.remove(path.size() - 1);
        }
      }
    }
  }
}
