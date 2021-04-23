package Order300;

import java.util.Comparator;
import java.util.PriorityQueue;

import Order300.T2_add_two_numbers.ListNode;

public class T23_merge_k_sorted_lists {

  public static void main(String[] args) {
    ListNode[] lists = new ListNode[3];
    lists[0] = new ListNode(1);
    lists[1] = new ListNode(1);
    lists[2] = new ListNode(2);
    lists[0].add(4).add(5);
    lists[1].add(3).add(4);
    lists[2].add(6);
    // System.out.println(new Solution().mergeKLists(lists).toList());
    System.out.println(new Solution().mergeKLists(new ListNode[] {}));
  }

  /* 分治归并 1ms 100% */
  static class Solution {

    public char[] mergeKLists(ListNode[] lists) {
      return null;
    }
  }

  /* 优先队列 5ms 66.20% */
  static class Solution_优先队列 {

    public ListNode mergeKLists(ListNode[] lists) {
      PriorityQueue<ListNode> set = new PriorityQueue<>(
        new Comparator<ListNode>() {
          @Override
          public int compare(ListNode o1, ListNode o2) {
            if (o1 != null && o2 != null) {
              return o1.val - o2.val;
            } else {
              return o1 != null ? 1 : -1;
            }
          }
        }
      );
      /* 数组也可以用 for-each 来遍历 */
      for (ListNode e : lists) {
        /* 只能插入非null的节点 */
        if (e != null) set.offer(e);
      }
      /* 需要 哨兵节点dummy 和 当前节点cur 这样两个节点 */
      /* 哨兵.next 用于返回答案 */
      /* 当前cur 用于尾插法构造链表 */
      ListNode dummy = new ListNode(0);
      ListNode cur = dummy;
      while (!set.isEmpty()) {
        ListNode min = set.poll();
        cur.next = min;
        cur = cur.next;
        if (min.next != null) set.offer(min.next);
      }
      return dummy.next;
    }
  }
}
