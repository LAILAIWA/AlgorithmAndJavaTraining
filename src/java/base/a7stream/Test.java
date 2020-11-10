package java.base.a7stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流相关代码
 */
public class Test {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700,Dish.Type.MEAT),
                new Dish("chicken",false,400,Dish.Type.MEAT),
                new Dish("french fries",true,530,Dish.Type.OTHER),
                new Dish("rice",true,350,Dish.Type.OTHER),
                new Dish("season fruit",true,120,Dish.Type.OTHER),
                new Dish("pizza",true,550,Dish.Type.OTHER),
                new Dish("prawns",false,300,Dish.Type.FISH),
                new Dish("salmon",false,450,Dish.Type.FISH)
        );

        List<String> threeHighCaloricDishNames = menu.stream() //从集合获得流，准备创建流水线
                .filter(d -> d.getCalories() > 300) //筛选高热量的菜肴
                .map(Dish::getName) //获取菜名
                .limit(3) //只选择头三个
                .collect(Collectors.toList()); //将结果存在另一个List中
        System.out.println(threeHighCaloricDishNames);

        Stream s = menu.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println); 流和迭代器一样不能多次遍历

        List<String> names = new ArrayList<>();
        //集合通过for-each循环外部迭代
        for(Dish dish : menu){
            names.add(dish.getName());
        }
        //集合通过迭代器做外部迭代
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()){
            Dish d = iterator.next();
            names.add(d.getName());
        }
        //流内部迭代
        names = menu.stream().map(Dish::getName).collect(Collectors.toList());

        System.out.println("打印每个操作");
        //打印每个操作
        List<String> delayNames = menu.stream() //从集合获得流，准备创建流水线
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                }) //筛选高热量的菜肴
                .map(d -> {
                    System.out.println("maping " + d.getName());
                    return d.getName();
                }) //获取菜名
                .limit(3) //只选择头三个
                .collect(Collectors.toList()); //将结果存在另一个List中
        System.out.println(threeHighCaloricDishNames);

        List<String> words = Arrays.asList("Java 8","Lambdas","In","Action");
        //对流中每个元素应用函数
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(wordLengths);
        //流的扁平化
        //通过map把单词映射为字符列表，再distinct去重，但返回是Stream<String[]>
        List<String[]> wordCharsArray = words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
        wordCharsArray.forEach(wordChar -> System.out.println(Arrays.toString(wordChar)));
        //我们想要的是Stream<String>而不是Stream<String[]>，Arrays::stream这个方法好像可以把数组转为流，但只是把字符数组变为了流
        words.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
        //再通过flatMap
        List<String> wordChars = words.stream()
                .map(word -> word.split("")) //将每个单词转换为由其字母构成的数组
                .flatMap(Arrays::stream) //将各个生成流扁平化为单个流
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordChars);

        //归约
        //元素求和
        int sum = menu.stream().map(Dish::getCalories).reduce(0,(a, b) -> a + b);
        System.out.println("sum: " + sum);
        //最大值和最小值
        Optional<Integer> max = menu.stream().map(Dish::getCalories).reduce(Integer::max);
        System.out.println("max: " + max);
        Optional<Integer> min = menu.stream().map(Dish::getCalories).reduce(Integer::min);
        System.out.println("min: " + min);

        //原始类型流特化
        int sumSpec = menu.stream()
                .mapToInt(Dish::getCalories) //先映射为IntStream
                .sum(); //再调用IntStream提供的sum()求和
        System.out.println("sumSpec: " + sumSpec);

        //勾股数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100) //从1至100取值
                .boxed() //数值流装箱转换为Stream<Integer>
                .flatMap(a -> //把生成的三元流扁平为一个三元数流
                        IntStream.rangeClosed(a,100) //从a至100取值，避免重复
                                .filter(b -> //过滤不合法的(a, b)取值
                                        Math.sqrt(a * a + b * b) % 1 == 0) //Math.sqrt(a * a + b * b) % 1 == 0判断c是否为整数
                                .mapToObj(b -> //映射出三元组
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        System.out.println("++++++++++++++++++++");
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        //优化
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1,100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}) //先生成所有三元组
                                .filter(t -> t[2] % 1 == 0)); //再筛选掉不合法的
        System.out.println("++++++++++++++++++++");
        pythagoreanTriples2.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        //由值创建流
        Stream<String> stringStream = Stream.of("a","b");
        //由数组创建流
        int[] a = {1,2,3};
        Stream<Integer> integerStream = Arrays.stream(a).boxed();
        //由文件生成流
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("d:/data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println("文件中不重复单词有：" + uniqueWords + "个");
        //由函数生成流
        //迭代
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        //生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        //斐波那契数列，每个数字都是前两个数字之和
        //打印二元组
        Stream.iterate(new int[]{0, 1}, temp -> new int[]{temp[1], temp[0] + temp[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
        //打印斐波那契数列
        Stream.iterate(new int[]{0, 1}, temp -> new int[]{temp[1], temp[0] + temp[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
        //实例化IntSupplier，修改状态，实现斐波那契数列
        IntSupplier fib = new IntSupplier() {
            private int pre = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int old = this.pre;
                int next = this.pre + this.current;
                this.pre = this.current;
                this.current = next;
                return old;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

        //归约与汇总
        //统计：统计菜单有多少道菜肴
        long dishedNum = menu.stream().collect(Collectors.counting());
        System.out.println("dishedNum：" + dishedNum);
        long dishedNum1 = menu.stream().count();
        System.out.println("dishedNum1：" + dishedNum1);
        //求最大/最小值：找出热量最高的菜肴
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println("mostCaloriesDish：" + mostCaloriesDish);
        Optional<Dish> mostCaloriesDish1 = menu.stream().max(dishCaloriesComparator);
        System.out.println("mostCaloriesDish1：" + mostCaloriesDish1);
        //汇总求和：求所有菜肴总热量
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("totalCalories：" + totalCalories);
        //汇总求平均：求所有菜肴热量平均值
        double averageCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println("averageCalories：" + averageCalories);
        //一次遍历求所有汇总结果
        IntSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("statistics：" + statistics);
        //连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println("shortMenu：" + shortMenu);
        //加分隔符
        String shortMenu1 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("shortMenu1：" + shortMenu1);

        //前面都是常用情况的便捷工具，reducing()则需要三个参数(初始值，转换，操作)
        int totalCalories3 = menu.stream().collect(Collectors.reducing(0,Dish::getCalories,(i,j) -> i + j));
        System.out.println("totalCalories3：" + totalCalories3);
        int totalCalories4 = menu.stream().map(Dish::getCalories).reduce(0,(i,j) -> i + j);
        System.out.println("totalCalories4：" + totalCalories4);
        //单参数reducing()，流中第一个元素作为起点，转换函数为默认输入参数。
        Optional<Dish> mostCaloriesDish2 = menu.stream()
                .collect(Collectors.reducing(
                        (d1,d2) -> d1.getCalories() > d2.getCalories() ? d1 :d2));
        System.out.println("mostCaloriesDish2：" + mostCaloriesDish2);

        //这种写法最简洁，且性能最好，避免了自动拆箱
        int totalCalories1 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("totalCalories1：" + totalCalories1);

        //分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        dishesByType.forEach((k,v) -> System.out.println(k + "->" + v));

        //以热量分组
        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(
           dish -> {
               if(dish.getCalories() <= 400) return CaloricLevel.DIET;
               else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
               else return CaloricLevel.FAT;
           }
        ));
        dishesByCaloricLevel.forEach((k,v) -> System.out.println(k + "->" + v));

        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(dish ->{
                                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }))
                );
        dishesByTypeCaloricLevel.forEach((k,v) -> System.out.println(k + "->" + v));

        //按子组收集数据
        //分类汇总求和
        Map<Dish.Type, Long> typesCount =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,Collectors.counting()));
        typesCount.forEach((k,v) -> System.out.println(k + "->" + v));
        //分类汇总取最大值
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
        mostCaloricByType.forEach((k,v) -> System.out.println(k + "->" + v));

        //收集后转换类型
        Map<Dish.Type, Dish> mostCaloricByType1 =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Dish::getCalories)),Optional::get)));
        mostCaloricByType1.forEach((k,v) -> System.out.println(k + "->" + v));

        //分区
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        partitionedMenu.forEach((k,v) -> System.out.println(k + "->" + v));

        //收集器接口
        List<Dish> dishes = menu.stream().collect(Collectors.toList());
        System.out.println("-------------");
        dishes.forEach(System.out::println);
        //toList是一个工厂方法由单例生成，ToListCollector则需要实例化
        List<Dish> dishes1 = menu.stream().collect(new ToListCollector<>());
        System.out.println("-------------");
        dishes1.forEach(System.out::println);
        //简单但不易读，且收集器永远是IDENTITY_FINISH+CONCURRENT且非UNORDERED的
        List<Dish> dishes2 = menu.stream().collect(ArrayList::new, List::add,List::addAll);
        System.out.println("-------------");
        dishes2.forEach(System.out::println);

        //实现收集器
        //将前n个自然数划分为质数和非质数(大于1的自然数中，除了1和它本身以外不再有其他因数的自然数)
        System.out.println("将前n个自然数划分为质数和非质数");
        Map<Boolean, List<Integer>> primes = partitionPrimes(5);
        primes.forEach((k,v) -> System.out.println(k + "->" + v));

        //优化：自定义收集器
        System.out.println("优化：自定义收集器");
        Map<Boolean, List<Integer>> primes1 = partitionPrimesWithPrimeNumbersCollector(5);
        primes1.forEach((k,v) -> System.out.println(k + "->" + v));

        //测试性能
        measurePartitionPrimes();
        measurepartitionPrimesWithPrimeNumbersCollector();
    }
    public enum CaloricLevel {DIET,NORMAL,FAT};

    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithPrimeNumbersCollector(int n){
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    public static void measurePartitionPrimes(){
        long fastest = Long.MAX_VALUE;
        for(int i = 0;i < 10;i++){//测试运行10次
            long start = System.nanoTime();
            partitionPrimes(1_000_000);//前一百万自然数分质数
            long duration = (System.nanoTime() - start) / 1_000_000;//取运行时间的毫秒值
            if(duration < fastest)//检查执行是否是最快的一个
                fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    public static void measurepartitionPrimesWithPrimeNumbersCollector(){
        long fastest = Long.MAX_VALUE;
        for(int i = 0;i < 10;i++){//测试运行10次
            long start = System.nanoTime();
            partitionPrimesWithPrimeNumbersCollector(1_000_000);//前一百万自然数分质数
            long duration = (System.nanoTime() - start) / 1_000_000;//取运行时间的毫秒值
            if(duration < fastest)//检查执行是否是最快的一个
                fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }
}
