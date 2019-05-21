package categories.java.a3array;

import categories.java.a13thread.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-16
 */
public class Test {
    public static void main(String[] args) {
//        List<String> staff = new LinkedList<>();
//        staff.add("张三");
//        staff.add("李四");
//        staff.add("王五");
//        ListIterator<String> iterator = staff.listIterator();
//        String first = iterator.next();
//        System.out.println("first:" + first);
//        String second = iterator.next();
//        System.out.println("second:" + second);
//        String pre1 = iterator.previous();
//        System.out.println("pre1:" + pre1);
//        iterator.remove();
//        iterator.forEachRemaining(str -> System.out.println(str + " "));

//        SortedSet<Person> people = new TreeSet<>();
//        people.add(new Person("Mary",17));
//        people.add(new Person("Lee",20));
//        people.add(new Person("Ada",17));
//        System.out.println("people:" + people);
//
//        NavigableSet<Person> sortByName = new TreeSet<>(Comparator.comparing(Person::getName));
//        sortByName.addAll(people);
//        System.out.println("sortByName:" + sortByName);

//        PriorityQueue<Person> people = new PriorityQueue<>();
//        people.add(new Person("Mary",17));
//        people.add(new Person("Lee",20));
//        people.add(new Person("Ada",17));
//        System.out.println("Iterating over elements ");
//        for(Person person : people)
//            System.out.println(person.toString());
//        System.out.println("Removing elements ");
//        while (!people.isEmpty())
//            System.out.println("remove:" + people.remove().toString());

        //示例通过Lambda表达式迭代处理键值对。
//        Map<String,Person> personMap = new HashMap<>();
//        personMap.put("001",new Person("Mary",17));
//        personMap.put("002",new Person("Lee",20));
//        personMap.put("003",new Person("Ada",17));
//
//        personMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));
//        personMap.replaceAll((k,vo) -> new Person("Mary",18));
//        personMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));

        //实现姓名计数器
//        Map<String,Integer> nameMap = new HashMap<>();
//        nameMap.put("Michael",0);
//        nameMap.put("Lay",0);
//        nameMap.put("Amanda",0);
//        nameMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));
//        //put更新
//        //System.out.println("put更新");
//        //nameMap.put("Michael",nameMap.get("Michael")+1);
//        //nameMap.put("Jack",nameMap.get("Jack")+1);//java.lang.NullPointerException
//        //nameMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));
//        //put+getOrDefault更新
//        System.out.println("put+getOrDefault更新");
//        nameMap.put("Michael",nameMap.getOrDefault("Michael",0)+1);
//        nameMap.put("Jack",nameMap.getOrDefault("Jack",0)+1);
//        nameMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));
//        //putIfAbsent+put更新
//        System.out.println("putIfAbsent+put更新");
//        nameMap.putIfAbsent("Michael",0);
//        nameMap.put("Michael",nameMap.get("Michael")+1);
//        nameMap.putIfAbsent("Jack",0);
//        nameMap.put("Jack",nameMap.get("Jack")+1);
//        nameMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));
//        //merge更新
//        System.out.println("merge更新");
//        nameMap.merge("Michael",1,Integer::sum);
//        nameMap.merge("Jack",1,Integer::sum);
//        nameMap.forEach((k,v) -> System.out.println("key[" + k + "] value[" + v + "]"));

//        System.out.println("always");
//        print(always);
//        System.out.println("never");
//        print(never);
//        System.out.println("workday");
//        print(workday);
//        System.out.println("mwf");
//        print(mwf);

        LocalDateTime time = LocalDateTime.now();
        String value = time.format(DateTimeFormatter.ofPattern("d-MMM-uuuu").withLocale(Locale.US));
        System.out.println(value);

//        NavigableSet<Person> syncCodeList = new TreeSet<Person>(Comparator.comparingInt(Person::getAge));
//        Person a = new Person("a",19);
//        Person b = new Person("b",17);
//        Person c = new Person("c",18);
//        syncCodeList.add(a);
//        syncCodeList.add(b);
//        syncCodeList.add(c);
//        syncCodeList.forEach(sc->System.out.println(sc.toString()));
//        b.setAge(30);
//        c.setAge(10);
//        syncCodeList.forEach(sc->System.out.println(sc.toString()));
//
//        int[] nums = {2,7,11,15};
//        int[] result = twoSum(nums,20);
//        for(int i = 0;i < result.length;i++){
//            System.out.println(result[i]);
//        }
    }

    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0;i < nums.length;i++){
            for(int j = nums.length-1;j >= 0;j--){
                if(nums[i] + nums[j] == target){
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public static void pr(String s){
        int syncCode = "-1".equals(s) ?
                0 : (syncCode = s.hashCode()) ^ (syncCode >>> 16);
        System.out.println("s: " + s + " code: " + syncCode);
    }

    enum WeekDay {MONDAY,TUESDAY,WEDNESDAY,THURSTAY,FRIDAY,SATURDAY,SUNDAY};
    static EnumSet<WeekDay> always = EnumSet.allOf(WeekDay.class);
    static EnumSet<WeekDay> never = EnumSet.noneOf(WeekDay.class);
    static EnumSet<WeekDay> workday = EnumSet.range(WeekDay.MONDAY,WeekDay.FRIDAY);
    static EnumSet<WeekDay> mwf = EnumSet.of(WeekDay.MONDAY,WeekDay.WEDNESDAY,WeekDay.FRIDAY);

    public static void print(EnumSet set){
        set.forEach(v -> System.out.println("value[" + v + "]"));
    }
}
