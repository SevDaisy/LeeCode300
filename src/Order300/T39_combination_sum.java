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

  /* 第一次尝试 回溯 时间 6ms => 20.13% 空间 38.6MB => 68.03% */
  static class Solution {

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
