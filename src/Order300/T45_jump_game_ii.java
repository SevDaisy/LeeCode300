package Order300;

public class T45_jump_game_ii {

  static class Solution {

    public int jump(int[] nums) {
      if (nums == null || nums.length < 1) {
        return 0;
      }
      int iMax = nums.length;
      int rightMax = 0;/* 最远可达的右边 */
      int curRightMax = 0;/* 当前这一步内 最远的可达的右边 */
      int stepCnt = 0;/* 计步器 */

      /**
       * 注意，是遍历 [0,iMax-1) 而不是 [0,iMax)
       * 因为，如果 在 i = iMax-1 的时候，i 已经在终点了
       * 正巧，curRightMax 也是 iMax-1，然后就会 计步器再++
       * 那就走到终点后面去了。那就了多走了一步了。
       * 
       * 题目保证了一定会到达终点，所以只要能走到 iMax-2
       * 如果 正好 curRightMax == iMax-2 那么就 stepCnt++ 下一步就是终点没问题
       * 否则 curRightMax > iMax-2，即 curRightMax ≥ iMax-1 所以这一步就足够到达终点
       */
      for (int i = 0; i < iMax - 1; i++) {
        /* 遍历中，每次都要更新 最远可达的右边 */
        rightMax = Math.max(rightMax, i + nums[i]);
        /* 当 i 已经是 这一步的最远右边 */
        if (i == curRightMax) {
          /* 更新 这一步最远右边 改为 全局的最远可达右边 */
          curRightMax = rightMax;
          /* 说明走了一步，计步器++ */
          stepCnt++;
        }
      }

      return stepCnt;
    }
  }
}
