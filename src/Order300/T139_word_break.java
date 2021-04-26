package Order300;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T139_word_break {

  public static void main(String[] args) {
    System.out.println(
      new Solution()
      .wordBreak(
          "catsandog",
          Arrays.asList(
            new String[] { "cats", "dog", "sand", "and", "cat", "cee" }
          )
        )
    );
  }

  // TODO 自己再做一遍嗷！
  // ！！！就是不需要 start 集 只需要 end 集就够了！start 并不重要！
  static class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
      int iMax = s.length();
      Set<String> wordDictSet = new HashSet<>(wordDict);
      boolean[] dp = new boolean[iMax + 1];
      dp[0] = true;
      for (int i = 1; i <= iMax; i++) {
        // 检测分割点
        for (int j = 0; j < i; j++) {
          if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
            dp[i] = true;
            break;
          }
        }
      }
      return dp[iMax];
    }
  }
}
