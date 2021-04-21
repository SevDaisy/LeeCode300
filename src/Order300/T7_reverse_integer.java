package Order300;

public class T7_reverse_integer {

  public static void main(String[] args) {
    System.out.println(new Solution().reverse(1147483647));
  }

  static class Solution {

    /**
     * 坑点有两个
     * - 后缀0转前导0 —— 处理很简单
     * - 2147483647 倒转以后是 7463847412 是溢出的，需要处理为 0 —— 正负数的溢出都是返回0
     */
    public int reverse(int x) {
      int out = 0;
      /* 把max和min保存在变量中，而不是写在条件判断里。为了方便和程序可读性。 */
      int max = Integer.MAX_VALUE / 10;
      int min = Integer.MIN_VALUE / 10;
      while (x != 0) {
        int cur = x % 10;
        x /= 10;
        if (out > max || (out == max && cur > 7)) return 0; else if (
          out < min || (out == min && cur < -8)
        ) return 0; else out = out * 10 + cur;
      }
      return out;
    }
  }
}
