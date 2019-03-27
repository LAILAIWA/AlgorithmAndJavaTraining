package synsugar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @program: datastructure
 * @description: foreach测试
 * @author: 来建培
 * @create: 2019-01-04
 */
public class foreach {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Child");
        list.add("b");
        list.add("c");
        list.add("d");
        for(int i = 0;i < list.size();i++){
            if(i == 0)
                list.remove(i);
        }

        System.out.println(list);

        List<String> list1 = new ArrayList<>();
        list1.add("Child");
        list1.add("b");
        list1.add("c");
        list1.add("d");

        for(Iterator<String> iterator = list1.iterator();iterator.hasNext();){
            String s = iterator.next();
            if(s.equals("b"))
                iterator.remove();
        }
        System.out.println(list1);
    }

    public static List<String> getListIte(){
        ArrayList<String> strings = new ArrayList<String>(){
            @Override
            public Iterator<String> iterator() {
                System.out.println("iterator 被调用");
                return super.iterator();
            }
        };

        strings.add("Brown");
        strings.add("Carl");
        return strings;
    }

    public static List<String> getList(){
        System.out.println("getList 被调用");
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Brown");
        strings.add("Carl");
        return strings;
    }


}
