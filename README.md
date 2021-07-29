## 个人博客链接

[www.zdiml.xyz](https://www.zdiml.xyz/categories/LeeCode%E4%B8%89%E7%99%BE%E9%A2%98/)

## 部分精选题目编号序列

| LeeCode编号 | 题目名称（链接）                                             | 备注                                                         |
| :---------: | :----------------------------------------------------------- | :----------------------------------------------------------- |
|      5      | [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring) | [官方题解](https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/) **《左神》**`P535`有 `Manacher算法` 的讲解。原理搞懂了，但是官方的解题代码还是没看明白。 |
|     15      | [三数之和](https://leetcode-cn.com/problems/3sum)            | **排序**+**双指针** **排序**是为了**去重**+**限制**了可能**解**的**分布**。**双指针**是为了**优化伪循环** |
|     21      | [合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists) | **1.** 别忘了考虑，提供的 `list_1` `list_2` 为空的情况。**2.** 使用**哨兵节点**简化代码编写 |
|     23      | [合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists) | 方法有三。`1.` 按顺序两两合并 `2.` 分治两两合并 `3.` **头**节点**优先队列**逐节点合并 |
|     42      | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water) | `1.`动态规划（代码不懂），`2.`单调栈（算法不懂，代码也不懂），`3.`双指针（代码不懂） |
|     46      | [全排列](https://leetcode-cn.com/problems/permutations)      | 主要思想是**回溯**，可以用**递归**实现或**迭代**实现         |
|     132     | [分割回文串 II](https://leetcode-cn.com/problems/palindrome-partitioning-ii) |                                                              |
|     141     | [环形链表](https://leetcode-cn.com/problems/linked-list-cycle) | `1.`暴力破解——【定时/定次】循环，`2.` `set`去重，`3.`双指针（快慢指针） |
|     670     | [最大交换](https://leetcode-cn.com/problems/maximum-swap)    |                                                              |
|     121     | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock) |                                                              |
|     122     | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii) |                                                              |
|     123     | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii) |                                                              |
|     188     | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv) |                                                              |
|     309     | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown) |                                                              |
|     714     | [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee) |                                                              |
|             |                                                              |                                                              |
|             | **《左神》**`P279`                                           |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |
|             |                                                              |                                                              |

## LRU Cache

https://leetcode-cn.com/problems/lru-cache-lcci/

拿回《左神代码》对照着写。

## 回溯 专项练习

下面提供一些我做过的「回溯」算法的问题，以便大家学习和理解「回溯」算法。

### 题型一：排列、组合、子集相关问题

提示：这部分练习可以帮助我们熟悉「回溯算法」的一些概念和通用的解题思路。解题的步骤是：先画图，再编码。去思考可以剪枝的条件， 为什么有的时候用 used 数组，有的时候设置搜索起点 begin 变量，理解状态变量设计的想法。

46. 全排列（中等）
47. 全排列 II（中等）：思考为什么造成了重复，如何在搜索之前就判断这一支会产生重复；
39. 组合总和（中等）
40. 组合总和 II（中等）
77. 组合（中等）
78. 子集（中等）
90. 子集 II（中等）：剪枝技巧同 47 题、39 题、40 题；
60. 第 k 个排列（中等）：利用了剪枝的思想，减去了大量枝叶，直接来到需要的叶子结点；
54. 复原 IP 地址（中等）

### 题型二：Flood Fill

提示：Flood 是「洪水」的意思，Flood Fill 直译是「泛洪填充」的意思，体现了洪水能够从一点开始，迅速填满当前位置附近的地势低的区域。类似的应用还有：PS 软件中的「点一下把这一片区域的颜色都替换掉」，扫雷游戏「点一下打开一大片没有雷的区域」。

下面这几个问题，思想不难，但是初学的时候代码很不容易写对，并且也很难调试。我们的建议是多写几遍，忘记了就再写一次，参考规范的编写实现（设置 visited 数组，设置方向数组，抽取私有方法），把代码写对。

733. 图像渲染（Flood Fill，中等）
200. 岛屿数量（中等）
130. 被围绕的区域（中等）
79. 单词搜索（中等）
说明：以上问题都不建议修改输入数据，设置 visited 数组是标准的做法。可能会遇到参数很多，是不是都可以写成成员变量的问题，面试中拿不准的记得问一下面试官

### 题型三：字符串中的回溯问题

提示：字符串的问题的特殊之处在于，字符串的拼接生成新对象，因此在这一类问题上没有显示「回溯」的过程，但是如果使用 StringBuilder 拼接字符串就另当别论。
在这里把它们单独作为一个题型，是希望朋友们能够注意到这个非常细节的地方。

17. 电话号码的字母组合（中等），题解；
784. 字母大小写全排列（中等）；
19. 括号生成（中等） ：这道题广度优先遍历也很好写，可以通过这个问题理解一下为什么回溯算法都是深度优先遍历，并且都用递归来写。

### 题型四：游戏问题

回溯算法是早期简单的人工智能，有些教程把回溯叫做暴力搜索，但回溯没有那么暴力，回溯是有方向地搜索。「力扣」上有一些简单的游戏类问题，解决它们有一定的难度，大家可以尝试一下。

1. N 皇后（困难）：其实就是全排列问题，注意设计清楚状态变量，在遍历的时候需要记住一些信息，空间换时间；
2. 解数独（困难）：思路同「N 皇后问题」；
3. 祖玛游戏（困难）
4. 扫雷游戏（困难）

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
