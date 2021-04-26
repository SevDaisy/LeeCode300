package Order300;

import BaseNode.ListNode;

public class T25_reverse_nodes_in_k_group {

  public static void main(String[] args) {
    ListNode list = new ListNode(0);
    list.add(1).add(2).add(3).add(4).add(5).add(6).add(7);
    System.out.println(list.toList());
    System.out.println(new Solution().reverseKGroup(list, 3).toList());
  }

  static class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
      /* 鲁棒性 输入太短则不做翻转 */
      int groupSize = k;
      ListNode cur = head;
      while (groupSize-- > 0) {
        if (head == null) return head; else cur = cur.next;
      }

      ListNode[] heads = new ListNode[k + 1];
      cur = head;
      for (int i = 0; i <= k; i++) {
        heads[i] = cur;
        cur = cur.next;
      }
      for (int i = 1; i < k; i++) {
        heads[i].next = heads[i - 1];
      }
      heads[0].next = reverseKGroup(heads[k], k);

      return heads[k - 1];
    }
  }
}
