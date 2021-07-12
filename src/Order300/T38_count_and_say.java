package Order300;

public class T38_count_and_say {

  public static void main(String[] args) {
    // System.out.println(new Solution().countAndSay(0));
    System.out.println(new Solution().countAndSay(1));
    System.out.println(new Solution().countAndSay(2));
    System.out.println(new Solution().countAndSay(3));
    System.out.println(new Solution().countAndSay(4));
    System.out.println(new Solution().countAndSay(5));
  }

  static class Solution {

    /* 第一次尝试 时间 5ms => 48.80% 空间 37.9MB => 25.42% */
    public String countAndSay(int n) {
      int loops = 1;
      String str = "1";
      while (loops < n) {
        StringBuilder builder = new StringBuilder();
        int cnt = 0;
        char k = '-';

        for (char ch : str.toCharArray()) {
          if (ch == k) {
            cnt++;
          } else {
            if (k != '-') {
              builder.append(String.valueOf(cnt));
              builder.append(k);
            }
            k = ch;
            cnt = 1;
          }
        }
        builder.append(String.valueOf(cnt));
        builder.append(k);

        loops++;
        str = builder.toString();
      }
      return str;
    }
  }
}
