package java.base.a3array;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        List<String> staff = new LinkedList<>();
        staff.add("张三");
        staff.add("李四");
        staff.add("王五");
        ListIterator<String> iterator = staff.listIterator();
        String first = iterator.next();
        System.out.println("first:" + first);
        String second = iterator.next();
        System.out.println("second:" + second);
        String pre1 = iterator.previous();
        System.out.println("pre1:" + pre1);
        iterator.remove();
        iterator.forEachRemaining(str -> System.out.println(str + " "));

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

//        int n = 0;
//        n = n - (n >>> 2);
//        int rs = resizeStamp(64);
//        System.out.println("resizeStamp " + rs);
//        int sizeCtrl = (rs << 16) + 2;
//        System.out.println("sizeCtrl = (rs << 16) + 2 " + sizeCtrl);
//        System.out.println("sizeCtrl-1 " + (sizeCtrl-1));
//        System.out.println("rs+1 " + (rs+1));
//        System.out.println("resizeStamp " + rs);

        List<BigDecimal> counter = new ArrayList<>();
        counter.add(new BigDecimal(6.0 * 64.0));
        counter.add(new BigDecimal(3.0 * 73.0));
        counter.add(new BigDecimal(0.5 * 79.0));
        counter.add(new BigDecimal(1.5 * 76.0));
        counter.add(new BigDecimal(2.0 * 73.0));
        counter.add(new BigDecimal(2.0 * 75.0));
        counter.add(new BigDecimal(2.0 * 78.0));
        counter.add(new BigDecimal(1.0 * 65.0));
        counter.add(new BigDecimal(3.0 * 93.0));
        counter.add(new BigDecimal(2.5 * 87.0));
        counter.add(new BigDecimal(2.0 * 92.8));
        counter.add(new BigDecimal(1.0 * 76.0));
        counter.add(new BigDecimal(1.0 * 86.0));
        counter.add(new BigDecimal(2.0 * 90.0));
        counter.add(new BigDecimal(1.0 * 71.0));
        counter.add(new BigDecimal(1.0 * 87.0));
        counter.add(new BigDecimal(1.0 * 80.0));
        counter.add(new BigDecimal(4.0 * 70.0));
        counter.add(new BigDecimal(5.0 * 85.0));
        counter.add(new BigDecimal(1.5 * 76.0));
        counter.add(new BigDecimal(2.0 * 80.0));
        counter.add(new BigDecimal(1.0 * 87.0));
        counter.add(new BigDecimal(1.0 * 62.0));
        counter.add(new BigDecimal(3.0 * 74.0));
        counter.add(new BigDecimal(2.0 * 91.0));
        counter.add(new BigDecimal(1.0 * 96.0));
        counter.add(new BigDecimal(4.0 * 60.0));
        counter.add(new BigDecimal(3.0 * 77.0));
        counter.add(new BigDecimal(3.0 * 74.0));
        counter.add(new BigDecimal(0.5 * 80.0));
        counter.add(new BigDecimal(1.5 * 76.0));
        counter.add(new BigDecimal(2.0 * 80.0));
        counter.add(new BigDecimal(1.0 * 90.0));
        counter.add(new BigDecimal(1.0 * 82.0));
        counter.add(new BigDecimal(2.0 * 80.0));
        counter.add(new BigDecimal(3.5 * 65.0));
        counter.add(new BigDecimal(4.0 * 76.0));

        counter.add(new BigDecimal(1.0 * 94.0));
        counter.add(new BigDecimal(4.0 * 75.0));
//        counter.add(new BigDecimal(2.0 * P));
//        counter.add(new BigDecimal(2.0 * P));
        counter.add(new BigDecimal(1.0 * 95.0));
        counter.add(new BigDecimal(3.0 * 78.0));
        counter.add(new BigDecimal(1.5 * 84.0));
        counter.add(new BigDecimal(2.0 * 80.0));
        counter.add(new BigDecimal(1.0 * 81.0));
        counter.add(new BigDecimal(3.5 * 84.0));
        counter.add(new BigDecimal(3.5 * 60.0));
        counter.add(new BigDecimal(2.0 * 94.0));
        counter.add(new BigDecimal(3.0 * 95.0));
        counter.add(new BigDecimal(2.0 * 84.0));
        counter.add(new BigDecimal(1.0 * 82.0));
        counter.add(new BigDecimal(2.0 * 96.0));
        counter.add(new BigDecimal(2.0 * 75.0));
        counter.add(new BigDecimal(2.5 * 90.0));
        counter.add(new BigDecimal(1.0 * 84.0));
        counter.add(new BigDecimal(1.0 * 90.0));
        counter.add(new BigDecimal(0.5 * 80.0));
        counter.add(new BigDecimal(3.0 * 72.0));
        counter.add(new BigDecimal(1.0 * 85.0));
        counter.add(new BigDecimal(1.0 * 72.0));
        counter.add(new BigDecimal(4.0 * 73.0));
        counter.add(new BigDecimal(3.5 * 80.0));
        counter.add(new BigDecimal(2.5 * 88.0));
        counter.add(new BigDecimal(2.5 * 70.0));
        counter.add(new BigDecimal(1.0 * 88.0));
        counter.add(new BigDecimal(3.0 * 76.0));
        counter.add(new BigDecimal(1.0 * 86.0));
        counter.add(new BigDecimal(3.0 * 82.0));
        counter.add(new BigDecimal(1.0 * 85.0));
        counter.add(new BigDecimal(2.0 * 74.0));
        counter.add(new BigDecimal(1.0 * 84.0));
        counter.add(new BigDecimal(1.0 * 81.0));

        counter.add(new BigDecimal(1.0 * 92.0));
