package Order300;

import java.util.ArrayList;
import java.util.List;

public class T22_generate_parentheses {

  public static void main(String[] args) {
    System.out.println(new Solution().generateParenthesis(3));
  }

  static class Solution {

    void backtrack(
      List<String> walkedList,
      int goal,
      StringBuilder path,
      int left,/* 已使用的 左括号数目 */
      int right/* 已使用的 右括号数目 */
    ) {
      if (left == goal && right == goal) {
        walkedList.add(path.toString());
      } else {
        if (left < goal) {
          path.append('(');
          backtrack(walkedList, goal, path, left + 1, right);
          path.deleteCharAt(path.length() - 1);
        }
        if (right < left) {
          path.append(')');
          backtrack(walkedList, goal, path, left, right + 1);
          path.deleteCharAt(path.length() - 1);
        }
      }
    }

    public List<String> generateParenthesis(int n) {
      List<String> out = new ArrayList<>();
      backtrack(out, n, new StringBuilder(), 0, 0);
      return out;
    }
  }
}
