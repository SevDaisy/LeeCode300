package Order300;

import java.util.ArrayList;
import java.util.List;

public class T37_sudoku_solver {

  public static void main(String[] args) {
    new Solution()
    .solveSudoku(
        new char[][] {
          { '5', '3', '4', '6', '7', '8', '9', '1', '2' },
          { '6', '7', '2', '1', '9', '5', '3', '4', '8' },
          { '1', '9', '8', '3', '4', '2', '5', '6', '7' },
          { '8', '5', '9', '7', '6', '1', '4', '2', '3' },
          { '4', '2', '6', '8', '5', '3', '7', '9', '1' },
          { '7', '1', '3', '9', '2', '4', '8', '5', '6' },
          { '9', '6', '1', '.', '.', '.', '2', '8', '.' },
          { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
          { '.', '.', '.', '.', '8', '.', '.', '7', '9' },
        }
      );
  }

  static class Solution {

    /* 学习变量处理 用类变量代替方法传参 */
    /* 学习命名 row 代表 行 */
    boolean[][] row = new boolean[9][9];
    /* 学习命名 col 代表 列 */
    boolean[][] col = new boolean[9][9];
    /* 学习命名 cell 代表 小单元 */
    /* 学习数据格式 大胆使用高维数组 */
    boolean[][][] cell = new boolean[3][3][9];

    public void solveSudoku(char[][] board) {
      /* 初始化读入 */
      for (int xi = 0; xi < 9; xi++) {
        for (int yi = 0; yi < 9; yi++) {
          if (board[xi][yi] != '.') {
            int step = board[xi][yi] - '1';
            /* 这三行可以用连等来写成一行，但是我觉得丑、就算了 */
            row[xi][step] = true;
            col[yi][step] = true;
            cell[xi / 3][yi / 3][step] = true;
          }
        }
      }
      System.out.println(dfs(board, 0, 0));
      /* 打印结果 */
      // for (int ii = 0; ii < 9; ii++) {
      // for (int jj = 0; jj < 9; jj++) {
      // System.out.printf(" %c", board[ii][jj]);
      // }
      // System.out.println();
      // }
    }

    /**
     * 这个问题实际上是 深度优先搜索 而不是 回溯
     * 因为题目的目标是找到答案，而不是找出全路径
     * 所以，
     * - 命名上：应该是 DFS 而不是 回溯
     * - 函数处理上：
     *  - 不需要保存历史路径
     *  - 及时退出，返回结果
     * 特别的，其实，参数 x:int 也可以抽离到成员变量中去
     */
    private boolean dfs(char[][] board, int x, int y) {
      /**
       * 观察当前状态
       * 确定下一步怎么走
       * 看看是，走一步，还是退出，还是回头
       */
      if (y == 9) {
        /* 一个方向走到头了，换行，回头 */
        x++;
        y = 0;
      }
      if (x == 9) {
        /* 走到第九行了，说明前0～8行都走完了，成功，退出 */
        /* 打印结果 */
        for (int ii = 0; ii < 9; ii++) {
          for (int jj = 0; jj < 9; jj++) {
            System.out.printf(" %c", board[ii][jj]);
          }
          System.out.println();
        }
        return true;
      }
      if (board[x][y] != '.') {
        /**
         * 现在这步不用走，那就跳过，去走下一步
         * 无需考虑新的 x y 是否合法
         * 在下一次函数执行体的头部再来做（x,y) 的矫正
         */
        return dfs(board, x, y + 1);
      }
      /**
       * 状态观察/位置矫正 Over
       * 接下来是，认真走一步，要做的业务处理
       */
      for (int step = 0; step < 9; step++) {
        /* 如果这一步可以这样走 */
        if (!(row[x][step] || col[y][step] || cell[x / 3][y / 3][step])) {
          /* 那就走这样的一步 */
          board[x][y] = (char) (step + '1');
          row[x][step] = col[x][step] = cell[x / 3][y / 3][step] = true;
          if (dfs(board, x, y + 1)) {
            /* DFS 中的及时退出处理 —— 上游函数栈传递返回 true */
            return true;
          }
          /* 然后把这一步撤回来 */
          board[x][y] = '.';
          row[x][step] = col[x][step] = cell[x / 3][y / 3][step] = false;
        }
      }
      /* 没能在前面的逻辑中return，走到这儿了，那就是失败了，返回 false */
      return false;
    }
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
