package categories.java.a7stream;

public class WordCounter {
    private final int counter;//当前统计数目
    private final boolean lastSpace;//上个字符是否空格

    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){//如果此字符是空格
            //上个字符是空格，返回当前计数器；
            // 上个字符不是空格，返回新的计数器，并标记lastSpace
            return lastSpace ? this : new WordCounter(counter, true);
        }else {//此字符不是空格
            //上个字符是空格，返回新计数器，统计+1，重置lastSpace；
            // 上个字符不是空格，返回当前计数器
            return lastSpace ? new WordCounter(counter+1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter,wordCounter.lastSpace);//lastSpace不重要
    }

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isLastSpace() {
        return lastSpace;
    }
}
