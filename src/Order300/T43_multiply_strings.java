package Order300;

public class T43_multiply_strings {

  public static void main(String[] args) {
    String out;
    out = new Solution().multiply("12", "13");
    System.out.println(out);
    out = new Solution().multiply("99", "99");
    System.out.println(out);
  }

  /* 第一次尝试 时间 2ms => 99.30% 空间 38.1MB => 95.63% */
  static class Solution {

    /**
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     */
    public String multiply(String num1, String num2) {
      int[] buffer = new int[220];
      int[] s1 = new int[110];
      int[] s2 = new int[110];

      /* 倒序储存 num1 和 num2 的各位数 */
      for (int i = num1.length() - 1, j = 0; i >= 0; i--, j++) {
        s1[j] = num1.charAt(i) - '0';
      }
      for (int i = num2.length() - 1, j = 0; i >= 0; i--, j++) {
        s2[j] = num2.charAt(i) - '0';
      }

      /* 逐位计算乘 并相加 */
      for (int i1 = 0; i1 < num1.length(); i1++) {
        for (int i2 = 0; i2 < num2.length(); i2++) {
          buffer[i1 + i2] += s1[i1] * s2[i2];
        }
      }

      /* 统一计算进位，并导出为结果字符串 out */
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < num1.length() + num2.length(); i++) {
        buffer[i + 1] += buffer[i] / 10;
        buffer[i] = buffer[i] % 10;
        sb.append((char) (buffer[i] + '0'));
      }
      String out = sb.reverse().toString();

      /* 消除 out 头部的 0 */
      for (int i = 0; i < out.length(); i++) {
        if (out.charAt(i) != '0') {
          return out.substring(i, out.length());
        }
      }
      /* 如果没在上面的for循环中返回值，那么显然，返回值应为 "0" */
      return "0";
    }
  }
}
