package Order300;

public class T42_trapping_rain_water {

  public static void main(String[] args) {
    System.out.println(
      new Solution().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 })
    );
    // System.out.println(new Solution().trap(new int[] { 4, 2, 0, 3, 2, 5 }));
  }

  static class Solution {

    public int trap(int[] height) {
      /* 鲁棒性 特殊条件 输入太短 */
      if (height == null || height.length < 2) {
        return 0;
      }
      int iMax = height.length;
      int leftMax[] = new int[iMax];
      int rightMax[] = new int[iMax];

      // leftMax range(0, iMax, 1)
      leftMax[0] = height[0];
      for (int i = 1; i < iMax; i++) {
        leftMax[i] = Math.max(height[i], leftMax[i - 1]);
      }

      // rightMax range(iMax-2, -1, -1)
      rightMax[iMax - 1] = height[iMax - 1];
      for (int i = iMax - 2; i > -1; i--) {
        rightMax[i] = Math.max(height[i], rightMax[i + 1]);
      }
      // for (int i : leftMax) {
        // System.out.print(i);
        // System.out.print(" ");
      // }
      // System.out.println();
      // for (int i : height) {
        // System.out.print(i);
        // System.out.print(" ");
      // }
      // System.out.println();
      // for (int i : rightMax) {
        // System.out.print(i);
        // System.out.print(" ");
      // }
      // System.out.println();

      // val range(1, iMax-1, 1)
      int sum = 0;
      int single = 0;
      // System.out.print("  ");
      for (int i = 1; i < iMax - 1; i++) {
        single = Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i];
        // System.out.print(single > 0 ? single : "-");
        // System.out.print(" ");
        sum += (single > 0 ? single : 0);
      }
      // System.out.println();
      return sum;
    }
  }
}
