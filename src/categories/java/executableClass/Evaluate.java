package categories.java.executableClass;

import java.util.Scanner;
import java.util.Stack;

public class Evaluate {
    static Stack<Double> vals = new Stack<>();
    static Stack<String> ops = new Stack<>();

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String in = input.next();
            if(in.equals("/r/n")){//判断结束
                input.close();
                break;
            }else{
                count(in);
            }
        }
        System.out.println(vals.peek());
    }

    public static void count(String in){
        //操作数栈和运算符栈
        if(in.equals("("));
        else if(in.equals("+"))  ops.push(in);
        else if(in.equals("-"))  ops.push(in);
        else if(in.equals("*"))  ops.push(in);
        else if(in.equals("/"))  ops.push(in);
        else if(in.equals("sqrt")) ops.push(in);
        else if(in.equals(")")){
            //取出运算符和操作数
            String op = ops.pop();
            Double val = vals.pop();
            if(op.equals("+")) val += vals.pop();
            else if(op.equals("-")) val -= vals.pop();
            else if(op.equals("*")) val *= vals.pop();
            else if(op.equals("/")) val /= vals.pop();
            else if(op.equals("sqrt")) val = Math.sqrt(val);
            vals.push(val);
        }
        else
            vals.push(Double.parseDouble(in));
    }
}
