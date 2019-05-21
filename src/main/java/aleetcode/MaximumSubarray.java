package aleetcode;

public class MaximumSubarray {

    public static void main(String[] args) throws Exception {
//        int[] A = {-2, 1, -3, 4, -1, 2, 1,100,-4, 6, 4};

        int[] A = {-2, 1, 100, 40, -1, 2, 1,5,-4, 6, 4};
        System.out.println(new MaximumSubarray().maxSubArray(A));

        System.out.println(new MaximumSubarray().maxSubArray2(A));

    }

    // 自己实现的。 把第一个当成最大，
    public int maxSubArray2(int[] A) {

        if (A.length == 1){
            return A[0];
        }
        int max = A[0];  //当成最大值

        for (int i = 0; i <= A.length - 2; i++) {
            A[i] = Math.max(A[i], A[i] + A[i+1]); //一定是 a[i],递增的情况
            max = Math.max(max, A[i+1]);// max 不跟任何数相加
        }
        return max;
    }


    public int maxSubArray(int[] A) {

        if (A.length == 1){
            return A[0];
        }
        int max = A[A.length - 1];  //当成最大值

        for (int i = A.length - 2; i >= 0; i--) {
            A[i] = Math.max(A[i], A[i] + A[i + 1]);
            max = Math.max(max, A[i]);
        }
        return max;
    }



}