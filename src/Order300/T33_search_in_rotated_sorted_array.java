package Order300;

public class T33_search_in_rotated_sorted_array {

  public static void main(String[] args) {
    int[] nums;

    nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
    System.out.println(new Solution().search(nums, 0));
  }

  static class Solution {

    public int search(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
        return -1;
      }
      int iMax = nums.length;
      int left, right, mid;

      if (iMax == 1) return nums[0] == target ? 0 : -1;

      left = 0;
      right = iMax - 1;
      /* 二分合法条件 left≤right */
      while (left <= right) {
        /* 取得当前 mid 的值 */
        mid = (left + right) >> 1;
        /* 如果找到了 则 退出 */
        if (nums[mid] == target) return mid;
        if (nums[0] <= nums[mid]) {
          /* 如果 0～mid 上，数组是有序的，那就在 0~mid 上正常二分搜索 */
          if (nums[0] <= target && target < nums[mid]) {
            right = mid - 1;
          } else left = mid + 1;
        } else {
          /* 如果 0～mid 上，数组不是升序的，那么，mid～iMax-1 上，数组一定是有序的，在此范围正常二分搜索即可 */
          if (nums[mid] < target && target <= nums[iMax - 1]) {
            left = mid + 1;
          } else right = mid - 1;
        }
      }

      return -1;
    }
  }
}
