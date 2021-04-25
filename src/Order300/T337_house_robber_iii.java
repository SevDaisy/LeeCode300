package Order300;

import BaseNode.TreeNode;

public class T337_house_robber_iii {

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
}