//        counter.add(new BigDecimal(0.5 * P));
        counter.add(new BigDecimal(2.0 * 70.0));
        counter.add(new BigDecimal(0.5 * 82.0));
        counter.add(new BigDecimal(2.5 * 82.0));
        counter.add(new BigDecimal(3.0 * 85.0));
//        counter.add(new BigDecimal(2.0 * P));
        counter.add(new BigDecimal(7.0 * 89.0));
        counter.add(new BigDecimal(0.5 * 95.0));

        List<BigDecimal> counter1 = new ArrayList<>();
        counter1.add(new BigDecimal(6.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(0.5));
        counter1.add(new BigDecimal(1.5));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(2.5));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(4.0));
        counter1.add(new BigDecimal(5.0));
        counter1.add(new BigDecimal(1.5));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(4.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(0.5));
        counter1.add(new BigDecimal(1.5));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(3.5));
        counter1.add(new BigDecimal(4.0));

        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(4.0));
//        counter1.add(new BigDecimal(2.0P));
//        counter1.add(new BigDecimal(2.0P));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(1.5));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(3.5));
        counter1.add(new BigDecimal(3.5));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(2.5));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(0.5));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(4.0));
        counter1.add(new BigDecimal(3.5));
        counter1.add(new BigDecimal(2.5));
        counter1.add(new BigDecimal(2.5));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(3.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(1.0));
        counter1.add(new BigDecimal(1.0));

        counter1.add(new BigDecimal(1.0));
//        counter1.add(new BigDecimal(0.5P));
        counter1.add(new BigDecimal(2.0));
        counter1.add(new BigDecimal(0.5));
        counter1.add(new BigDecimal(2.5));
        counter1.add(new BigDecimal(3.0));
//        counter1.add(new BigDecimal(2.0P));
        counter1.add(new BigDecimal(7.0));
        counter1.add(new BigDecimal(0.5));

        BigDecimal a = new BigDecimal(0);
        for(BigDecimal b : counter){
            a = a.add(b);
        }
        System.out.println("counter = " + counter.size());
        System.out.println("counter1 = " + counter1.size());
        System.out.println("a = " + a);
        BigDecimal c = new BigDecimal(0);
        for(BigDecimal b : counter1){
            c = c.add(b);
        }
        System.out.println("c = " + c);

        System.out.println("a / c = " + a.divide(c,10, RoundingMode.HALF_UP));
//
//        System.out.println("1 << (RESIZE_STAMP_BITS - 1) " + Integer.toBinaryString(1 << (16 - 1)));
//        System.out.println(" " + (31 | 1 << (16 - 1)) );
//        for(int i = -2;i < 64;i++){
//            System.out.println(i + " : "  + numberOfLeadingZeros(i));
//        }
//        System.out.println("1 "  + Integer.toBinaryString(1));
//        System.out.println("-1 "  + Integer.toBinaryString(-1));
//        System.out.println("64 "  + Integer.toBinaryString(64));
//        System.out.println("-64 "  + Integer.toBinaryString(-64));
//        List<String> a1 = new ArrayList<>(1);
//        System.out.println("a1 : " + a1.getClass().getName() + "@" + Integer.toHexString(a1.hashCode()));
//        a1.add("a");
//        System.out.println("a1 : " + a1.getClass().getName() + "@" + Integer.toHexString(a1.hashCode()));
//        a1.add("b");
//        System.out.println("a1 : " + a1.getClass().getName() + "@" + Integer.toHexString(a1.hashCode()));
//        a1.add("c");
//        System.out.println("a1 : " + a1.getClass().getName() + "@" + Integer.toHexString(a1.hashCode()));
//        a1.add("d");
//        System.out.println("a1 : " + a1.getClass().getName() + "@" + Integer.toHexString(a1.hashCode()));
//
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("b");
//        //普通for循环
//        for(int i = 0;i < list.size();i++) {
//            String s = list.get(i);
//            if("b".equals(s)) {
//                list.remove(s);
//            }
//        }
//
//        System.out.println(list);
//        list.add("b");
//        //for-each
//        for(String s : list) {
//            if(s.equals("b")) {
//                list.remove(s);
//            }
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

    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        //高16位为0，n为17，i左移16位
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        //高8位为0，n为9，i左移8位
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        //高4位为0，n为5，i左移4位
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        //高2位为0，n为3，i左移2位
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        System.out.println("此时n " + n + " i " + i + " "+ Integer.toBinaryString(i));
        n -= i >>> 31;
        return n;
    }
}
