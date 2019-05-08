package org.apache.spark.examples;

import java.util.HashMap;
import java.util.Hashtable;

public class MapTest {
    public static void main(String[] args) {
        Hashtable numbers = new Hashtable();
        numbers.put("one", new Integer(1));
        numbers.put("two", new Integer(2));
        numbers.put("three", new Integer(3));
        numbers.put("three", new Integer(3));

        System.out.println(numbers);

        HashMap numbers2 = new HashMap();
        numbers2.put("one", new Integer(1));
        numbers2.put("two", new Integer(2));
        numbers2.put("three", new Integer(3));
        numbers2.put("4", new Integer(4));

        numbers2.put(null, new Integer(4));
        numbers2.put( new Integer(43),null);

        int aa = 2;
        numbers2.put( new Integer(436),aa);

        System.out.println("hashmap");
        System.out.println("numbers2.get(null) : " +numbers2.get(null));
        System.out.println(numbers2);

        numbers2.remove("4");
        System.out.println(numbers2);
        System.out.println(numbers2.entrySet());
        System.out.println(numbers2.keySet().size());

    }
}
