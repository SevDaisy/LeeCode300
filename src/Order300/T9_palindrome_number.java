package Order300;

public class T9_palindrome_number {

  public static void main(String[] args) {
    // new Solution().isPalindrome(1234321);
    // new Solution().isPalindrome(123321);
    new Solution().isPalindrome(0);
  }

  static class Solution {

    public boolean isPalindrome(int x) {
      /* 特别的，对于负数和整十的数(0除外)，不可能是回文数 */
      if (x == 0) return true;
      if (x < 0 || x % 10 == 0) return false;

      /* 字符串法 10~12ms 10ms => 73% */
      char[] line = String.valueOf(x).toCharArray();
      int len = line.length;
      for (int i = 0; i < len / 2; i++) {
        if (line[i] != line[len - 1 - i]) {
          return false;
        }
      }
      return true;
      /* 数学演算法 150ms+ => 5.51%  */
      // int reverseNum = 0;
      // while (reverseNum < x) {
      //   reverseNum = reverseNum * 10 + x % 10;
      //   x /= 10;
      //   System.out.println(reverseNum + "\t" + x);
      // }
      // return reverseNum == x || reverseNum / 10 == x;
    }
  }
}
