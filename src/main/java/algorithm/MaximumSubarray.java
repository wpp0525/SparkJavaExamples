package algorithm;

public class MaximumSubarray {

    public static void main(String[] args) throws Exception {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1,100,-4, 6, 4};
        System.out.println(new MaximumSubarray().maxSubArray(nums));

        System.out.println(new MaximumSubarray().maxSubArray2(nums));

    }

    // 自己实现的。
    public int maxSubArray2(int[] nums) {

        if (nums.length == 1){
            return nums[0];
        }
        int max = nums[0];  //当成最大值

        for (int i = 1; i <= nums.length - 2; i++) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i + 1]);
            max = Math.max(max, nums[i+1]);
        }

        return max;
    }


    public int maxSubArray(int[] nums) {

        if (nums.length == 1){
            return nums[0];
        }
        int max = nums[nums.length - 1];  //当成最大值

        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i + 1]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }



}