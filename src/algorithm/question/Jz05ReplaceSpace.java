package algorithm.question;

public class Jz05ReplaceSpace {
    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= s 的长度 <= 10000
     */
    public static String replaceSpace(String s) {
        //return s.replace(" ", "%20");
        StringBuilder res = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c == ' ') res.append("%20");
            else res.append(c);
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }
}
