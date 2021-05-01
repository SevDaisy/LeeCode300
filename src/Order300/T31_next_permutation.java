package Order300;

public class T31_next_permutation {

  public static void main(String[] args) {
    int[] nums;
    nums = new int[] { 3, 4, 5, 4, 3, 2, 1 };
    new Solution().nextPermutation(nums);
    for (int x : nums) System.out.print(x);
    System.out.println();

    nums = new int[] { 1, 3, 2 };
    new Solution().nextPermutation(nums);
    for (int x : nums) System.out.print(x);
    System.out.println();

    nums = new int[] { 4, 5, 2, 6, 3, 1 };
    new Solution().nextPermutation(nums);
    for (int x : nums) System.out.print(x);
    System.out.println();

    nums = new int[] { 1, 2, 3, 4, 5, 6 };
    new Solution().nextPermutation(nums);
    for (int x : nums) System.out.print(x);
    System.out.println();

    nums = new int[] { 6, 5, 4, 3, 2, 1 };
    new Solution().nextPermutation(nums);
    for (int x : nums) System.out.print(x);
    System.out.println();
  }

  static class Solution {

    public void nextPermutation(int[] nums) {
      if (nums == null || nums.length < 2) {
        return;
      }
      int iMax = nums.length;
      int leftBound = -1;
      int aLittleBigger;
      int temp;
      int left;
      int right;

      /* 452631 leftBound -> 2 in 526 */
      for (int i = iMax - 2; i >= 0; i--) {
        if (nums[i] < nums[i + 1]) {
          leftBound = i;
          break;
        }
      }

      if (leftBound != -1) {
        /* 452631 leftBound -> 2 in 526; aLittleBigger -> 3 in 631 */
        aLittleBigger = -1;
        for (int i = iMax - 1; i >= 0; i--) {
          /* 只要 leftBound 已经是合法值,那就一定能找到 aLittleBigger */
          if (nums[i] > nums[leftBound]) {
            aLittleBigger = i;
            break;
          }
        }
        /* 452631 => 453621 then 621 must be descend */
        temp = nums[aLittleBigger];
        nums[aLittleBigger] = nums[leftBound];
        nums[leftBound] = temp;

        /* 453621 => 453126 reverse 621 */
        left = leftBound + 1;
        right = iMax - 1;
      } else {
        /* 654321 => 123456 reverse 621 */
        left = 0;
        right = iMax - 1;
      }

      /* reverse [left, right] */
      while (left < right) {
        temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
        left++;
        right--;
      }
    }
  }
}
