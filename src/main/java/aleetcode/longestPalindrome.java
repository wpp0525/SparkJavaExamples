package aleetcode;


import java.util.LinkedList;

/**
 *  最长回文串
 */

class longestPalindrome {//典型暴力枚举法，显然超时

    public static boolean judge(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        sb.reverse();
        String str=new String(sb);
        if(s.equals(str)) {
            return true;
        }else
            return false;
    }
    public static String longestPalindrome2(String s) {
        int len=s.length();
        if(len<2)
        {
            return s;
        }
        for(int i=len;i>0;i--)
        {
            for(int j=0;j<=len-i;j++)
            {
                String str= s.substring(j,j+i);
                if(judge(str))
                {
                    return str;
                }
            }
        }

        return s;
    }

    public static LinkedList<String> longestPalindrome3(String s) {
        LinkedList<String> result = new LinkedList<String>();
        int len=s.length();
        if(len<2) {
            result.add(s);
            return result;
        }
        for(int i=len;i>0;i--){
            for(int j=0;j<=len-i;j++){

                String str= s.substring(j,j+i);  //先从整体判断,在一步步缩小范围
                if(judge(str)  && str.length() >= 2) {
                    result.add(str);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String longestPalindrome =  longestPalindrome2("abaceecaba111");
        System.out.println(longestPalindrome);

        LinkedList<String> longestPalindrome2 =  longestPalindrome3("abaceecaba111");
        for(String row : longestPalindrome2){
            System.out.println(row);
        }

    }

}
