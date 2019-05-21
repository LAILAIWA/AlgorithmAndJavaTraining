package categories.java.a6synsugar;

/**
 * @program: datastructure
 * @description: 可变长参数
 * @author: 来建培
 * @create: 2019-05-18
 */
public class VarParm {

    public static void main(String[] args) {
        print("1","2","3");
    }

    public static void print(String... strs){
        for(String str : strs)
            System.out.println(str);
    }
}
