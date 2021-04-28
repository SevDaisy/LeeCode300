package Order300;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class T30_substring_with_concatenation_of_all_words {

  public static void main(String[] args) {
    System.out.println(
      new Solution()
      .findSubstring("barfoothefoobarman", new String[] { "foo", "bar" })
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
      Map<String, Boolean> map = new Hashtable<String, Boolean>(wordsSize) {
        {
          for (int i = 0; i < wordsSize; i++) {
            put(words[i], false);
          }
        }
      };
      Set<String> keySet = map.keySet();

      for (int begin = 0; begin < step; begin++) {
        int matchedCnt = 0;
        for (int left = begin; left + L < iMax; left += step) {
          if (left == begin) {
            for (int i = left; i + step < iMax; i += step) {
              String cur = s.substring(left, left + step);
              if (keySet.contains(cur) && !map.get(cur)) {
                map.put(cur, true);
                matchedCnt++;
              }
            }
          } else {
            String _old = s.substring(left - step, left);
            String _new = s.substring(left + L - step, left + L);
            if (keySet.contains(_old)) {
              map.put(_old, false);
              matchedCnt--;
            }
            if (keySet.contains(_new) && !map.get(_new)) {
              map.put(_new, true);
              matchedCnt++;
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
