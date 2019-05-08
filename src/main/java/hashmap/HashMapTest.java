package hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        //统计每个等级的人数
        //<pid,topLevel>
        Map<String,Integer> map=new HashMap<>();
        map.put("1001",3);
        map.put("1002",4);
        map.put("1003",3);

        Map<Integer,Integer> res=new HashMap<>();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            if (res.containsKey(entry.getValue())){
                res.put(entry.getValue(),res.get(entry.getValue())+1);
            }else{
                res.put(entry.getValue(),1);
            }
        }

        System.out.println(res);

    }
}
