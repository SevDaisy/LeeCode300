package Order300;

public class T11_container_with_most_water {

  public static void main(String[] args) {
    System.out.println(
      new Solution().maxArea(new int[] { 0, 14, 6, 2, 10, 9, 4, 1, 10, 3 })
    );
  }

  static class Solution {

    public int maxArea(int[] height) {
      int left = 0;
      int right = height.length - 1;
      int answer = Math.min(height[left], height[right]) * (right - left);
      while (left < right) {
        if (height[left] < height[right]) {
          int old = height[left];
          while (left < right && height[++left] < old);
        } else {
          int old = height[right];
          while (left < right && height[--right] < old);
        }
        if (left >= height.length || right < 0) break;
        answer =
          Math.max(
            answer,
            Math.min(height[left], height[right]) * (right - left)
          );
      }
      return answer;
    }
  }
}
