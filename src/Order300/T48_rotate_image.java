package Order300;

/**
 * 题目要求 必须原地旋转
 * 题目保证 输入是一个正方形
 **/
public class T48_rotate_image {

  /**
   * 第 -1 次尝试，希望递归解决，写出来是错的
   * 失败的样例如下
   * Input:
   *  1  2  3  4
   *  5  6  7  8
   *  9 10 11 12
   * 13 14 15 16
   *
   * MyOutput:
   *  5  1  7  3
   *  6  2  8  4
   * 13  9 15 11
   * 14 10 16 12
   */
  static class error_1 {

    public void rotate(int[][] matrix) {
      rotateAngle(matrix, 0, 0, matrix.length);
    }

    /**
     * @param map
     * @param _i 即 baseI
     * @param _j 即 baseJ
     * @param size
     */
    private void rotateEdge(int[][] map, int _i, int _j, int size) {
      int mid = size / 2;
      int[] buffer = new int[size];
      for (int i = 0; i < size; i++) {
        buffer[i] = map[_i + i][mid];
        map[_i + i][mid] = map[mid][_j + i];
      }
      for (int i = 0; i < size; i++) {
        map[mid][_j + i] = buffer[size - 1 - i];
      }
    }

    /**
     * @param map
     * @param _i 即 baseI
     * @param _j 即 baseJ
     * @param size
     */
    private void rotateAngle(int[][] map, int _i, int _j, int size) {
      if (size == 2) {
        int buffer = map[_i][_j];
        map[_i][_j] = map[_i + 1][_j];
        map[_i + 1][_j] = map[_i + 1][_j + 1];
        map[_i + 1][_j + 1] = map[_i][_j + 1];
        map[_i][_j + 1] = buffer;
      } else if (size == 3) {
        rotateEdge(map, _i, _j, size);
        int buffer = map[_i][_j];
        map[_i][_j] = map[_i + 2][_j];
        map[_i + 2][_j] = map[_i + 2][_j + 2];
        map[_i + 2][_j + 2] = map[_i][_j + 2];
        map[_i][_j + 2] = buffer;
      } else {
        int halfSize = size / 2;
        if (size % 2 != 0) {
          rotateEdge(map, _i, _j, size);
          rotateAngle(map, _i, _j, halfSize);
          rotateAngle(map, _i, _j + halfSize + 1, halfSize);
          rotateAngle(map, _i + halfSize + 1, _j, halfSize);
          rotateAngle(map, _i + halfSize + 1, _j + halfSize + 1, halfSize);
        } else {
          rotateAngle(map, _i, _j, halfSize);
          rotateAngle(map, _i, _j + halfSize, halfSize);
          rotateAngle(map, _i + halfSize, _j, halfSize);
          rotateAngle(map, _i + halfSize, _j + halfSize, halfSize);
        }
      }
    }
  }

  static class Solution {

    public void rotate(int[][] matrix) {}
  }
}
