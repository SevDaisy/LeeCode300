package Order300;

public class T12_integer_to_roman {

  static class Solution {

    /**
     * 将规则稍加完善（指 增加 900:CM 这样的映射）后，
     * 遍历规则集，做贪心匹配，即
     * 我们希望每次找到不大于目标值的最大映射对。
     *
     * 理论上来说可以可以通过二分法更快的找到这样的最大映射对
     * 但是罗马数字的规则集有限。这个优化实在是没什么意义。
     *
     * 于是对有序数组（规则集应该是降序存储的）逐个遍历即可。
     * 
     * 
     * 当然，硬编码数字也是一种解法。
     * 对规则集进行全扩充，实现 1:I 2:II 3:III 4:IV 这样的全映射也不失为一种解法
     * 但是这这个解法：
     *  - 没有让代码量变得少
     *  - 没有优化时间/空间复杂度
     *  - 没有让后期维护更轻松
     * 不会吧，不会真的有人做硬编码吧。。。
     **/
    int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I",};

    public String intToRoman(int num) {
      StringBuilder sb = new StringBuilder();
      while (num > 0) {
        for (int i = 0; i < values.length; i++) {
          if (values[i] <= num) {
            num -= values[i];
            sb.append(symbols[i]);
            break;
          }
        }
      }
      return sb.toString();
    }
  }
}
