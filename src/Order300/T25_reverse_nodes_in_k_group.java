package Order300;

import BaseNode.ListNode;

public class T25_reverse_nodes_in_k_group {

  static ListNode getCase() {
    ListNode list;
    list = new ListNode(0);
    list.add(1).add(2).add(3).add(4).add(5).add(6).add(7);
    return list;
  }

  public static void main(String[] args) {
    System.out.println(getCase().next.toList());
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 1).toList() + "\t1"
    );
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 2).toList() + "\t2"
    );
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 3).toList() + "\t3"
    );
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 4).toList() + "\t4"
    );
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 5).toList() + "\t5"
    );
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 6).toList() + "\t6"
    );
    System.out.println(
      new Solution().reverseKGroup(getCase().next, 7).toList() + "\t7"
    );
  }

  static class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
      // System.out.println("\t\t\t\t" + ((head == null) ? "NULL" : head.toList()) + "\t" + k);
      
      /* 鲁棒性 输入head太短 则不做翻转 */
      int groupSize = k;
      ListNode cur = head;
      while (groupSize-- > 0) {
        if (cur == null) return head; else cur = cur.next;
      }

      ListNode[] heads = new ListNode[k + 1];
      cur = head;
      for (int i = 0; i < k; i++) {
        heads[i] = cur;
        cur = cur.next;
      }
      /* 修复 head 长度 == k 时，于是 cur 为 null ，对 cur.next 的异常访问造成的空指针异常 */
      heads[k] = cur == null ? null : cur.next;

      for (int i = 1; i < k; i++) {
        heads[i].next = heads[i - 1];
      }
      heads[0].next = reverseKGroup(heads[k], k);

      return heads[k - 1];
    }
  }
}
