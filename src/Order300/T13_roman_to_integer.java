package Order300;

public class T13_roman_to_integer {

  static class Solution {

    int val(char e) {
      switch (e) {
        case 'I':
          return 1;
        case 'V':
          return 5;
        case 'X':
          return 10;
        case 'L':
          return 50;
        case 'C':
          return 100;
        case 'D':
          return 500;
        case 'M':
          return 1000;
        default:
          return 0;
      }
    }

    /**
     * 第一步 宏替换 IV 转为 IIII
     * 第二步 翻译 即 OK
     **/
    public int romanToInt(String s) {
      int i = 0;
      int iMax = s.length();
      int answer = 0;
      while (i < iMax) {
        if (i + 1 < iMax) {
          if (val(s.charAt(i)) < val(s.charAt(i + 1))) {
            answer += (val(s.charAt(i + 1)) - val(s.charAt(i)));
            i += 2;
            continue;
          }
        }
        answer += val(s.charAt(i));
        i += 1;
      }
      return answer;
    }
  }
}
