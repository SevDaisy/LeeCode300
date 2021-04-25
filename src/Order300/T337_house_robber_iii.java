package Order300;

import java.util.HashMap;

import BaseNode.TreeNode;

public class T337_house_robber_iii {

  public static void main(String[] args) {
    TreeNode root = new TreeNode();
    root
      .setVal(4)
      .setLeft(TreeNode.from(1).setLeft(TreeNode.from(2)))
      .setRight(TreeNode.from(0).setLeft(TreeNode.from(3)));
    // -> 9
    System.out.println(new Solution_me_暴力递归().rob(root));
    System.out.println(new Solution_other().rob(root));
    System.out.println(new Solution_me_最优子结构递归().rob(root));
    System.out.println();

    root =
      TreeNode
        .from(3)
        .setLeft(TreeNode.from(2).setRight(TreeNode.from(3)))
        .setRight(TreeNode.from(3).setRight(TreeNode.from(1)));
    // -> 7
    System.out.println(new Solution_me_暴力递归().rob(root));
    System.out.println(new Solution_other().rob(root));
    System.out.println(new Solution_me_最优子结构递归().rob(root));
    System.out.println();

    root =
      TreeNode
        .from(3)
        .setLeft(
          TreeNode.from(4).setLeft(TreeNode.from(1)).setRight(TreeNode.from(3))
        )
        .setRight(TreeNode.from(5).setRight(TreeNode.from(1)));
    // -> 9
    System.out.println(new Solution_me_暴力递归().rob(root));
    System.out.println(new Solution_other().rob(root));
    System.out.println(new Solution_me_最优子结构递归().rob(root));
    System.out.println();
  }

  static class Solution_me_暴力递归 {

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

  /** 通过优化为 最优子结构 来优化动态规划路径。
   * 最优子结构 是 7个节点 的 一颗爷孙二叉树
   * result = max(
   *  val(root) +
   *  rob(root.left.left) + rob(root.left.right) +
   *  rob(root.right.left) + rob(root.right.right)
   *  ,
   *  rob(root.left) + rob(root.right);
   * )
   *
   * 复杂度太高，超时了哈哈哈哈
   */
  static class Solution_me_最优子结构递归 {

    /** 安全的取值函数 —— 无惧输入为 null */
    private int val(TreeNode node) {
      return (node == null) ? 0 : node.val;
    }

    /** 安全的取值函数 —— 无惧输入为 null */
    private TreeNode getSon(TreeNode node, String who) {
      return (node == null)
        ? null
        : ("left".equals(who) ? node.left : node.right);
    }

    public int rob(TreeNode root) {
      /* 鲁棒性 特殊条件 输入过少 */
      if (root == null) {
        return 0;
      }
      /* 可能性一 去 爷爷家 和 四个孙子的小区 */
      int get_5 =
        val(root) +
        rob(getSon(getSon(root, "left"), "right")) +
        rob(getSon(getSon(root, "left"), "left")) +
        rob(getSon(getSon(root, "right"), "left")) +
        rob(getSon(getSon(root, "right"), "right"));
      /* 可能性二 去 两个儿子的小区 */
      int get_2 = rob(getSon(root, "left")) + rob(getSon(root, "right"));
      return Math.max(get_5, get_2);
    }
  }

  /** 
   * 问题结构是数，所以dp不方便用数组，就用 HashMap 存储 node:TreeNode 和 rob(node) 的对应关系。
   * 效率：3ms => 54.44% */
  static class Solution {

    /** 安全的取值函数 —— 无惧输入为 null */
    private int val(TreeNode node) {
      return (node == null) ? 0 : node.val;
    }

    /** 安全的取值函数 —— 无惧输入为 null */
    private TreeNode getSon(TreeNode node, String who) {
      return (node == null)
        ? null
        : ("left".equals(who) ? node.left : node.right);
    }

    public int rob(TreeNode root) {
      return memorizedRob(root, new HashMap<TreeNode, Integer>());
    }

    private int memorizedRob(
      TreeNode root,
      HashMap<TreeNode, Integer> dpTable
    ) {
      /* 鲁棒性 特殊条件 输入过少 */
      if (root == null) {
        return 0;
      }
      if (dpTable.containsKey(root)) {
        return dpTable.get(root);
      }
      /* 可能性一 去 爷爷家 和 四个孙子的小区 */
      int lr = memorizedRob(getSon(getSon(root, "left"), "right"), dpTable);
      int ll = memorizedRob(getSon(getSon(root, "left"), "left"), dpTable);
      int rl = memorizedRob(getSon(getSon(root, "right"), "left"), dpTable);
      int rr = memorizedRob(getSon(getSon(root, "right"), "right"), dpTable);
      int get_5 = val(root) + lr + ll + rl + rr;
      /* 可能性二 去 两个儿子的小区 */
      int get_2 =
        memorizedRob(getSon(root, "left"), dpTable) +
        memorizedRob(getSon(root, "right"), dpTable);
      int robRoot = Math.max(get_5, get_2);
      dpTable.put(root, robRoot);
      return robRoot;
    }
  }
}
