package Order300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T17_letter_combinations_of_a_phone_number {

  public static void main(String[] args) {
    for (String s : new Solution().letterCombinations("23")) {
      System.out.println(s);
    }
  }

  static class Solution {

    static final Map<Character, String> MAP = new HashMap<Character, String>() {
      {
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
      }
    };

    public List<String> letterCombinations(String digits) {
      List<String> answerList = new ArrayList<>(256);
      if (digits.length() == 0) {
        return answerList;
      }
      backtrack(answerList, MAP, digits, 0, new StringBuilder());
      return answerList;
    }

    public void backtrack(
      List<String> walkedList, // 所有走完的路径，保存在此
      Map<Character, String> map, // 每个节点的下一条路可以怎么选
      String goal,// 总共要走完的 深度/目标
      int pos, // 当前是 第几步/第几层
      StringBuilder path // 从起点至今的节点路径
    ) {
      if (pos == goal.length()) {
        /* 若已经走完了，则保存结果 */
        walkedList.add(path.toString());
      } else {
        /* 看看下一步能怎么走 */
        char cur = goal.charAt(pos);
        String subPaths = map.get(cur);
        int subPathCnt = subPaths.length();
        for (int i = 0; i < subPathCnt; i++) {
          /* 对于每一种子路线 尝试访问之 */
          path.append(subPaths.charAt(i));
          backtrack(walkedList, map, goal, pos + 1, path);
          path.deleteCharAt(pos);
        }
      }
    }
  }
}
