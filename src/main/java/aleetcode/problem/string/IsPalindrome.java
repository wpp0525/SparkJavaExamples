package aleetcode.problem.string;

/**
 *
 */
public class IsPalindrome {

    public static boolean isHuiWen(String str) {
        int length = str.length();
        char[] charArr = str.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            if (charArr[i] != charArr[length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String str = "abc1cba";
        System.out.println(isHuiWen(str));
    }
}
