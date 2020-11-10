package java.base.a12ionio;

import javax.crypto.CipherInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.*;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-16
 */
public class Test {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        InputStream i;
        AudioInputStream i1;
        ByteArrayInputStream i2;
        FileInputStream i3;
        PipedInputStream i4;
        FilterInputStream i5;
        SequenceInputStream i6;
        StringBufferInputStream i7;
        ObjectInputStream i8;

        BufferedInputStream i51;
        CheckedInputStream i52;
        CipherInputStream i53;
        DigestInputStream i54;
        InflaterInputStream i55;
        LineNumberInputStream i56;
        ProgressMonitorInputStream i57;
        PushbackInputStream i58;
        DataInputStream i59;
        DeflaterInputStream i510;

        GZIPInputStream i551;
        ZipInputStream i552;
        JarInputStream j5521;


        ObjectInput i81;
        DataInput i811;
        ObjectOutput o51;
        DataOutput o511;
        RandomAccessFile raf;

        OutputStream o;
        ByteArrayOutputStream o1;
        FileOutputStream o2;
        FilterOutputStream o3;
        PipedOutputStream o4;
        ObjectOutputStream o5;

        BufferedOutputStream o31;
        CheckedOutputStream o32;
        DigestOutputStream o33;
        DeflaterOutputStream o34;
        PrintStream o35;
        DataOutputStream o36;

        GZIPOutputStream o341;
        ZipOutputStream o342;
        JarOutputStream o3421;

        Reader r;
        BufferedReader r1;
        CharArrayReader r2;
        FilterReader r3;
        InputStreamReader r4;
        PipedReader r5;
        StringReader r6;

        LineNumberReader r11;
        PushbackReader r31;
        FileReader r41;

        Writer w;
        BufferedWriter w1;
        CharArrayWriter w2;
        FilterWriter w3;
        OutputStreamWriter w4;
        PipedWriter w5;
        PrintWriter w6;
        StringWriter w7;

        FileWriter w41;

//        try(DataInputStream dis = new DataInputStream(new PushbackInputStream(new BufferedInputStream(new FileInputStream("/test.txt"))))){
//
//        }catch (IOException ex){
//
//        }
//        Charset charset = Charset.forName("UTF-8");
//        String str = "Hello世界";
//        ByteBuffer buffer = charset.encode(str);
//        byte[] bytes = buffer.java.base.a3array();
//
//        ByteBuffer buffer1 = ByteBuffer.wrap(bytes);
//        CharBuffer charBuffer = charset.decode(buffer1);
//        String str1 = charBuffer.toString();
//        System.out.println(str1);
//
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.dat"))){
//            Employee employee = new Employee("张三",3000,1995,8,1);
//            Manager manager1 = new Manager("李四",8000,1986,12,15);
//            Manager manager2 = new Manager("王五",7000,1992,3,4);
//            manager1.setEmployee(employee);
//            manager2.setEmployee(employee);
//            oos.writeObject(employee);
//            oos.writeObject(manager1);
//            oos.writeObject(manager2);
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("test.dat"));
//            Employee e1 = (Employee)in.readObject();
//            Employee e2 = (Employee)in.readObject();
//            Employee e3 = (Employee)in.readObject();
//            System.out.println(e1.toString());
//            System.out.println(e2.toString());
//            System.out.println(e3.toString());
//        }catch (IOException | ClassNotFoundException ex){
//            ex.printStackTrace();
//        }

        Path path = Paths.get("E:\\BlogUpload\\pic\\upload\\cover","1");
        System.out.println("文件名：" + path.getFileName());
        System.out.println("名称元素的数量：" + path.getNameCount());
        System.out.println("父路径：" + path.getParent());
        System.out.println("根路径：" + path.getRoot());
        System.out.println("是否是绝对路径：" + path.isAbsolute());
        //startsWith()方法的参数既可以是字符串也可以是Path对象
        System.out.println("是否是以为给定的路径D:开始：" + path.startsWith("D:\\") );
        System.out.println("该路径的字符串形式：" + path.toString());

        try{
            //.表示的是当前目录
            Path currentDir = Paths.get(".");
            System.out.println(currentDir.toAbsolutePath());
            Path currentDir2 = Paths.get(".\\test.dat");
            System.out.println("原始路径格式："+currentDir2.toAbsolutePath());
            System.out.println("执行normalize（）方法之后："+currentDir2.toAbsolutePath().normalize());
            System.out.println("执行toRealPath()方法之后："+currentDir2.toRealPath());

            //..表示父目录或者说是上一级目录：
            Path currentDir3 = Paths.get("..");
            System.out.println("原始路径格式："+currentDir3.toAbsolutePath());
            System.out.println("执行normalize（）方法之后："+currentDir3.toAbsolutePath().normalize());
            System.out.println("执行toRealPath()方法之后："+currentDir3.toRealPath());

            boolean pathExits = Files.exists(path,new LinkOption[]{LinkOption.NOFOLLOW_LINKS});//检测时不包含符号链接文件
            System.out.println("pathExits："+pathExits);
            Path target = Paths.get("D:\\a.txt");
            Path newDir = Files.createDirectories(path);//创建文件夹,会创建所有不存在目录，createDirectory() 则在父目录不存在时抛出FileAlreadyExistsException
            Files.delete(target);//删除文件或目录
            Files.copy(target,newDir);// 把一个文件从一个地址复制到另一个位置，增加参数StandardCopyOption.REPLACE_EXISTING可以覆盖已存在的目标文件
            if(!Files.exists(target))
                Files.createFile(target);//创建文件
            //获取文件相关属性
            System.out.println(Files.getLastModifiedTime(path));
            System.out.println(Files.size(path));
            System.out.println(Files.isSymbolicLink(path));
            System.out.println(Files.isDirectory(path));
            System.out.println(Files.readAttributes(path, "*"));

            //遍历单个文件夹， Files.walkFileTree()可以递归遍历目录
            Path dir = Paths.get("D:\\Java");
            try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
                for(Path e : stream){
                    System.out.println(e.getFileName());
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
