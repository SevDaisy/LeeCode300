package BaseNode;

public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {}

  public TreeNode(int val) {
    this.val = val;
  }

  public static TreeNode from(int val) {
    return new TreeNode(val);
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public TreeNode setVal(int val) {
    this.val = val;
    return this;
  }

  public TreeNode setLeft(TreeNode left) {
    this.left = left;
    return this;
  }

  public TreeNode setRight(TreeNode right) {
    this.right = right;
    return this;
  }
}
