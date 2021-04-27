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

  /** 使用了数组来存储即将翻转的节点，使用了递归来实现对剩余节点的处理。时间和空间复杂度都是 O(n) 效率 1ms => 35.56% */
  static class me_array_递归 {

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
      heads[k] = cur;

      for (int i = 1; i < k; i++) {
        heads[i].next = heads[i - 1];
      }
      heads[0].next = reverseKGroup(heads[k], k);

      return heads[k - 1];
    }
  }

  static class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
      // System.out.println("\t\t\t\t" + ((head == null) ? "NULL" : head.toList()) + "\t" + k);

      /* 鲁棒性 输入head太短 则不做翻转 */
      int groupSize = k;
      ListNode cur = head;
      while (groupSize-- > 0) {
        if (cur == null) {
          /* 如果发现已经遍历完了，则说明 head 长度不够，不用翻转 */
          return head;
        } else {
          /* 如果循环因 groupSize-- > 0 而不再继续了，则 cur 就是链表中的第 k+1 个节点 —— 可能是 null */
          cur = cur.next;
        }
      }

      /**
       * 如果循环因 groupSize-- > 0 而不再继续了
       * 则 cur 就是链表中的 k 号位节点 —— 索引从 0 开始
       * k 号位节点可能是 null
       */
      ListNode kthNode = cur;
      ListNode reversedListHead = null;
      // ListNode reversedListTail = null;
      cur = head;
      while (cur != kthNode) {
        ListNode second = cur.next;
        cur.next = reversedListHead;
        // if (reversedListHead == null) reversedListTail = cur;
        reversedListHead = cur;
        cur = second;
      }
      /**
       * k 号位节点及其以后的节点，翻转了以后，再连接到已经翻转好了的链表的尾部
       *
       * 在上面那个 while (cur != kthNode) 开始之前
       * 因为 kthNode 是 链表中的 k 号位节点 —— 索引从 0 开始
       * 同时 cur 是 head，也就是 链表中的 0 号位节点
       * 又因为 k > 0, 所以一定又 cur != kthNode, 所以循环会至少执行一次
       * 所以，语句 reversedListTail = cur; 一定会被执行到。
       * 而当时，cur也就是head，所以 reversedListTail 的值一定会被赋为 head
       *
       * 综上所诉，reversedListTail 可以由 head 完全等价替换
       **/
      // reversedListTail.next = reverseKGroup(kthNode, k);
      head.next = reverseKGroup(kthNode, k);

      return reversedListHead;
    }
  }
}
