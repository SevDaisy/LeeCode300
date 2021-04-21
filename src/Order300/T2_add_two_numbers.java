package Order300;

public class T2_add_two_numbers {

  static class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  static class Solution {

    static String printListNode(ListNode cur) {
      /* 无需 ListNode cur = in; 也不会产生对 cur 的副作用 */
      StringBuilder builder = new StringBuilder();
      while (cur != null) {
        builder.append(String.format("-> %d ", cur.val));
        cur = cur.next;
      }
      return builder.toString();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      /**
       * 同步遍历 l1 l2
       * - 并相加配对节点
       * - 若 l1 l2 长度不匹配则，长度不足者值取零
       * 因为不确定 l1 l2 谁更长。因此新建链表 head-tail 用于存储 l1 l2 节点配对相加的结果
       * - head指针用于返回这个链表
       * - tail指针用于执行尾插法
       *
       * 对于 val1+val2 >= 10 的情况，在循环外保留变量 carry 用于保存上一次加法的进位值。
       * 也就是说 val1+val2+carry 才是真正的 sum
       **/
      ListNode head = null, tail = null;
      int carry = 0;
      /* 只要 l1 l2 不为空，就可以继续遍历 */
      while (l1 != null || l2 != null || carry != 0) {
        /* 求和 保留进位 */
        int val1 = l1 != null ? l1.val : 0; // 取值
        int val2 = l2 != null ? l2.val : 0; // 取值
        int sum = val1 + val2 + carry; // 求和
        carry = sum / 10; // 保存进位值
        sum = sum % 10; // 更新和的值（处理进位以后）

        /* l1 l2 向后位移 */
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;

        /* 更新out链表 */
        if (head != null) {
          tail.next = new ListNode(sum);
          tail = tail.next;
        } else head = tail = new ListNode(sum);
      }
      // System.out.println(printListNode(head));
      // System.out.println(printListNode(head));
      return head;
    }
  }
}
