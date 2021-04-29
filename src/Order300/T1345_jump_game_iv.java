package Order300;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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

    nums = new int[] { 7, 7, 2, 1, 7, 7, 7, 3, 4, 1 };
    System.out.println(
      "\nanswer is 3 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 3

    nums = BaseNode.BigCaseVal.nums_T1345; // nums_T1345.length = 3549
    System.out.println(
      "\nanswer is 30 but " + new Solution().minJumps(nums) + "\n"
    ); // -> 30
  }

  /** 普通 BFS 85～90ms */
  static class Solution {

    static final Comparator<Integer> descendComparator = new Comparator<Integer>() {
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    };

    public int minJumps(int[] arr) {
      if (arr == null || arr.length < 1) {
        return 0;
      }

      int iMax = arr.length;
      /* 剪枝 合并数组中连续的相同项为相同的两项 48ms => 94.36% */
      LinkedList<Integer> list = new LinkedList<>();
      int last = arr[0];
      int count = 0;
      list.add(last);
      for (int x : arr) {
        if (x == last) {
          if (++count == 2) {
            list.add(last);
          }
        } else {
          count = 0;
          last = x;
          list.add(last);
        }
      }
      iMax = list.size();
      arr = new int[iMax];
      int xi = 0;
      for (int x : list) arr[xi++] = x;

      Queue<Integer> queue = new LinkedList<>();
      /* 对 索引 的 访问记录 */
      boolean[] visited = new boolean[iMax];/* boolean 默认初始值为 false */
      /* 对 跳转值 的 访问记录 */
      HashSet<Integer> valSet = new HashSet<>();
      /* 记录可跳转的组 */
      HashMap<Integer, Queue<Integer>> valGroup = new HashMap<Integer, Queue<Integer>>();

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
           * 上面这个想法是错的。详见 line: [114 ~ 121]
           */
          Queue<Integer> group = new PriorityQueue<Integer>(descendComparator);
          group.offer(i);
          valGroup.put(cur, group);
        }
      }

      int cur, curVal;
      int target = iMax - 1;
      int next;
      int stepCnt = 0;
      int levelSize;
      Queue<Integer> nextJumps;
      queue.offer(0);
      while (!queue.isEmpty()) {
        levelSize = queue.size();
        while (levelSize-- > 0) {
          /* 取 索引 */
          cur = queue.poll().intValue();
          /* 取 索引对应值 */
          curVal = arr[cur];
          /* 判断终点 */
          if (cur == target) {
            return stepCnt;
          }
          /* 索引 标记访问 */
          visited[cur] = true;
          /* 试着跳跃 */
          if (valSet.add(curVal) && valGroup.containsKey(curVal)) {
            nextJumps = valGroup.get(curVal);
            for (int x : nextJumps) {
              /**
               * // 不需要 if (!visited[x]) 因为跳跃肯定是往右跳
               * 上面这个想法是错误的！考虑 A1 ... B1 ... B2 A2
               * 可能会因为 A 就 A1 -> A2
               * 然后 A2 向左走是 B2
               * 结果现在 B2 跳不到 B1 了！—— 因为 group 中只存了 B2
               * 甚至会 B2 跳到 B2 ！—— 因为我这边取消了 if (!visited[x])
               **/
              if (!visited[x]) {
                queue.offer(x);
              }
            }
          }
          /* 试着右走 */
          next = cur + 1;
          if (next < iMax && !visited[next]) {
            queue.offer(next);
          }
          /* 试着左走 */
          next = cur - 1;
          if (next >= 0 && !visited[next]) {
            queue.offer(next);
          }
        }
        stepCnt++;
      }

      return -1;
    }
  }

  /** 退化为 BFS 的 AStar 100ms+ */
  static class Solution_Astar_BFS {

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

      public int compareTo(state o) {
        return this.cost - o.cost;
      }
    }

    static final Comparator<Integer> descendComparator = new Comparator<Integer>() {
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    };

    public int minJumps(int[] arr) {
      if (arr == null || arr.length < 1) {
        return 0;
      }
      int iMax = arr.length;
      Queue<state> queue = new PriorityQueue<state>();
      boolean[] visited = new boolean[iMax];
      HashSet<Integer> valSet = new HashSet<>(1024);
      HashMap<Integer, Queue<Integer>> valGroup = new HashMap<Integer, Queue<Integer>>(
        1024
      );

      for (int i = 0; i < iMax; i++) {
        int cur = arr[i];
        if (valGroup.containsKey(cur)) {
          valGroup.get(cur).offer(i);
        } else {
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
        cur = queue.poll();
        curIndex = cur.index;
        curVal = arr[curIndex];
        curStep = cur.stepCnt;
        if (curIndex == target) {
          return curStep;
        }
        if (valSet.add(curVal) && valGroup.containsKey(curVal)) {
          nextJumps = valGroup.get(curVal);
          for (Integer x : nextJumps) {
            if (!visited[x]) {
              visited[x] = true;
              queue.offer(new state(x, curStep + 1, target));
            }
          }
        }
        next = curIndex + 1;
        if (next < iMax && !visited[next]) {
          visited[curIndex] = true;
          queue.offer(new state(next, curStep + 1, target));
        }
        next = curIndex - 1;
        if (next >= 0 && !visited[next]) {
          visited[curIndex] = true;
          queue.offer(new state(next, curStep + 1, target));
        }
      }
      return -1;
    }
  }
}
