package java.base.a7stream;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 单词切分迭代器
 */
public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));//处理当前字符串
        return currentChar < string.length();//若还有字符串要处理，返回true
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if(currentSize < 10){//此时切分的已足够小，返回null进行顺序处理
            return null;
        }
        //开始试探拆分的位置为当前字符串中间
        for(int splitPos = currentSize / 2 + currentChar; splitPos < string.length() ; splitPos++){
            //让拆分位置前进到下一个空格
            if(Character.isWhitespace(string.charAt(splitPos))){
                //创建一个新的WordCounterSpliterator来解析开始到拆分位置的部分
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar,splitPos));
                //再把此Spliterator的起始位置设置为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    public WordCounterSpliterator(String string) {
        this.string = string;
    }
}
