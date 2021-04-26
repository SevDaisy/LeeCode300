package Order300;

import BaseNode.ListNode;

public class T24_swap_nodes_in_pairs {

  public static void main(String[] args) {
    ListNode list = new ListNode(0);
    list.add(1).add(2).add(3).add(4);
    System.out.println(new Solution().swapPairs(list).toList());
  }

  static class Solution {

    public ListNode swapPairs(ListNode head) {
      if (head == null || head.next == null) {
        return head;
      }
      ListNode second = head.next;
      head.next = swapPairs(second.next);
      second.next = head;
      return second;
    }
  }
}
