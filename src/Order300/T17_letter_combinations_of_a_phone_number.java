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
      List<String> walkedList,
      Map<Character, String> map,
      String goal,
      int pos,
      StringBuilder path
    ) {
      if (pos == goal.length()) {
        walkedList.add(path.toString());
      } else {
        char cur = goal.charAt(pos);
        String subPaths = map.get(cur);
        int subPathCnt = subPaths.length();
        for (int i = 0; i < subPathCnt; i++) {
          path.append(subPaths.charAt(i));
          backtrack(walkedList, map, goal, pos + 1, path);
          path.deleteCharAt(pos);
        }
      }
    }
  }
}
