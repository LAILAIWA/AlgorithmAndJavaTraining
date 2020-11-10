package java.base.a4trycatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-05-18
 */
public class TryWithResource {
    public static void main(String[] args){
        try (Scanner in = new Scanner(new FileInputStream("/in.txt"),"UTF-8");
             PrintWriter out = new PrintWriter("/out.txt")){
            while (in.hasNext())
                out.println(in.next().toUpperCase());
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
