package Order300;

public class T26_remove_duplicates_from_sorted_array {

  public static void main(String[] args) {
    int[] list;
    list = new int[] { 1 };
    list = new int[] { 1, 1 };
    list = new int[] { 1, 2 };
    list = new int[] { 1, 1, 1, 2, 3, 3 };
    int len = new Solution().removeDuplicates(list);
    for (int i = 0; i < len; i++) {
      System.out.printf("%d ", list[i]);
    }
  }

  static class Solution {

    public int removeDuplicates(int[] nums) {
      if (nums == null || nums.length < 1) return 0;
      int skip = 0;
      int iMax = nums.length;

      /* 遍历方法 一 */
      for (int i = 1; i < iMax; i++) {
        if (nums[i] == nums[i - 1]) {
          skip++;
        } else {
          nums[i - skip] = nums[i];
        }
      }

      /* 遍历方法 二 */
      // int cur = 1;
      // while (true) {
      // while (cur < iMax && nums[cur] == nums[cur - 1]) {
      // cur++;
      // skip++;
      // }
      // if (cur < iMax) {
      // nums[cur - skip] = nums[cur];
      // cur++;
      // } else {
      // break;
      // }
      // }
      return iMax - skip;
    }
  }
}
