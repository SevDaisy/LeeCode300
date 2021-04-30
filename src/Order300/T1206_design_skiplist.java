package Order300;

import java.util.ArrayList;
import java.util.Random;

public class T1206_design_skiplist {

  public static void main(String[] args) {}

  public static class Skiplist {

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

      // @Override
      // public String toString() {
      //   return String.format(
      //     "val:%d a:%s d:%s level:%d w:%s s:%s",
      //     val,
      //     (a != null) ? String.valueOf(a.val) : "null",
      //     (d != null) ? String.valueOf(d.val) : "null",
      //     level,
      //     (w != null) ? String.valueOf(w.val) : "null",
      //     (s != null) ? String.valueOf(s.val) : "null"
      //   );
      // }
    }

    private static final int MAX_LAVAL = 64;

    ArrayList<Node> dummys = new ArrayList<>();

    private boolean coinFlip() {
      return (System.nanoTime() & 3) == 0;
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
      // System.err.printf("add > num: %d\t level:%d\n", num, tarLevel);
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
      return (findValAtLevel(dummys.get(dummys.size() - 1), target, 1) == null)
        ? false
        : true;
    }

    public boolean erase(int num) {
      Node cur = findValAtLevel(dummys.get(dummys.size() - 1), num, 1);
      if (cur == null) return false;
      Node[] lefts = new Node[MAX_LAVAL];
      Node[] rights = new Node[MAX_LAVAL];
      int nodeCnt = 0;
      while (cur != null) {
        if (cur.val != num) throw new RuntimeException(
          "找错了要删除的中心节点"
        );
        lefts[nodeCnt] = cur.a;
        rights[nodeCnt] = cur.d;
        cur = cur.w;
        nodeCnt++;
      }
      for (int i = 0; i < nodeCnt; i++) {
        if (lefts[i] != null) lefts[i].d = rights[i];
        if (rights[i] != null) rights[i].a = lefts[i];
      }
      /* 不需要递归调用来把num都删光erase(num); 就是只删 level 1 的一个就可以了 */
      return true;
    }
  }

  /** 跳跃表高端实现，优雅又强大 16 ~ 17ms => 99.499% ~ 99.33%
   * ? 看不懂大佬的实现。记得之后自己重写一次哦
   */
  static class Skiplist_终极 {

    private static final int MAX_LAVAL = 64;

    Node head;

    public Skiplist_终极() {
      head = new Node(null, null, Integer.MIN_VALUE);
    }

    public boolean search(int target) {
      Node p;
      for (p = head; p != null; p = p.down) {
        while (p.right != null && target > p.right.val) {
          p = p.right;
        }
        if (p.right != null && p.right.val == target) {
          return true;
        }
      }
      return false;
    }

    Node[] stack = new Node[MAX_LAVAL];
    Random random = new Random();

    public void add(int num) {
      int size = 0;
      for (Node p = head; p != null; p = p.down) {
        while (p.right != null && num > p.right.val) {
          p = p.right;
        }
        stack[size++] = p;
      }
      Node down = null;
      Node node;
      boolean up = true;
      while (up && size > 0) {
        node = stack[--size];
        node.right = new Node(node.right, down, num);
        down = node.right;
        up = (random.nextInt() & 3) == 0;
      }
      if (up) {
        head = new Node(new Node(null, down, num), head, Integer.MIN_VALUE);
      }
    }

    public boolean erase(int num) {
      boolean flag = false;
      for (Node p = head; p != null; p = p.down) {
        while (p.right != null && num > p.right.val) {
          p = p.right;
        }
        if (p.right != null && p.right.val == num) {
          p.right = p.right.right;
          flag = true;
        }
      }
      return flag;
    }

    static class Node {

      Node right, down;
      int val;

      public Node(Node right, Node down, int val) {
        this.right = right;
        this.down = down;
        this.val = val;
      }
    }
  }
}
