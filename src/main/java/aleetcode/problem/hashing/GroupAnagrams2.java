package aleetcode.problem.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 10/03/2017.
 * Given an array of strings, group anagrams together.
 * <p>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p>
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 *
 *
 */

/**
 * 这道题让我们群组给定字符串集中所有的错位词，所谓的错位词就是两个字符串中字母出现的次数都一样，只是位置不同，
 * 比如abc，bac, cba等它们就互为错位词，那么我们如何判断两者是否是错位词呢，我们发现如果把错位词的字符顺序重新排列，
 * 那么会得到相同的结果，所以重新排序是判断是否互为错位词的方法，由于错位词重新排序后都会得到相同的字符串，
 * 我们以此作为key，将所有错位词都保存到字符串数组中，建立key和字符串数组之间的映射，最后再存入结果res中即可.
 */
public class GroupAnagrams2 {

    private int[] A = new int[256];
    private HashMap<String, List<String>> hashMap = new HashMap<>(); //相同的错位词都包含在一个值里。
    private List<List<String>> result = new ArrayList<>();

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        String[] strs = {"huh", "tit"};
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = new GroupAnagrams2().groupAnagrams(strs);
        for (List<String> row : result) {
            for (String s : row) {
                System.out.println(s);
            }
            System.out.println("-----");
        }
    }

    public  List<List<String>> groupAnagrams(String[] strs) {
        for (int i = 0, l = strs.length; i < l; i++) {
            Arrays.fill(A, 0);
            String s = strs[i];
            for (int j = 0, sl = s.length(); j < sl; j++)
                A[s.charAt(j)]++;

            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < 256; k++) {
                if (A[k] != 0)
                    sb.append(k).append("").append(A[k]);
            }
            List<String> value = hashMap.get(sb.toString());
            if (value == null)
                value = new ArrayList<>();
            value.add(s);
            hashMap.put(sb.toString(), value);
        }

        for (String s : hashMap.keySet())
            result.add(hashMap.get(s));

        return result;
    }
}
