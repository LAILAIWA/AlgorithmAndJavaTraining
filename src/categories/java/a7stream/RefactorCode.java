package categories.java.a7stream;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RefactorCode {
    public static void main(String[] args) throws IOException{
        Logger logger = Logger.getLogger("RefactorCode");
        if(logger.isLoggable(Level.FINER)){
            logger.finer("Problem: " + generateDiagnostic());
        }

        //优化
        logger.log(Level.FINER, "Problem: " + generateDiagnostic());

        //有条件的延迟执行
        logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());

        //环绕执行
        //通过函数式接口BufferedReaderProcesser，可以传递各种Lambda表达式对BufferedReader对象进行处理
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        String twoLine = processFile((BufferedReader b) -> b.readLine() + b.readLine());
    }

    private static String generateDiagnostic(){
        return "a";
    }

    public static String processFile(BufferedReaderProcesser p)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("d:/data.txt"))){
            return p.process(br);
        }
    }

    public interface BufferedReaderProcesser{
        String process(BufferedReader b)throws IOException;
    }
}
