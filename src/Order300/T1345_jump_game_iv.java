package Order300;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class T1345_jump_game_iv {

  public static void main(String[] args) {
    int[] nums;
    // nums = new int[] { 100, -23, -23, 404, 100, 23, 23, 23, 3, 404 };
    // System.out.println(new Solution().minJumps(nums)); // -> 3
    // System.out.println(new Solution_other().minJumps(nums)); // -> 3
    // System.out.println();
    
    // nums = new int[] { 7 };
    // System.out.println(new Solution().minJumps(nums)); // -> 0
    // System.out.println(new Solution_other().minJumps(nums)); // -> 0
    // System.out.println();

    // nums = new int[] { 7, 6, 9, 6, 9, 6, 9, 7 };
    // System.out.println(new Solution().minJumps(nums)); // -> 1
    // System.out.println(new Solution_other().minJumps(nums)); // -> 1
    // System.out.println();

    // nums = new int[] { 6, 1, 9 };
    // System.out.println(new Solution().minJumps(nums)); // -> 2
    // System.out.println(new Solution_other().minJumps(nums)); // -> 2
    // System.out.println();

    // nums = new int[] { 11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13 };
    // System.out.println(new Solution().minJumps(nums)); // -> 3
    // System.out.println(new Solution_other().minJumps(nums)); // -> 3
    // System.out.println();
    
    nums = zyy.BigCaseVal.nums_T1345;// nums_T1345.length = 3549
    System.out.println(new Solution().minJumps(nums)); // -> 30
    System.out.println(new Solution_other().minJumps(nums)); // -> 30
    System.out.println();

  }

  static class Solution {

    static final Comparator<Integer> descendComparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    };

    public int minJumps(int[] arr) {
      if (arr == null || arr.length < 1) {
        return 0;
      }
      int iMax = arr.length;
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
          /* 实际上，对于每个 group，第一次遇到的索引 i 并不需要保存 */
          Queue<Integer> group = new PriorityQueue<Integer>(descendComparator);
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
              /* 不需要 if (!visited[x]) 因为跳跃肯定是往右跳 */
              queue.offer(x);
              System.out.printf("%d ",x);
            }
          }
          /* 试着右走 */
          next = cur + 1;
          if (next < iMax && !visited[next]) {
            queue.offer(next);
            System.out.printf("%d ",next);
          }
          /* 试着左走 */
          next = cur - 1;
          if (next >= 0 && !visited[next]) {
            queue.offer(next);
            System.out.printf("%d ",next);
          }
        }
        System.out.println();
        stepCnt++;
      }

      return -1;
    }
  }

  /**  66ms => 56.19% */
  static class Solution_other {

    static class JumpNode {

      public int index;
      public int value;
      public int cost;

      public JumpNode(int value, int index, int cost) {
        this.value = value;
        this.index = index;
        this.cost = cost;
      }
    }

    public int minJumps(int[] arr) {
      /* 为了方便我对拍，下面这步是不需要的 */
      // 对连在一起的相同数据进行合并 生成新数组
      // List<Integer> list = new ArrayList<>();
      // list.add(arr[0]);
      // int count = 0;
      // for (int i = 1; i < arr.length; i++) {
      //   if (arr[i] - arr[i - 1] == 0) {
      //     count++;
      //     if (count == 2) list.add(arr[i]);
      //   } else {
      //     count = 0;
      //     list.add(arr[i]);
      //   }
      // }
      // arr = new int[list.size()];
      // for (int i = 0; i < arr.length; i++) {
      //   arr[i] = list.get(i);
      // }
      // --------------------------------------

      // 对每个元素值生成他的所有索引位置 方便之后查询，不然每次到一个元素都要循环一遍数组找和他相同的值
      HashMap<Integer, List<Integer>> map = new HashMap<>();
      for (int i = 0; i < arr.length; i++) {
        List<Integer> l = map.getOrDefault(arr[i], new ArrayList<>());
        l.add(i);
        map.put(arr[i], l);
      }

      // 标准的BFS
      HashSet<Integer> visited = new HashSet<>();
      Queue<JumpNode> queue = new LinkedList<>();
      queue.offer(new JumpNode(arr[0], 0, 0));
      visited.add(0);
      while (!queue.isEmpty()) {
        JumpNode node = queue.poll();
        if (node.index == arr.length - 1) return node.cost; else queue.addAll(
          expand(node, visited, arr, map)
        );
      }
      return -1;
    }

    private static List<JumpNode> expand(
      JumpNode node,
      HashSet<Integer> visited,
      int[] arr,
      HashMap<Integer, List<Integer>> map
    ) {
      List<JumpNode> list = new ArrayList<>();
      // 试图扩展到左边的节点
      if (node.index - 1 >= 0 && !visited.contains(node.index - 1)) {
        list.add(
          new JumpNode(arr[node.index - 1], node.index - 1, node.cost + 1)
        );
      }
      // 试图扩展到右边的节点
      if (node.index + 1 < arr.length && !visited.contains(node.index + 1)) {
        list.add(
          new JumpNode(arr[node.index + 1], node.index + 1, node.cost + 1)
        );
      }
      // 试图扩展到相同值的其他元素
      List<Integer> sameValueIndex = map.get(node.value);
      sameValueIndex.forEach(
        i -> {
          if (!visited.contains(i)) list.add(
            new JumpNode(node.value, i, node.cost + 1)
          );
        }
      );

      list.forEach(
        i -> {
          visited.add(i.index);
        }
      );
      return list;
    }
  }
}
