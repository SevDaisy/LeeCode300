package Order300;

public class T27_remove_element {

  public static void main(String[] args) {
    int[] list;
    list = new int[] { 1 };
    list = new int[] { 1, 1 };
    list = new int[] { 1, 2 };
    list = new int[] { 1, 1, 1, 2, 3, 3 };
    list = new int[] { 3, 2, 2, 3 };
    int len = new Solution().removeElement(list, 3);
    for (int i = 0; i < len; i++) {
      System.out.printf("%d ", list[i]);
    }
  }

  static class Solution {

    public int removeElement(int[] nums, int val) {
      if (nums == null) return 0;

      int skip = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == val) {
          skip++;
        } else if (i - skip >= 0) {
          nums[i - skip] = nums[i];
        }
      }
      return nums.length - skip;
    }
  }
}
