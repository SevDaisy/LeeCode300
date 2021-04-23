package Order300;

import java.util.Stack;

public class T20_valid_parentheses {

  public static void main(String[] args) {
    System.out.println(new Solution().isValid("()"));
    System.out.println(new Solution().isValid("()[]{}"));
    System.out.println(new Solution().isValid("([)]"));
  }

  static class Solution {

    char pair(char right) {
      switch (right) {
        case ')':
          return '(';
        case ']':
          return '[';
        case '}':
          return '{';
        default:
          return right;
      }
    }

    boolean left(char x) {
      if (x == '(' || x == '[' || x == '{') return true; else return false;
    }

    public boolean isValid(String s) {
      int iMax = s.length();
      if ((s.length() & 1) != 0) {
        return false;
      }
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < iMax; i++) {
        if (left(s.charAt(i))) {
          stack.push(s.charAt(i));
        } else if (
          stack.isEmpty() || stack.pop() != pair(s.charAt(i))
        ) return false;
      }
      return stack.isEmpty();
    }
  }
}
