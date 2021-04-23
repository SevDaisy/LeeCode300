package Order300;

import Order300.T2_add_two_numbers.ListNode;

public class T19_remove_nth_node_from_end_of_list {

  public static void main(String[] args) {
    ListNode list = new ListNode(1);
    list.add(2).add(3).add(4).add(5).add(6);
    System.out.println(list.toList());
    System.out.println((new Solution().removeNthFromEnd(list, 2).toList()));
    System.out.println((new Solution().removeNthFromEnd(list, 2).toList()));
    System.out.println((new Solution().removeNthFromEnd(list, 2).toList()));
    System.out.println((new Solution().removeNthFromEnd(list, 1).toList()));
    System.out.println((new Solution().removeNthFromEnd(list, 1).toList()));
    System.out.println((new Solution().removeNthFromEnd(list, 1).toList()));
  }

  static class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
      /* 哨兵节点 以 dummy 为名（直译为 假人） */
      ListNode dummy = new ListNode(0, head);
      ListNode fast, slow;
      fast = slow = dummy;
      while (n-- != 0) {
        fast = fast.next;
      }
      while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
      }
      slow.next = slow.next.next;
      return dummy.next;
    }
  }
}
