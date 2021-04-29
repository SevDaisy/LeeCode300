package Order300;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class T1345_jump_game_iv {

  public static void main(String[] args) {
    int[] nums;
    nums = new int[] { 100, -23, -23, 404, 100, 23, 23, 23, 3, 404 };
    System.out.println(
      "\nanswer is 3 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 3

    nums = new int[] { 7 };
    System.out.println(
      "\nanswer is 0 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 0

    nums = new int[] { 7, 6, 9, 6, 9, 6, 9, 7 };
    System.out.println(
      "\nanswer is 1 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 1

    nums = new int[] { 6, 1, 9 };
    System.out.println(
      "\nanswer is 2 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 2

    nums = new int[] { 11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13 };
    System.out.println(
      "\nanswer is 3 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 3

    nums = BaseNode.BigCaseVal.nums_T1345; // nums_T1345.length = 3549
    System.out.println(
      "\nanswer is 30 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 30
  }

  static class Solution {

    static class state implements Comparable<state> {

      int index;
      int stepCnt;
      int cost;

      public state(int index, int stepCnt, int target) {
        this.index = index;
        this.stepCnt = stepCnt;
        // this.cost = target - index + stepCnt*1024;
        this.cost = stepCnt;
      }

      @Override
      public int compareTo(state o) {
        return this.cost - o.cost;
      }
    }

    static final Comparator<Integer> descendComparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    };

    public int minJumps(int[] arr) {
      // System.err.println("\n============================================================");
      // BaseNode.util.errPrintList(Arrays.asList(arr), "arr");
      // System.err.print("arr" + " is ");
      // if (arr.length < 99) for (int x : arr) {System.err.print(" " + x);}
      // System.err.println();
      if (arr == null || arr.length < 1) {
        return 0;
      }
      int iMax = arr.length;
      Queue<state> queue = new PriorityQueue<state>();
      /* 对 索引 的 访问记录 */
      // boolean[] visited = new boolean[iMax];/* boolean 默认初始值为 false */
      boolean[] visited = new boolean[iMax];
      /* 对 跳转值 的 访问记录 */
      HashSet<Integer> valSet = new HashSet<>(1024);
      /* 记录可跳转的组 */
      HashMap<Integer, Queue<Integer>> valGroup = new HashMap<Integer, Queue<Integer>>(
        1024
      );

      for (int i = 0; i < iMax; i++) {
        int cur = arr[i];
        if (valGroup.containsKey(cur)) {
          /**
           * 其实这个 group 最好是降序排序的
           * // 可能的优化方向: 用 降序 排序的 PriorityQueue 代替 ArrayList
           * 当前 group 已经是 PriorityQueue
           */
          valGroup.get(cur).offer(i);
        } else {
          /**
           * // 实际上，对于每个 group，第一次遇到的索引 i 并不需要保存
           * 上面这个想法是错的。详见 ·试着跳跃· 之后的注释
           */
          Queue<Integer> group = new PriorityQueue<Integer>(descendComparator);
          group.offer(i);
          valGroup.put(cur, group);
        }
      }
      state cur;
      int target = iMax - 1;
      int next;
      int curStep, curIndex, curVal;
      Queue<Integer> nextJumps;
      queue.offer(new state(0, 0, target));
      visited[0] = true;
      while (!queue.isEmpty()) {
        // System.err.printf("%d -> ", levelSize);
        /* 取 节点状态 */
        cur = queue.poll();
        /* 取 索引 */
        curIndex = cur.index;
        /* 取 索引对应值 */
        curVal = arr[curIndex];
        /* 取当前步数 */
        curStep = cur.stepCnt;
        /* 判断终点 */
        if (curIndex == target) {
          return curStep;
        }
        /**
         * //索引 标记访问
         * // visited[cur] = true;
         **/
        /* 试着跳跃 */
        if (valSet.add(curVal) && valGroup.containsKey(curVal)) {
          nextJumps = valGroup.get(curVal);
          for (Integer x : nextJumps) {
            /**
             * // 不需要 if (!visited[x]) 因为跳跃肯定是往右跳
             * 上面这个想法是错误的！考虑 A1 ... B1 ... B2 A2
             * 可能会因为 A 就 A1 -> A2
             * 然后 A2 向左走是 B2
             * 结果现在 B2 跳不到 B1 了！—— 因为 group 中只存了 B2
             * 甚至会 B2 跳到 B2 ！—— 因为我这边取消了 if (!visited[x])
             **/
            if (!visited[x]) {
              visited[x] = true;
              queue.offer(new state(x, curStep + 1, target));
              // System.err.printf("%d ", x);
            }
          }
        }
        /* 试着右走 */
        next = curIndex + 1;
        if (next < iMax && !visited[next]) {
          visited[curIndex] = true;
          queue.offer(new state(next, curStep + 1, target));
          // System.err.printf("%d ", next);
        }
        /* 试着左走 */
        next = curIndex - 1;
        if (next >= 0 && !visited[next]) {
          visited[curIndex] = true;
          queue.offer(new state(next, curStep + 1, target));
          // System.err.printf("%d ", next);
        }
      }
      // System.err.println();
      // stepCnt++;
      return -1;
    }
  }
}
