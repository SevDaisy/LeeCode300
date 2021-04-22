package Order300;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class T10_regular_expression_matching {

  public static void main(String[] args) {
    // System.out.println(new Solution().isMatch("aa", "a"));
    // System.out.println(new Solution().isMatch("aa", "a*"));
    // System.out.println(new Solution().isMatch("ab", ".*"));
    // System.out.println(new Solution().isMatch("aab", "c*a*b"));
    System.out.println(new Solution().isMatch("aaa", "a*a"));
    // System.out.println(new Solution().isMatch("mississippi", "mis*is*p*"));
  }

  static class Solution {

    static class AutoState {

      int si;
      int pi;

      public AutoState(int si, int pi) {
        this.si = si;
        this.pi = pi;
      }

      @Override
      public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pi;
        result = prime * result + si;
        return result;
      }

      @Override
      public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AutoState other = (AutoState) obj;
        if (pi != other.pi) return false;
        if (si != other.si) return false;
        return true;
      }
    }

    /**
     * 自动机原理，双指针+DFS+回溯
     */
    public boolean isMatch(String s, String p) {
      char[] line = s.toCharArray();
      char[] mode = p.toCharArray();
      Stack<AutoState> stack = new Stack<>();
      Set<AutoState> visited = new HashSet<>(128);
      int sMax = s.length();
      int pMax = p.length();
      stack.push(new AutoState(/* si */0, /* pi */0));
      while (!stack.isEmpty()) {
        AutoState cur = stack.pop();
        visited.add(cur);

        /* 看看当前状态需不需要下一步的判断 */
        if (cur.si >= sMax && cur.pi >= pMax) {
          /* 如果 s,p 均已经遍历完毕，则返回 匹配成功 */
          return true;
        } else if (cur.si >= sMax) {
          /* 如果 s 已经遍历完毕，则检查 p 中剩下的是不是 '*' 或者 '.' */
          /**
           * '.' 匹配任意单个字符
           * '*' 匹配零个或多个前面的那一个元素
           */
          /* 因此，当且仅当剩下的是一个‘*’时，才算是匹配到了 */
          if (
            pMax == cur.pi + 1 && mode[cur.pi] == '*'
          ) return true; else continue;
        } else if (cur.pi >= pMax) {
          /* 如果 p 已经遍历完毕,则说明这个状态不行,继续检索下一个状态即可 */
          continue;
        }

        /* 查看下一步能不能走 */
        switch (mode[cur.pi]) {
          case '.':
            stack.add(new AutoState(cur.si + 1, cur.pi + 1));
            break;
          case '*':
            /* 题目明文 “保证每次出现字符 * 时，前面都匹配到有效的字符” */
            if (mode[cur.pi - 1] == '.') {
              /* 如果这个 * 其实是 .* 那么，可匹配任意字符。si前进一位，pi不变 */
              stack.add(new AutoState(cur.si + 1, cur.pi));
            } else {
              /**
               * 需要查看 s 中的上一个字符与当前字符是否相同
               * 因此 先确认 s 有没有 “上一个” 字符
               * 不过，既然 mode 中每次出现字符 * 时，前面都匹配到有效的字符，而pi指针指向了当前的 ‘*’
               * 说明，s 中必然有 “上一个” 字符 和 mode 中的 “上一个” 字符匹配了
               * 结论 s 中必有 “上一个” 字符
               */
              if (line[cur.si - 1] == line[cur.si]) {
                /* 如果当前字符和上一个字符相同，则匹配成功。si前进一位，pi不变 */
                stack.add(new AutoState(cur.si + 1, cur.pi));
              } else {
                /* 说明 ‘*’ 匹配失败。si不变，pi前进一位  */
                stack.add(new AutoState(cur.si, cur.pi + 1));
              }
            }
            break;
          default:
            /* mode[pi] 是普通字符 */
            if (line[cur.si] == mode[cur.pi]) {
              stack.add(new AutoState(cur.si + 1, cur.pi + 1));
            } else {
              /* 如果mode的下一个'*'意为0个 也可以算作被匹配了 */
              if (cur.pi + 1 < pMax && mode[cur.pi + 1] == '*') {
                stack.add(new AutoState(cur.si + 1, cur.pi + 2));
              }
            }
            break;
        }
      }
      return false;
    }
  }
}
