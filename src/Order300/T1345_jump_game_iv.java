package Order300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class T1345_jump_game_iv {

  public static void main(String[] args) {
    int[] nums = new int[] { 100, -23, -23, 404, 100, 23, 23, 23, 3, 404 };
    System.out.println(new Solution().minJumps(nums));
  }

  static class Solution {

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
      HashMap<Integer, List<Integer>> valGroup = new HashMap<Integer, List<Integer>>();

      for (int i = 0; i < iMax; i++) {
        int cur = arr[i];
        if (valGroup.containsKey(i)) {
          /**
           * 其实这个 group 最好是降序排序的
           * ? 可能的优化方向: 用 降序 排序的 PriorityQueue 代替 ArrayList
           */
          valGroup.get(cur).add(i);
        } else {
          /* 实际上，对于每个 group，第一次遇到的索引 i 并不需要保存 */
          List<Integer> group = new ArrayList<Integer>();
          valGroup.put(i, group);
        }
      }

      int cur, curVal;
      int target = iMax - 1;
      int next;
      int stepCnt = 0;
      int levelSize;
      List<Integer> nextJumps;
      queue.add(0);
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
              queue.add(x);
            }
          }
          /* 试着右走 */
          next = cur + 1;
          if (next < iMax && !visited[next]) {
            queue.add(next);
          }
          /* 试着左走 */
          next = cur - 1;
          if (next >= 0 && !visited[next]) {
            queue.add(next);
          }
        }

        stepCnt++;
      }

      return -1;
    }
  }
}
