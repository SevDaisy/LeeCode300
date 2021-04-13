package zyy;

import java.util.Deque;
import java.util.LinkedList;

public class T42_trapping_rain_water {

  static class Solution_动态规划 {

    public int trap(int[] height) {
      int n = height.length;
      if (n == 0) {
        return 0;
      }

      int[] leftMax = new int[n];
      leftMax[0] = height[0];
      for (int i = 1; i < n; ++i) {
        leftMax[i] = Math.max(leftMax[i - 1], height[i]);
      }

      int[] rightMax = new int[n];
      rightMax[n - 1] = height[n - 1];
      for (int i = n - 2; i >= 0; --i) {
        rightMax[i] = Math.max(rightMax[i + 1], height[i]);
      }

      int ans = 0;
      for (int i = 0; i < n; ++i) {
        ans += Math.min(leftMax[i], rightMax[i]) - height[i];
      }
      return ans;
    }
  }

  static class Solution_单调栈 {

    public int trap(int[] height) {
      int ans = 0;
      Deque<Integer> stack = new LinkedList<Integer>();
      int n = height.length;
      for (int i = 0; i < n; ++i) {
        while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
          int top = stack.pop();
          if (stack.isEmpty()) {
            break;
          }
          int left = stack.peek();
          int currWidth = i - left - 1;
          int currHeight = Math.min(height[left], height[i]) - height[top];
          ans += currWidth * currHeight;
        }
        stack.push(i);
      }
      return ans;
    }
  }

  static class Solution_双指针 {

    public int trap(int[] height) {
      int ans = 0;
      int left = 0, right = height.length - 1;
      int leftMax = 0, rightMax = 0;
      while (left < right) {
        leftMax = Math.max(leftMax, height[left]);
        rightMax = Math.max(rightMax, height[right]);
        if (height[left] < height[right]) {
          ans += leftMax - height[left];
          ++left;
        } else {
          ans += rightMax - height[right];
          --right;
        }
      }
      return ans;
    }
  }
}
