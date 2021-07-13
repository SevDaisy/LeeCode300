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
    .combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8)) {
      for (int x : list) {
        System.out.printf(" %d", x);
      }
      System.out.println();
    }
  }

  static class Solution_2ms {

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      Arrays.sort(candidates);
      dfs(candidates, target, 0);
      return list;
    }

    private void dfs(int[] candidates, int target, int index) {
      if (target == 0) {
        list.add(new ArrayList<>(path));
        return;
      }
      for (int i = index; i < candidates.length; i++) {
        if (candidates[i] <= target) {
          if (i > index && candidates[i] == candidates[i - 1]) {
            continue;
          }
          path.add(candidates[i]);
          dfs(candidates, target - candidates[i], i + 1);
          path.remove(path.size() - 1);
        }
      }
    }
  }

  /* 第二次尝试 改编自 2ms 范程 时间 2ms => 99.94% 空间 38.6% => 64.29% */
  static class Solution {

    int[] nums;
    List<List<Integer>> answers = new ArrayList<List<Integer>>();
    /* path只有尾部操作，所以，其实完全可以用 Deque 来做 */
    ArrayList<Integer> path = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      /* 健壮性考虑 */
      if (candidates == null || candidates.length == 0) return answers;
      this.nums = candidates;
      Arrays.sort(this.nums);
      // BaseNode.util.errPrintList(new ArrayList<Integer>() {{for (int i = 0; i < nums.length; i++) {add(nums[i]);}}},"nums");
      backtrace(target, 0);
      return answers;
    }

    private void backtrace(int target, int pos) {
      if (target == 0) {
        answers.add(new ArrayList<Integer>(path));
        return;
      }

      for (int i = pos; i < nums.length; i++) {
        if (nums[i] <= target) {
          /* 对于多次访问到的重复元素进行跳过 */
          if (i > pos && nums[i] == nums[i - 1]) continue;
          path.add(nums[i]);
          backtrace(target - nums[i], i + 1);/* 注意！此处应为 i+1 而非 pos+1 */
          path.remove(path.size() - 1);
        }
      }
    }
  }

  /* 第一次尝试 频率表 + 回溯 时间 4ms => 54.52% 空间 38.7MB => 39.91% */
  static class Solution_first {

    /* 用一个 int[2] 代替 元组（x,y） */
    List<int[]> numList = new ArrayList<int[]>();
    List<List<Integer>> answers = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      /* 健壮性考虑 */
      if (candidates == null || candidates.length == 0) return answers;
      /**
       * 既要【去除重复解】又要【可以多次使用相同的数字】
       * 处理：
       * 第一步，排序
       * 第二步，过滤原数组为[(x,cnt)]
       * 例如 2，5，2，1，2 => [(1,1) (2,3) (5,1)]
       * 第三步，基于过滤后的数组来排序（而非基于原数组）
       */
      Arrays.sort(candidates);
      for (int x : candidates) {
        int index = numList.size() - 1;
        if (index < 0 || numList.get(index)[0] != x) {
          numList.add(new int[] { x, 1 });
        } else {
          numList.get(index)[1]++;
        }
      }
      backtrace(target, 0, new ArrayList<Integer>());
      return answers;
    }

    private void backtrace(int target, int pos, ArrayList<Integer> path) {
      if (target == 0) {
        answers.add(new ArrayList<Integer>(path));
        return;
      }
      if (pos == numList.size()) return;

      /* 根据 x,cnt 进行 accept x with {$timeCnt} times 多条递归路径 timeCnt in [0,cnt] */
      int x = numList.get(pos)[0];
      int cnt = numList.get(pos)[1];
      for (int i = 0; i <= cnt; i++) {
        if (target - x * i >= 0) {
          if (i > 0) path.add(x);
          backtrace(target - x * i, pos + 1, path);
        }
      }
      /**
       * 弹出之前往 path 中添加的 x
       * 这段代码确实有趣, 请试着理解为什么注释中的这段代码不行
       * int size = path.size();
       * for (int i = 1; i <= cnt && (size - i) >= 0; i++) {
       *   path.remove(size - i);
       * }
       * 答案是，缺少了条件 path.get(size-i) == x
       * 正确的写法 如下
       * int size = path.size();
       * for (
       *   int i = 1;
       *   i <= cnt && (size - i) >= 0 && path.get(size - i) == x;
       *   i++
       * ) {
       *   path.remove(size - i);
       * }
       */
      for (
        int lastIndex = path.size() - 1;
        lastIndex >= 0 && path.get(lastIndex) == x;
        lastIndex = path.size() - 1
      ) {
        path.remove(lastIndex);
      }
    }
  }
}
