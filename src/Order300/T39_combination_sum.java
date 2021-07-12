package Order300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class T39_combination_sum {

  public static void main(String[] args) {
    List<List<Integer>> out = new Solution()
    .combinationSum(new int[] { 2, 3, 6, 7 }, 7);
    for (List<Integer> list : out) {
      for (int x : list) {
        System.out.printf(" %d", x);
      }
      System.out.println();
    }
  }

  /* 第二次尝试 借鉴别人的回溯 时间 2ms => 99.96% 空间 38.7MB => 40.03%  */
  static class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> answers = new ArrayList<>();

      /* 考虑学习 健壮性考虑 */
      if (candidates == null || candidates.length == 0) return answers;

      findSum(candidates, target, answers, new ArrayList<Integer>(), 0);
      return answers;
    }

    /* 考虑学习 dfs 返回值为 void */
    private void findSum(
      int[] nums,
      int target,
      List<List<Integer>> answers,
      ArrayList<Integer> path,
      int i
    ) {
      if (target == 0) {
        answers.add(new ArrayList<Integer>(path));
        return;
      }
      /* 考虑学习 在这个位置处理 i 的合法越界问题 */
      if (i == nums.length) return;
      findSum(nums, target, answers, path, i + 1);
      if (target - nums[i] >= 0) {
        path.add(nums[i]);
        /* 考虑学习 更新 target 来代替对 lastSum 的依赖!!! */
        findSum(nums, target - nums[i], answers, path, i);
        path.remove(path.size() - 1);
      }
    }
  }

  /* 第一次尝试 回溯 时间 6ms => 20.13% 空间 38.6MB => 68.03% */
  static class Solution_first {

    int targetSum;
    int[] table;
    List<List<Integer>> answers = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      this.table = candidates;
      this.targetSum = target;
      Arrays.sort(this.table);
      backtrace(new ArrayList<Integer>(), 0, 0);
      return answers;
    }

    private boolean backtrace(ArrayList<Integer> path, int lastSum, int pos) {
      if (lastSum == targetSum) {
        answers.add((ArrayList<Integer>) path.clone());
        return true;
      }
      if (lastSum < targetSum) {
        if (pos + 1 < table.length) {
          backtrace(path, lastSum, pos + 1);
        }
        path.add(table[pos]);
        backtrace(path, lastSum + table[pos], pos);
        path.remove(path.size() - 1);
      }
      return false;
    }
  }
}
