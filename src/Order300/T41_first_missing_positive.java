package Order300;

public class T41_first_missing_positive {

  public static void main(String[] args) {
    System.out.println(new Solution().firstMissingPositive(new int[] { 1, 1 })); // -> 2
    System.out.println(
      new Solution().firstMissingPositive(new int[] { 1, 2, 0 })
    ); // -> 3
    System.out.println(
      new Solution().firstMissingPositive(new int[] { 3, 4, -1, 1 })
    ); // -> 2
    System.out.println(
      new Solution().firstMissingPositive(new int[] { 7, 8, 9, 11, 12 })
    ); // -> 1
    System.out.println(new Solution().firstMissingPositive(new int[] { 1 })); // -> 2
  }

  /* 第一次尝试 借鉴自己以前的代码，只能跑到 时间 2ms => 54.47% 空间 94.9MB => 7.00% */
  static class Solution {

    /**
     * 我一年前写过的题。笑死。根本不记得解法。最后看了自己的历史提交。借鉴思想，尝试复现代码。
     * 分析：
     * 题目，难就难在时间复杂度要求为O(n)，且空间复杂度为O(1)
     *
     * 如果没有时空要求。怎么做？
     * 直接过滤并排序为一个只有正整数的数组，再遍历数组，找到那个不符合 nums[i] = i+1 ，返回 i+1 就好了
     *
     * 所以，难点其实是，过滤和排序要在 O(n) 内完成
     *
     * 过滤显然没问题。for x in array: x>0 Only 就好了
     * 排序，要 O(n) 似乎是一个不可能的任务
     *
     * 转换思路。这其实是一个很特殊的排序问题。为什么，因为我们是知道每个数字最后应该被放在哪个位置上的。
     * 所以，我们不需要每个数都比来比去。只要拿到一个数，然后把他放到他应该在的位置。
     * 如果他就在那个位置上，那么，i++，直接去看下一个位置。直到整个数组遍历完一遍。
     * 如果他不在自己的位置上，那么，保存原本占了他位置的数，把这个数放到它该去的地方。重复，直到把整个数组遍历完一遍。
     *
     * 概要设计如上，还要注意的细节问题如下：
     *
     * 最后得到的数组，包含哪些元素？
     * 如果数组中没有缺少正整数，按题意，这个数组应该是这样的 [1,2,3,...,iMax]
     * 也就是说，数组中的元素，合法的范围不是 x > 0 而是 x in [1,iMax]
     *
     * 关于数字的比较和交换。
     * 应该是, if nums[nums[i]-1] != nums[i] 则 交换. 
     * 而不是, if nums[i] != i+1 则 buffer = nums[nums[i]-1]; nums[nums[i]-1] = nums[i];
     * 也就说, 不能拿着这个数，去保存到另外的 buffer。这样的代码远不如直接交换来得简明健壮好用好看。
     * 具体逻辑我也说不清楚。我只知道，一开始想用 buffer 写来着，我没写出来。
     * 品，细品。悟，好好悟。想明白了可以教教我🤪
     *
     * 
     * 补充说明 下面这个简化是不成立的："nums[nums[i]-1] != nums[i] 转化为 nums[i]-1 != i 转化为 nums[i] != i+1"
     * 因为，(nums[nums[i]-1] != nums[i]) 是 (nums[i]-1 != i) 的 充分但不必要条件
     */
    public int firstMissingPositive(int[] nums) {
      // if (nums == null || nums.length == 0) return 0;
      int iMax = nums.length;
      for (int i = 0; i < iMax; i++) {
        while (nums[i] > 0 && nums[i] <= iMax && nums[nums[i] - 1] != nums[i]) {
          int temp = nums[nums[i] - 1];
          nums[nums[i] - 1] = nums[i];
          nums[i] = temp;
        }
      }
      for (int i = 0; i < iMax; i++) {
        if (nums[i] != i + 1) return i + 1;
      }
      return iMax + 1;
    }
  }
}
