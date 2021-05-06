package Order300;

public class T34_find_first_and_last_position_of_element_in_sorted_array {

  public static void main(String[] args) {
    int[] nums;
    int[] ans;

    nums = new int[] { 0, 0, 0, 1, 2, 3 };
    ans = new Solution().searchRange(nums, 0);
    for (int x : ans) {
      System.out.print(x + " ");
    }
    System.out.println();

    nums = new int[] { 2, 2 };
    ans = new Solution().searchRange(nums, 2);
    for (int x : ans) {
      System.out.print(x + " ");
    }
    System.out.println();

    nums = new int[] { 5, 7, 7, 8, 8, 10 };
    ans = new Solution().searchRange(nums, 8);
    for (int x : ans) {
      System.out.print(x + " ");
    }
    System.out.println();

    nums = new int[] { 5, 7, 7, 8, 8, 10 };
    ans = new Solution().searchRange(nums, 6);
    for (int x : ans) {
      System.out.print(x + " ");
    }
    System.out.println();
  }

  /** 我自己的算法 二分搜索找到 mayBe，然后向左向右做 step 为 1 的 for 循环，得到左边界和右边界的值。
   * 对比官方题解，可以提出一个问题。
   * ? 我求出的 mayBe 和 leftBound 或者 rightBound 有没有什么必然关系？
   */
  static class Solution_me {

    public int[] searchRange(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return new int[] { -1, -1 };
      }
      int iMax = nums.length;
      int left, right, mid;
      int mayAt, leftBound, rightBound;

      mayAt = -1;
      left = 0;
      right = iMax - 1;
      while (left <= right) {
        mid = (left + right) >> 1;
        if (nums[mid] == target) {
          mayAt = mid;
          break;
        }
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1;
        } else left = mid + 1;
      }

      if (mayAt == -1) return new int[] { -1, -1 };

      leftBound = mayAt;
      for (int i = mayAt - 1; i >= 0; i--) {
        if (nums[i] == target) leftBound = i; else break;
      }
      rightBound = mayAt;
      for (int i = mayAt + 1; i < iMax; i++) {
        if (nums[i] == target) rightBound = i; else break;
      }

      return new int[] { leftBound, rightBound };
    }
  }

  /** LeeCode官方题解的方法 精准地理解和掌握了二分 */
  static class Solution {

    public int[] searchRange(int[] nums, int target) {
      int leftIdx = binarySearch(nums, target, true);
      int rightIdx = binarySearch(nums, target, false) - 1;
      if (
        leftIdx <= rightIdx &&
        rightIdx < nums.length &&
        nums[leftIdx] == target &&
        nums[rightIdx] == target
      ) {
        return new int[] { leftIdx, rightIdx };
      }
      return new int[] { -1, -1 };
    }

    /**
     * 二分搜索
     * @param nums 待搜索的数组
     * @param target 待搜索的目标值
     * @param lower 是否要求 {@code nums[mid]==target }
     * @return 若{@code lower==false } 则定有 {@code nums[ans]>target } 并可能有 {@code nums[ans-1]==target }
     */
    public int binarySearch(int[] nums, int target, boolean lower) {
      int left = 0, right = nums.length - 1, ans = nums.length, mid;
      while (left <= right) {
        mid = (left + right) >> 1;
        if (nums[mid] > target || (lower && nums[mid] >= target)) {
          /**
           * 如果 nums[mid] > target, 则 右边界左移
           * 如果 nums[mid] ≤ target, 考虑，需要的是 第一个等于 target 的位置 还是 第一个大于 target 的位置
           * - 如果是 第一个等于 target 的位置 则 右边界左移
           * - 如果是 第一个大于 target 的位置 则当 nums[mid] >= target 才有 右边界左移
           */
          right = mid - 1;
          ans = mid;
        } else {
          /**
           * 如果 nums[mid] ≤ target 且 nums[mid] < target 才有 左边界右移
           */
          left = mid + 1;
        }
      }
      return ans;
    }
  }
}
