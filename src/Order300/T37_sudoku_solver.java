package Order300;

import java.util.ArrayList;
import java.util.List;

public class T37_sudoku_solver {

  public static void main(String[] args) {
    new Solution()
    .solveSudoku(
        new char[][] {
          { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
          { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
          { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
          { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
          { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
          { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
          { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
          { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
          { '.', '.', '.', '.', '8', '.', '.', '7', '9' },
        }
      );
  }

  static class Solution {

    public void solveSudoku(char[][] board) {}
  }

  /* 第一次尝试 时间 3ms => 86.88% 空间 38.2 => 7.76% */
  static class Solution_me_1 {

    /** 对参数中对res进行修改，则，res的值真的会发生变化
     * Java的方法传递是 值传递
     * 对于基本数据类型，值传递，所以，对形式参数的赋值不会改变实际参数的值
     * 而对于引用数据类型，就需要考虑 Enverment 和 Store 的不同了
     */
    // boolean[][] tempDo(boolean[][] res, int i, int j, boolean value) {
    //   res[i][j] = value;
    //   return res;
    // }

    List<String> debugPrintBoard(char[][] board) {
      List<String> out = new ArrayList<>();
      StringBuilder sb;
      for (int i = 0; i < 9; i++) {
        sb = new StringBuilder();
        for (int j = 0; j < 9; j++) {
          sb.append(" ");
          sb.append(board[i][j]);
        }
        out.add(sb.toString());
      }
      return out;
    }

    List<Character> mixUp(boolean[] lineX, boolean[] lineY, boolean[] lineS) {
      int iMax = lineX.length;
      List<Character> out = new ArrayList<Character>();
      for (int i = 0; i < iMax; i++) {
        if (!(lineX[i] || lineY[i] || lineS[i])) {
          out.add((char) (i + '1'));
        }
      }
      return out;
    }

    boolean backtrack(
      char[][] board,
      boolean[][] lineX,
      boolean[][] lineY,
      boolean[][] lineS,
      int pi,
      int pj
    ) {
      searchNext:for (int i = pi; i < 9; i++) {
        for (int j = (i == pi ? pj : 0); j < 9; j++) {
          if (board[i][j] == '.') {
            pi = i;
            pj = j;
            break searchNext;
          } else if (i == 8 && j == 8) {
            /* 打印结果 */
            // for (int jj = 0; jj < 9; jj++) {
            // for (int ii = 0; ii < 9; ii++) {
            // System.out.printf(" %c", board[ii][jj]);
            // }
            // System.out.println();
            // }
            return true;
          }
        }
      }
      List<Character> choices = mixUp(
        lineX[pi],
        lineY[pj],
        lineS[pi / 3 * 3 + pj / 3]
      );
      for (char x : choices) {
        // char old = board[pi][pj];
        board[pi][pj] = x;
        lineX[pi][x - '1'] = true;
        lineY[pj][x - '1'] = true;
        lineS[pi / 3 * 3 + pj / 3][x - '1'] = true;
        if (backtrack(board, lineX, lineY, lineS, pi, pj)) {
          /* 及时退出 */
          return true;
        }
        /* 复原到上一步 但是由于前文的逻辑限制，所以，此处的 old 必定为 '.' */
        // board[pi][pj] = old;
        // System.out.println("old\t" + old);
        board[pi][pj] = '.';
        lineX[pi][x - '1'] = false;
        lineY[pj][x - '1'] = false;
        lineS[pi / 3 * 3 + pj / 3][x - '1'] = false;
      }
      return false;
    }

    public void solveSudoku(char[][] board) {
      boolean[][] lineX = new boolean[9][9];
      boolean[][] lineY = new boolean[9][9];
      boolean[][] lineS = new boolean[9][9];

      /* 初始化读入 */
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (board[i][j] != '.') {
            int val = board[i][j] - '1';/* [0,8] */
            int sIndex = i / 3 * 3 + j / 3;
            lineX[i][val] = true;
            lineY[j][val] = true;
            lineS[sIndex][val] = true;
          }
        }
      }

      /* 调用回溯函数 */
      backtrack(board, lineX, lineY, lineS, 0, 0);
      /* 打印结果 */
      // for (int i = 0; i < 9; i++) {
      // for (int j = 0; j < 9; j++) {
      // System.out.printf(" %c", board[i][j]);
      // }
      // System.out.println();
      // }
    }
  }
}
