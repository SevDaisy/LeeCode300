package Order300;

import java.util.HashMap;
import java.util.Map;

public class T8_string_to_integer_atoi {

  public static void main(String[] args) {
    System.out.println(new Solution().myAtoi("2147483649"));
    System.out.println(new Solution().myAtoi("99999999999999999"));
    System.out.println(new Solution().myAtoi("-99999999999999999"));
    System.out.println(new Solution().myAtoi("abdc"));
    System.out.println(new Solution().myAtoi("123abdc"));
  }

  static class Automaton {

    private static final Map<String, String[]> TABLE = new HashMap<>(5);
    private static final int MAX_VALUE_tenth = Integer.MAX_VALUE / 10;
    boolean overflow = false; // 标记是否溢出
    int numSign = 1; // 标记正负
    int val = 0; // 存储值
    String state = "start"; // 当前状态

    /* 初始化 状态映射矩阵 */
    static {
      TABLE.put("start", new String[] { "start", "sign", "num", "end" });
      TABLE.put("sign", new String[] { "end", "end", "num", "end" });
      TABLE.put("num", new String[] { "end", "end", "num", "end" });
      TABLE.put("end", new String[] { "end", "end", "end", "end" });
    }

    /* 从 Char 得出 Table 矩阵的 X 值 */
    static int getTableX(char x) {
      if (x == ' ') {
        return 0;
      } else if (x == '+' || x == '-') {
        return 1;
      } else if (Character.isDigit(x)) {
        return 2;
      } else return 3;
    }

    /* 自动机走一步 */
    void step(char c) {
      /* 先做状态转移 */
      this.state = TABLE.get(this.state)[getTableX(c)];
      /* 再做值的运算 */
      switch (state) {
        case "sign":
          this.numSign = (c == '-') ? -1 : 1;
          break;
        case "num":
          if (!overflow) {
            if (val > MAX_VALUE_tenth) {
              overflow = true;
            } else if (val == MAX_VALUE_tenth) {
              if (numSign == 1 && (c - '0') > 7) {
                overflow = true;
              } else if (numSign == -1 && (c - '0') > 8) {
                overflow = true;
              }
            }
            this.val = val * 10 + c - '0';
          }
          break;
        default:
          break;
      }
    }

    void readLine(String s) {
      for (int i = 0; i < s.length(); i++) {
        this.step(s.charAt(i));
      }
    }

    int getResult() {
      if (overflow) {
        return numSign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      } else {
        return numSign * val;
      }
    }
  }

  static class Solution {

    public int myAtoi(String str) {
      Automaton automaton = new Automaton();
      automaton.readLine(str);
      return automaton.getResult();
    }
  }
}
