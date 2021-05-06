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

  static class Solution {

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
}
