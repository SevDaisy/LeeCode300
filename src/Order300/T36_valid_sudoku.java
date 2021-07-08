package Order300;

public class T36_valid_sudoku {

  /* 1ms => 100% */
  static class Solution {

    public boolean isValidSudoku(char[][] board) {
      boolean[][] lineX = new boolean[9][9];
      boolean[][] lineY = new boolean[9][9];
      boolean[][] lineS = new boolean[9][9];

      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (board[i][j] != '.') {
            int val = board[i][j] - '1';/* [0,8] */
            int sIndex = i / 3 * 3 + j / 3;
            if (lineX[i][val] || lineY[j][val] || lineS[sIndex][val]) {
              return false;
            } else {
              lineX[i][val] = true;
              lineY[j][val] = true;
              lineS[sIndex][val] = true;
            }
          }
        }
      }
      return true;
    }
  }
}
