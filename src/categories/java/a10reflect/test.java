package categories.java.a10reflect;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2018-12-17
 */
public class test {
    public static void main(String[] args){
        String name;
        if(args.length > 0) name = args[0];
        else {
            try(Scanner in = new Scanner(System.in)){
                System.out.println("请输入class name: ");
                name = in.next();
            }
        }

        try{
            Class<?> cl = Class.forName(name);//Class类的forName()静态方法动态的获得Class对象。

            System.out.println("创建实例: ");
            String str1 = (String) cl.getDeclaredConstructor().newInstance();//通过调用类声明的构造器来创建实例
            System.out.println("str1: " + str1);

            Constructor constructor = cl.getConstructor(String.class);//获取String类的构造器
            //根据构造器创建实例
            String str2 = (String) constructor.newInstance("bbb");//调用带字符串参数的构造器
            System.out.println("str2: " + str2);

            printClass(cl);
            System.out.println("方法: ");
            for(Method method : cl.getDeclaredMethods())//获得类的所有方法
                printMethod(method);
        }catch (ClassNotFoundException | NoSuchMethodException |
                InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException ex){
            ex.printStackTrace();
        }
    }

    //打印类信息
    public static void printClass(Class<?> cl){
        System.out.println("Class<?>: " + cl);

        System.out.println("所有已声明的类成员: ");
        for(Field field : cl.getDeclaredFields())//获得类的所有已声明的类成员
            System.out.println("field[" + field.getName() + "] Type[" + field.getType() + "]");

        System.out.println("所有公有的（public）类成员: ");
        for(Field field : cl.getFields())//获得类的所有公有的（public）类成员
            System.out.println("field[" + field.getName() + "] Type[" + field.getType() + "]");

        System.out.println("类结构: ");
        printTypes(cl.getTypeParameters(),"<",",",">",true);//若为泛型类型则获取泛型类型变量，否则返回长度0数组
        Type sc = cl.getGenericSuperclass();//获得被声明为这一类型的超类的泛型类型，若是Object，或不是类返回null
        if(sc != null){
            System.out.print(" extends ");
            printType(sc,false);
        }
        printTypes(cl.getGenericInterfaces(),"<",",",">",false);//获得被声明为这一类型的接口的泛型类型，若没有实现接口，返回长度0数组
        System.out.println();
    }

    //打印方法信息
    public static void printMethod(Method method){
        String name = method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        printTypes(method.getTypeParameters(),"<",",",">",true);//若方法为泛型方法，则获得泛型变量，否则返回长度0数组

        printType(method.getGenericReturnType(),false);//获得此方法被声明的泛型返回类型
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(method.getGenericParameterTypes(),"<",",",">",true);//获得此方法被声明的泛型参数类型，若无参数，则返回长度0数组
        System.out.print(")");
        System.out.println("");
    }

    //打印泛型类型信息
    public static void printTypes(Type[] types,String pre,String sep,String suf,boolean isDefinition){
        if(pre.equals(" extends ") && Arrays.equals(types,new Type[]{Object.class})) return;
        if(types.length > 0) System.out.print(pre);
        for(int i = 0;i < types.length;i++){
            if(i > 0) System.out.print(sep);
            printType(types[i],isDefinition);
        }
        if(types.length > 0) System.out.print(suf);
    }

    //打印类型信息
    public static void printType(Type type,boolean ifDefinition){
        if(type instanceof Class){
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());//获得类型变量名
        }else if(type instanceof TypeVariable){
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if(ifDefinition)
                printTypes(t.getBounds()," extends "," & ","",false);//获得类型变量的子类限定
        }else if(type instanceof WildcardType){
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds()," extends "," & ","",false);//获得这个类型变量的子类extends限定，否则0数组
            printTypes(t.getLowerBounds()," super "," & ","",false);//获得这个类型变量的超类super限定，否则0数组
        }else if(type instanceof ParameterizedType){
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();//若是内部类型，返回其外部类型，若是顶级类型，返回null
            if(owner != null){
                printType(owner,false);
                System.out.print(".");
            }
            printType(t.getRawType(),false);//获得这个参数化类型的原始类型
            printTypes(t.getActualTypeArguments(),"<",",",">",false);//获得这个参数化类型声明时所使用的类型参数
        }else if(type instanceof GenericArrayType){
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(),ifDefinition);//获得声明该数组类型的泛型组件类型
            System.out.print("[]");
        }
    }


    //求集合最小
    public static Comparable min(Comparable[] a){
        if(a.length == 0)
            throw new IndexOutOfBoundsException("list`s size cant be zero");
        Comparable temp = a[0];
        for(Comparable comparable : a){
            if(temp.compareTo(comparable) > 0)
                temp = comparable;
        }
        return temp;
    }
}
