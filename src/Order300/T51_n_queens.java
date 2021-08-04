package Order300;

import java.util.ArrayList;
import java.util.List;

public class T51_n_queens {

  static class Solution {

    public List<List<String>> solveNQueens(int n) {
      List<List<String>> ans = new ArrayList<>();
      List<List<Character>> path = new ArrayList<List<Character>>() {
        {
          for (int i = 0; i < n; i++) {
            add(new ArrayList<Character>());
          }
        }
      };
      backtrace(ans, path, 0, n);
      return null;
    }

    boolean backtrace(
      List<List<String>> answers,
      List<List<Character>> path,
      int step,
      int goal
    ) {
      if (step == goal) {
        answers.add(
          new ArrayList<String>() {
            {
              for (List<Character> list : path) {
                StringBuilder builder = new StringBuilder();
                builder.append((Character[]) list.toArray());
                add(builder.toString());
              }
            }
          }
        );
      }
      return false;
    }
  }
}
