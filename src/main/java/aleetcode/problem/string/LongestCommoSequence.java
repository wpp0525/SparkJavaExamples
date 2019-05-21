package aleetcode.problem.string;

/**
 * https://blog.csdn.net/qbyhqp/article/details/82050121
 * 最长公共字串（JAVA实现）
 *
 * 在计算机科学中，最长公共子串问题是寻找两个或多个已知字符串最长的子串。
 * 此问题与最长公共子序列问题的区别在于子序列不必是连续的，而子串却必须是
 *
 * str1="123ABCD4567"      str2 = "ABE12345D6"
 *
 * 最长公共子串是：123
 *
 *
 * 最长公共子串的动态规划解法如下：
 *
 * dp[i][j] -- 表示以str1[i]和str2[j]为结尾的最长公共子串
 * 当str1[i] == str2[j]时，dp[i][j] = dp[i-1][j-1] + 1;
 * 否则，dp[i][j] = 0;
 * 最优解为max(dp[i][j]),其中0<=i<len1, 0<=j<len2;
 *
 */
public class LongestCommoSequence {

    public static void main(String[] args) {

            String str1 = "123ABCD4567";
            String str2 = "ABE12345D6";
            System.out.println(getCommonStrLength(str1, str2));
    }

    private static int getCommonStrLength(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }
}
