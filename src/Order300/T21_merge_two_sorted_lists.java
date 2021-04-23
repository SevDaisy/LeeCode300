package Order300;

import Order300.T2_add_two_numbers.ListNode;

public class T21_merge_two_sorted_lists {

  static class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode dummy = new ListNode(0);

      ListNode cur = dummy;
      while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
          cur.next = l1;
          l1 = l1.next;
        } else {
          cur.next = l2;
          l2 = l2.next;
        }
        cur = cur.next;
      }
      /**
       * 合并后 l1 和 l2 最多只有一个还未被合并完
       * 我们直接将链表末尾指向未合并完的链表即可
       */
      cur.next = l1 == null ? l2 : l1;

      return dummy.next;
    }
  }
}
