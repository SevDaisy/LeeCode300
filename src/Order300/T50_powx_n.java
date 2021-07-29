package Order300;

/**
 * 看了题解提示，用【快速幂】法！
 * 可以有【递归】和【迭代】两种实现
 */
public class T50_powx_n {

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.myPow(0, 0));
    System.out.println(s.myPow(0, 9));
    System.out.println(s.myPow(0, -9));
    System.out.println();
    System.out.println(s.myPow(1, 0));
    System.out.println(s.myPow(1, 9));
    System.out.println(s.myPow(1, -9));
    System.out.println();
    System.out.println(s.myPow(-1, 0));
    System.out.println(s.myPow(-1, 9));
    System.out.println(s.myPow(-1, -9));
    System.out.println();
    System.out.println(s.myPow(2, 3));
    System.out.println(s.myPow(2, -3));
    System.out.println();
    System.out.println(s.myPow(0.00001, Integer.MAX_VALUE));
    System.out.println(s.myPow(2, Integer.MIN_VALUE));
  }

  /* 第一次尝试 递归 时间 0ms => 100% 空间 36.6MB => 78.12%  */
  static class Solution {

    public double myPow(double x, int n) {
      /* 提前出口 */
      if (n == 0) return x == 0 ? 0 : 1;
      if (x == 1) return 1;
      if (x == 0) return 0;

      /* 模式过滤 */
      return n > 0 ? quickRec(x, n) : 1 / quickRec(x, -n);
    }

    /* 递归求值 */
    private double quickRec(double x, int n) {
      if (n == 0) return 1.0;
      /* 使用 buffer 比在 return 里运算子结果要快很多 */
      double buffer = quickRec(x, n / 2);/* BugFix：这里要调用的是 quiceRec 而不是 myPow，不要写错了！ */
      return (n % 2 == 0 ? buffer * buffer : x * buffer * buffer);
    }
  }
}
