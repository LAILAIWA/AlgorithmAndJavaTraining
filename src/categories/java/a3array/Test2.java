package categories.java.a3array;

import java.util.*;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-04-15
 */
public class Test2 {


    public static void main(String[] args) {

//        Map<String, String> personMap = new HashMap<>();
//        personMap.put("001","Mary");
//        personMap.put("002","Lee");
//        personMap.put("003","Ada");
//
//        replace1(personMap,"001","a");
//        System.out.println("--------------------");
//        replace2(personMap,"001","a");

        int a = 1;
        for(int i = 0;i < 10;i++){
            a = i;
        }
        System.out.println("a: " + a);
    }

    public static String replace1(Map<String, String> personMap,String key,String value){
        if(personMap.containsKey(key)){
            return personMap.put(key, value);
        }else
            return null;
    }
    public static String replace2(Map<String, String> personMap,String key,String value){
        String curValue;
        if (((curValue = personMap.get(key)) != null) || personMap.containsKey(key)) {
            curValue = personMap.put(key, value);
        }
        return curValue;
    }
}
