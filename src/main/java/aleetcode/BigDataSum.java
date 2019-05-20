package aleetcode;

/**
 *
 * 1、num1和num2的长度都小于5100。
 * 2、num1和num2都只包含数字0-9。
 * 3、num1和num2都不包含处于首位的0。
 * 4、你不能使用任何内置的大数库或者直接将输入转化成整型。
 *
 * 大数相减也是另外一个套路
 */

public class BigDataSum {

    public static String addStrings2(String num1, String num2) {
        StringBuilder result = new StringBuilder(5200);

        int carry = 0; //carry 是进位标志。
        int len1 = num1.length();
        int len2 = num2.length();

        while(len1>0 || len2 > 0 || carry > 0) {
            int tem = carry;
            if(len1 > 0) {
                len1--;
                tem = tem + num1.charAt(len1) - '0';
            }
            if(len2 > 0) {
                len2--;
                tem = tem + num2.charAt(len2) - '0';
            }
            carry = tem/10;
            result.append(tem%10);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {

        String aa = addStrings2(
                "1111111111111111111111111111111111111111111",
                "922222222222222222222222222222222222222222"
        );

        System.out.println(aa);
    }
}
