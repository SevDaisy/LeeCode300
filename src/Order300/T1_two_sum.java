package Order300;

import java.util.HashMap;
import java.util.Map;

public class T1_two_sum {

  static class Solution {

    public int[] twoSum(int[] nums, int target) {
      Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
      for (int i = 0; i < nums.length; i++) {
        /**
         * 先判断有没有答案，再将当前元素加入hashtable
         * 如此，满足要求 `数组中同一个元素在答案里不能重复出现`
         **/
        if (hashtable.containsKey(target - nums[i])) {
          return new int[] { hashtable.get(target - nums[i]), i };
        }
        hashtable.put(nums[i], i);
      }
      return new int[0];
    }
  }
}
