package Order300;

public class T35_search_insert_position {

  public static void main(String[] args) {
    int[] nums;

    nums = new int[] { 1, 3, 5, 6 };
    System.out.println(new Solution().searchInsert(nums, 5)); // -> 2
    System.out.println(new Solution().searchInsert(nums, 2)); // -> 1
    System.out.println(new Solution().searchInsert(nums, 7)); // -> 4
    System.out.println(new Solution().searchInsert(nums, 0)); // -> 0
  }

  static class Solution {

    public int searchInsert(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return 0;
      }
      int iMax = nums.length;
      int low = 0;
      int high = iMax - 1;
      int mid = 0;
      while (low <= high) {
        mid = (low + high) >>> 1;
        if (nums[mid] == target) return mid;

        if (nums[mid] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      return nums[mid] > target ? mid : (mid + 1);
    }
  }
}
