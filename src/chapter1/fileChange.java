package chapter1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class fileChange {
    public static void main(String[] args){
        File file = new File("D:/22.txt");
        try {
            PrintWriter printWriter = new PrintWriter("D:/0.txt");
            Scanner input = new Scanner(file);
            while (input.hasNext()){
                String line = input.nextLine();
                System.out.println("line: " + line);
                String[] splits = line.split(",");
//                for(int i = 0;i < splits.length;i++)
//                    System.out.print("splits[" + i + "]：" + splits[i] + " ");
//                System.out.println("");
                String result = "set theoryPeriod = " + splits[splits.length-4] + ",pcPeriod = " + splits[splits.length-3] + ",experimentPeriod = "
                        + splits[splits.length-2] + ",totalPeriod = " + splits[splits.length-1] + " " + splits[0] + ";";
                System.out.println("result: " + result);
                printWriter.write(result+"\r\n");
            }
            input.close();
            printWriter.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
