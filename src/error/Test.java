package error;

import java.io.*;
import java.util.Scanner;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-16
 */
public class Test {

    public static void main(String[] args){
        try (Scanner in = new Scanner(new FileInputStream("C:\\Users\\LinYi\\Desktop\\in.txt"),"UTF-8");
             PrintWriter out = new PrintWriter("C:\\Users\\LinYi\\Desktop\\out.txt")){
            while (in.hasNext())
                out.println(in.next().toUpperCase());
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static int f(int n){
        try{
            int r = n * n;
            return r;
        }finally {
            if(n == 2)
                return 0;
        }
    }
}
