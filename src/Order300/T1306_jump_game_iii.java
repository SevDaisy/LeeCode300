package Order300;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class T1306_jump_game_iii {

  public static void main(String[] args) {
    int[] nums;
    int start;

    nums = new int[] { 4, 2, 3, 0, 3, 1, 2 };
    start = 5;
    System.out.println(new Solution().canReach(nums, start)); // -> true

    nums = new int[] { 4, 2, 3, 0, 3, 1, 2 };
    start = 0;
    System.out.println(new Solution().canReach(nums, start)); // -> true

    nums = new int[] { 3, 0, 2, 1, 2 };
    start = 2;
    System.out.println(new Solution().canReach(nums, start)); // -> false
  }

  /** 递归实现 9ms => 31.71% */
  static class Solution_递归 {

    private boolean step(int[] nums, int cur, Set<Integer> walked) {
      if (cur < 0 || cur >= nums.length) {
        return false;
      }
      if (nums[cur] == 0) {
        return true;
      }
      /* 如果不是第一次加入 walked，则直接退出 */
      if (!walked.add(cur)) {
        return false;
      }
      return (
        step(nums, cur - nums[cur], walked) ||
        step(nums, cur + nums[cur], walked)
      );
    }

    public boolean canReach(int[] arr, int start) {
      if (arr == null || arr.length < 1) {
        return false;
      }
      return step(arr, start, new HashSet<Integer>(arr.length));
    }
  }

  /** 迭代实现 14～16ms */
  static class Solution_迭代 {

    public boolean canReach(int[] arr, int start) {
      if (arr == null || arr.length < 1) {
        return false;
      }
      int iMax = arr.length;
      Set<Integer> visited = new HashSet<Integer>(iMax);
      LinkedList<Integer> stack = new LinkedList<Integer>();
      stack.add(start);
      while (!stack.isEmpty()) {
        int cur = stack.removeLast();
        if (arr[cur] == 0) return true;
        if (visited.add(cur)) {
          if (cur - arr[cur] >= 0) stack.add(cur - arr[cur]);
          if (cur + arr[cur] < iMax) stack.add(cur + arr[cur]);
        }
      }
      return false;
    }
  }

  /* 最终优化版的递归 3ms */
  static class Solution {

    public boolean canReach(int[] arr, int start) {
      boolean[] visited = new boolean[arr.length];
      return step(arr, start, visited);
    }

    private boolean step(int[] arr, int cur, boolean[] visited) {
      if (visited[cur]) return false;
      if (arr[cur] == 0) return true;
      visited[cur] = true;

      return (
        ((cur - arr[cur] >= 0) ? step(arr, cur - arr[cur], visited) : false) ||
        (
          (cur + arr[cur] < arr.length)
            ? step(arr, cur + arr[cur], visited)
            : false
        )
      );
    }
  }
}
