package BaseNode;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

  public int val;
  public ListNode next;

  public ListNode() {}

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public List<Integer> toList() {
    List<Integer> out = new ArrayList<Integer>();
    ListNode cur = this;
    do {
      out.add(cur.val);
      cur = cur.next;
    } while (cur != null);
    return out;
  }

  public ListNode add(int val) {
    this.next = new ListNode(val);
    return this.next;
  }
}
