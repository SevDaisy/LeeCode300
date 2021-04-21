package Order300;

public class T6_zigzag_conversion {

  public static void main(String[] args) {
    System.out.println(new Solution().convert("PAYPALISHIRING", 3));
  }

  static class Solution {

    /**
     * 两种思路
     * 1. 模拟，Z字形走路过程，横向一行作为一个存储单位。最后遍历numRows个存储单位即可。
     * 2. 找规律。找到字符下标的对应关系，并通过这个关系来访问
     *
     * 方法二更麻烦，而且性能也没好多少 —— 顶多是，勉强算是省了点空间 —— 放弃方法二的代码实现
     **/
    public String convert(String s, int numRows) {
      if (numRows <= 1) return s;
      StringBuilder[] res = new StringBuilder[numRows];
      for (int i = 0; i < numRows; i++) {
        res[i] = new StringBuilder();
      }
      /* 数组中的 掉头/拐弯 问题模范代码 —— 厚着脸皮称自己的写的为模范代码 —— */
      int step = 1; // 方向 flag
      int pos = 0; // pos初值为合法值
      for (int i = 0; i < s.length(); i++) {
        // 先赋值
        res[pos].append(s.charAt(i));
        // 再看要不要换方向
        // 换方向的条件：
        // 正向且再走一步就是正向非法值 或者 逆向且再走一步就是逆向非法值
        if (
          (step > 0 && pos + step == numRows) || (step < 0 && pos + step == -1)
        ) step = -step;
        // 再按方向前进
        pos += step;
      }
      StringBuilder out = new StringBuilder();
      for (int i = 0; i < numRows; i++) {
        out.append(res[i].toString());
      }
      return out.toString();
    }
  }
}
