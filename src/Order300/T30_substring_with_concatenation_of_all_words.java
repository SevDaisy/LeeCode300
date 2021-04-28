package Order300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class T30_substring_with_concatenation_of_all_words {

  public static void main(String[] args) {
    System.out.println(
      new Solution()
      .findSubstring("barfoothefoobarman", new String[] { "foo", "bar" })
    );
    System.out.println(
      new Solution()
      .findSubstring(
          "barfoofoobarthefoobarman",
          new String[] { "bar", "foo", "the" }
        )
    );
  }

  static class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
      if (
        s == null ||
        s.length() == 0 ||
        words == null ||
        words.length == 0 ||
        s.length() < words[0].length()
      ) {
        return null;
      }
      List<Integer> answers = new ArrayList<>();
      int iMax = s.length();
      int wordsSize = words.length;
      int step = words[0].length();
      int L = step * wordsSize;
      Map<String, Integer> map = new HashMap<String, Integer>(wordsSize) {
        {
          for (int i = 0; i < wordsSize; i++) {
            put(words[i], 0);
          }
        }
      };
      Set<String> keySet = map.keySet();

      for (int begin = 0; begin < step; begin++) {
        int matchedCnt = 0;
        for (int left = begin; left + L < iMax; left += step) {
          if (left == begin) {
            for (int i = left; i + step <= left + L; i += step) {
              String cur = s.substring(i, i + step);
              if (keySet.contains(cur)) {
                int curCnt = map.get(cur);
                if (curCnt == 0) matchedCnt++;
                map.put(cur, curCnt + 1);
              }
            }
          } else {
            String _old = s.substring(left - step, left);
            String _new = s.substring(left + L - step, left + L);
            if (keySet.contains(_old)) {
              int cnt = map.get(_old);
              if (cnt == 1) matchedCnt--;
              map.put(_old, cnt - 1);
            }
            if (keySet.contains(_new)) {
              int cnt = map.get(_new);
              if (cnt == 0) matchedCnt++;
              map.put(_new, cnt + 1);
            }
          }
          if (matchedCnt == wordsSize) {
            answers.add(left);
          }
        }
      }

      return answers;
    }
  }
}
