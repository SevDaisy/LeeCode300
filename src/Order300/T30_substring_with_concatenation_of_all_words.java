package Order300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class T30_substring_with_concatenation_of_all_words {

  public static void main(String[] args) {
    String s;
    String words[];

    s = "barfoothefoobarman";
    words = new String[] { "foo", "bar" };
    System.out.println(new Solution().findSubstring(s, words));

    s = "barfoofoobarthefoobarman";
    words = new String[] { "bar", "foo", "the" };
    System.out.println(new Solution().findSubstring(s, words));

    s = "wordgoodgoodgoodbestword";
    words = new String[] { "word", "good", "best", "good" };
    System.out.println(new Solution().findSubstring(s, words));

    StringBuilder sb = new StringBuilder("a");
    words = new String[5000];
    for (int i = 0; i < 5000; i++) {
      words[i] = sb.toString();
    }
    for (int i = 1; i < 5000; i++) {
      sb.append('a');
    }
    s = sb.toString();
    System.out.println(new Solution().findSubstring(s, words));
  }

  public static class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
      List<Integer> answers = new ArrayList<>();
      if (
        s == null ||
        s.length() == 0 ||
        words == null ||
        words.length == 0 ||
        s.length() < words[0].length()
      ) {
        return answers;
      }
      int iMax = s.length();
      int wordsSize = words.length;
      int step = words[0].length();
      int L = step * wordsSize;
      Map<String, Integer> requireMap = new HashMap<String, Integer>(
        wordsSize
      ) {
        {
          for (int i = 0; i < wordsSize; i++) {
            if (keySet().contains(words[i])) {
              put(words[i], get(words[i]) + 1);
            } else put(words[i], 1);
          }
        }
      };
      Map<String, Integer> windowMap = new HashMap<String, Integer>(wordsSize);
      Set<String> keySet = requireMap.keySet();

      for (int begin = 0; begin < step; begin++) {
        for (String key : keySet) {
          windowMap.put(key, 0);
        }
        for (int left = begin; left + L <= iMax; left += step) {
          if (left == begin) {
            for (int i = left; i + step <= left + L; i += step) {
              String cur = s.substring(i, i + step);
              if (keySet.contains(cur)) {
                windowMap.put(cur, windowMap.get(cur) + 1);
              }
            }
          } else {
            String _old = s.substring(left - step, left);
            String _new = s.substring(left + L - step, left + L);
            if (keySet.contains(_old)) {
              windowMap.put(_old, windowMap.get(_old) - 1);
            }
            if (keySet.contains(_new)) {
              windowMap.put(_new, windowMap.get(_new) + 1);
            }
          }
          boolean isOK = true;
          for (String key : keySet) {
            /* Integer 包装类 的判断相等，不能用 == 那是比较内存地址的 */
            if (!requireMap.get(key).equals(windowMap.get(key))) {
              isOK = false;
              break;
            }
          }
          if (isOK) {
            answers.add(left);
          }
        }
      }

      return answers;
    }
  }
}
