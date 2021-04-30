package Order300;

import java.util.ArrayList;

public class T1206_design_skiplist {

  public static void main(String[] args) {
    int target = 1;
    int num = 1;
    new Skiplist().test();
    /** Your Skiplist object will be instantiated and called as such ↓↓↓ */
    Skiplist obj = new Skiplist();
    obj.search(target);
    obj.add(num);
    obj.erase(num);
  }

  static class Skiplist {

    /* 与其纠结前后左右指针怎么选，不如直接写好4个方向的指针到时候再改 */
    private static class Node {

      int level;
      int val;
      Node w, a, s, d;/* W A S D 在键盘上就是 上 下 左 右 */

      static Node from(int val, int level) {
        Node out = new Node();
        out.val = val;
        out.level = level;
        return out;
      }

      // Node setW(Node who) {  this.w = who;  return this;}
      // Node setA(Node who) {  this.a = who;  return this;}
      Node setS(Node who) {
        this.s = who;
        return this;
      }
      // Node setD(Node who) {  this.d = who;  return this;}
    }

    private static final int MAX_LAVAL = 64;

    ArrayList<Node> dummys = new ArrayList<>();

    private boolean coinFlip() {
      return (System.nanoTime() & 1) == 0;
    }

    /**
     * 找到 指定层 指定值 的 节点
     * @param begin 搜索的起点节点 Not NULL
     * @param num 搜索的目标值
     * @param tarLevel 搜索的目标层数 程序应保证 tarLevel ≤ dummys.size()
     * @return
     */
    private Node findValAtLevel(Node begin, int num, int tarLevel) {
      if (tarLevel < 1) return null;
      Node cur = begin;
      while (true) {
        /* 如果还能往右看 */
        if (cur.d != null) {
          /* 遇到 当前节点 ≤ 传入值 ≤ 右边节点，则准备插入 */
          if (cur.val < num && num <= cur.d.val) {
            if (cur.level > tarLevel && cur.s != null) {
              /* 如果 当前高于是目标层，则原地下探 */
              cur = cur.s;
            } else {
              /* 否则，退出，可以在 cur 后面插入 num 了 */
              cur = cur.d;
              break;
            }
          } else {
            /* 否则，继续往右走 */
            cur = cur.d;
          }
        } else {
          /* 右边没有节点了，试着往下看 */
          if (cur.level > tarLevel && cur.s != null) {
            /* 如果 当前高于是目标层，则原地下探 */
            cur = cur.s;
          } else {
            /* 否则，退出，可以在 cur 后面插入 num 了 */
            break;
          }
        }
      }
      return (cur.val == num && cur.level == tarLevel) ? cur : null;
    }

    private void addAtLevel(int num, int tarLevel) {
      Node cur;
      /* 如果 dummys 高度不够，则补足 */
      if (tarLevel > this.dummys.size()) for (
        int i = this.dummys.size() + 1;
        i <= tarLevel;
        i++
      ) {
        this.dummys.add(
            Node
              .from(Integer.MIN_VALUE, i)
              .setS(this.dummys.get(this.dummys.size() - 1))
          );
      }
      /* 取最高层的 dummy */
      cur = this.dummys.get(this.dummys.size() - 1);
      while (true) {
        /* 如果还能往右看 */
        if (cur.d != null) {
          /* 遇到 当前节点 ≤ 传入值 ≤ 右边节点，则准备插入 */
          if (cur.val <= num && num <= cur.d.val) {
            if (cur.level > tarLevel && cur.s != null) {
              /* 如果 当前高于是目标层，则原地下探 */
              cur = cur.s;
            } else {
              /* 否则，退出，可以在 cur 后面插入 num 了 */
              break;
            }
          } else {
            /* 否则，继续往右走 */
            cur = cur.d;
          }
        } else {
          /* 右边没有节点了，试着往下看 */
          if (cur.level > tarLevel && cur.s != null) {
            /* 如果 当前高于是目标层，则原地下探 */
            cur = cur.s;
          } else {
            /* 否则，退出，可以在 cur 后面插入 num 了 */
            break;
          }
        }
      }
      /**
       * 在 cur 后面插入新节点
       * 1. 新建节点 who.val := num
       * 2. 保存后面的尾巴 tail
       * 3. 左右指针链接
       * 4. 找到并保存 who 正下方的节点
       * 5. 上下指针链接
       **/
      Node who = Node.from(num, tarLevel);
      Node tail = cur.d;
      Node below = findValAtLevel(cur, num, tarLevel - 1);
      cur.d = who;
      who.a = cur;
      who.d = tail;
      if (tail != null) tail.a = who;
      who.s = below;
      if (below != null) below.w = who;
      if (tarLevel < MAX_LAVAL && coinFlip()) addAtLevel(num, tarLevel + 1);
    }

    public Skiplist() {
      this.dummys.add(Node.from(Integer.MIN_VALUE, 1));
    }

    public void add(int num) {
      addAtLevel(num, 1);
    }

    public boolean search(int target) {
      return (findValAtLevel(dummys.get(0), target, 1) == null) ? false : true;
    }

    public boolean erase(int num) {
      return false;
    }

    public void test() {
      Skiplist list = new Skiplist();
      System.out.println("adding 30 1");
      list.addAtLevel(30, 1);
      System.out.println("adding 50 1");
      list.addAtLevel(50, 1);
      System.out.println("adding 70 1");
      list.addAtLevel(70, 1);
      if (dummys.size() < 2) {
        list.addAtLevel(50, 2);
      }
      System.out.println("Checked");
    }
  }
}
