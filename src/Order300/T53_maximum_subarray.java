package Order300;

public class T53_maximum_subarray {

  public static void main(String[] args) {
    System.out.println(); // new Solution().maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 })
  }

  static class Solution_dp {

    public int maxSubArray(int[] nums) {
      /* DP解决，不过因为 f(x) 仅和 f(x-1) 有关，所以找个变量保存 f(x-1) 就好了 */
      int preSubSum = 0; // 包含 前一个元素 的所有子数组的maxSubArraySum
      int maxSubSum = nums[0]; // 从 起点至今 的所有子数组的maxSubArraySum
      for (int x : nums) {
        preSubSum = Math.max(preSubSum + x, x);
        maxSubSum = Math.max(maxSubSum, preSubSum);
        // System.out.printf("%d\t%d\t%d\n", x, preSubSum, maxSubSum);
      }
      return maxSubSum;
    }
  }

  /**
   * left,right 都 从左至右 遍历
   * right++，滑动窗口添加新节点，并求出当前滑动窗口的 sum
   * 若 [left].val < 0 或 sum([left,right]) < 0 则 left++
   * - (同时被原来的 left 指向的元素移出滑动窗口)
   * 每当 right,left,sum 整理过一次后，更新保存的 maxSubSum
   **/
  static class Solution_指针 {

    public int maxSubArray(int[] nums) {
      int maxSum = nums[0]; // 从 起点至今 的所有 滑动窗口 的 maxSum
      int sum = 0; // 当前 滑动窗口 的 sum
      int iMax = nums.length;
      for (int right = 0, left = 0; right < iMax; right++) {
        sum += nums[right];
        while (left < right && (nums[left] < 0 || sum < 0)) {
          sum -= nums[left++];
        }
        maxSum = Math.max(sum, maxSum);
      }
      return maxSum;
    }
  }
}
