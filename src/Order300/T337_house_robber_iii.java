package Order300;

import BaseNode.TreeNode;

public class T337_house_robber_iii {

  public static void main(String[] args) {
    TreeNode root = new TreeNode();
    root
      .setVal(4)
      .setLeft(TreeNode.from(1).setLeft(TreeNode.from(2)))
      .setRight(TreeNode.from(0).setLeft(TreeNode.from(3)));
    // -> 9
    System.out.println(new Solution().rob(root));
    System.out.println(new Solution_other().rob(root));

    root =
      TreeNode
        .from(3)
        .setLeft(TreeNode.from(2).setRight(TreeNode.from(3)))
        .setRight(TreeNode.from(3).setRight(TreeNode.from(1)));
    // -> 7
    System.out.println(new Solution().rob(root));
    System.out.println(new Solution_other().rob(root));

    root =
      TreeNode
        .from(3)
        .setLeft(
          TreeNode.from(4).setLeft(TreeNode.from(1)).setRight(TreeNode.from(3))
        )
        .setRight(TreeNode.from(5).setRight(TreeNode.from(1)));
    // -> 9
    System.out.println(new Solution().rob(root));
    System.out.println(new Solution_other().rob(root));
  }

  static class Solution {

    public int rob(TreeNode root) {
      /* 鲁棒性 特殊条件 输入过少 */
      if (root == null) {
        return 0;
      }
      return Math.max(robSubTree(root, true), robSubTree(root, false));
    }

    private int robSubTree(TreeNode root, boolean isRootSelected) {
      /* 仔细看 我的程序 保证了 robSubTree 的 root 不是 null */
      if (root == null) throw new RuntimeException(
        "robSubTree's root shouldn't be NULL !!!"
      );
      if (isRootSelected) {
        return (
          root.val +
          (root.left == null ? 0 : robSubTree(root.left, !isRootSelected)) +
          (root.right == null ? 0 : robSubTree(root.right, !isRootSelected))
        );
      } else {
        return (
          (root.left == null ? 0 : robSubTree(root.left, !isRootSelected)) +
          (root.right == null ? 0 : robSubTree(root.right, !isRootSelected))
        );
      }
    }
  }

  static class Solution_other {

    public int rob(TreeNode root) {
      int[] result = robInternal(root);
      return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
      if (root == null) return new int[2];
      int[] result = new int[2];

      int[] left = robInternal(root.left);
      int[] right = robInternal(root.right);

      result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
      result[1] = left[0] + right[0] + root.val;

      return result;
    }
  }
}
