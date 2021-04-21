package Order300;

public class T4_median_of_two_sorted_arrays {

  public static void main(String[] args) {
    System.out.println(
      new Solution()
      .findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }) // -> 2.5
    );
  }

  static class Solution {

    /* 二分搜索 注意边界条件 */
    /* 二分搜索的基本操作：mid值的计算 left/right 的转移，mid值的可能范围，仍相当不熟练 */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      /* 调整数组顺序，使得保证 nums1 不长于 nums2 */
      if (nums1.length > nums2.length) {
        int[] temp = nums1;
        nums1 = nums2;
        nums2 = temp;
      }

      /* 得到中位线左边的元素个数 leftCount */
      int l1 = nums1.length;
      int l2 = nums2.length;
      int leftCount = (l1 + l2 + 1) / 2;
      int p1; // nums1 上的中位线索引
      int p2; // nums2 上的中位线索引

      /* 在 nums1 上对索引 p1 进行范围为 [0,l1] 的二叉搜索 */
      int left = 0;
      int right = l1;
      /* 要求是 nums1[p1-1]<=nums2[p2] && nums2[p2-1]<=nums1[p1] */
      while (left < right) {
        p1 = left + ((right - left + 1) >> 1);
        p2 = leftCount - p1;
        if (nums1[p1 - 1] > nums2[p2]) {
          right = p1 - 1;
        } else {
          left = p1;
        }
      }
      /* 二叉搜索完毕，取出中位线的p1p2值 */
      p1 = left;
      p2 = leftCount - p1;
      int n1LeftMax = p1 == 0 ? Integer.MIN_VALUE : nums1[p1 - 1];
      int n1RightMin = p1 == l1 ? Integer.MAX_VALUE : nums1[p1];
      int n2LeftMax = p2 == 0 ? Integer.MIN_VALUE : nums2[p2 - 1];
      int n2RightMin = p2 == l2 ? Integer.MAX_VALUE : nums2[p2];

      if ((l1 + l2) % 2 == 1) {
        return Math.max(n1LeftMax, n2LeftMax);
      } else {
        return (
          (Math.max(n1LeftMax, n2LeftMax) + Math.min(n1RightMin, n2RightMin)) /
          2.0
        );
      }
    }
  }
}
