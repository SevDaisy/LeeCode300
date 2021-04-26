package Order300;

import java.util.Comparator;
import java.util.PriorityQueue;

import BaseNode.ListNode;

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

    public ListNode mergeKLists(ListNode[] lists) {
      return merge(lists, 0, lists.length - 1);
    }

    /* 左右指针 遍历 链表列表。递归，二分归并。 */
    public ListNode merge(ListNode[] lists, int left, int right) {
      if (left == right) {
        /* 左右指针指向了同一个链表 */
        return lists[left];
      }

      if (left > right) {
        /* 左指针越界 —— 超过了右指针 */
        return null;
      }

      /* 二分归并 */
      int mid = (left + right) >> 1;

      /* 递归 二分 合并链表 */
      return mergeTwoLists(
        merge(lists, left, mid),
        merge(lists, mid + 1, right)
      );
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
      if (a == null || b == null) {
        return a == null ? b : a;
      }
      ListNode dummy = new ListNode(0);
      ListNode tail, l1, l2;
      tail = dummy;
      l1 = a;
      l2 = b;
      while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
          tail.next = l1;
          l1 = l1.next;
        } else {
          tail.next = l2;
          l2 = l2.next;
        }
        tail = tail.next;
      }
      tail.next = ((l1 == null) ? l2 : l1);
      return dummy.next;
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
