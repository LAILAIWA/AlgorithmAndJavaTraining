package sort;

import java.util.Random;

/**
 * 随机数工具
 */
public class RandomUtil {
    public static Random random = new Random();

    public static Integer[] getRandomIndex(Integer number) {

        Integer[] indexs = new Integer[number];
        int n = 0;
        while (n < number) {
            indexs[n] = random.nextInt(number);
            /*
             * 排除重复生成随机数
             */
            if (n >= 1) {
                int i = 0;
                while (i < n) {
                    if (indexs[i] == indexs[n]) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i == n) {
                    n++;
                }
            } else {
                n++;
            }
        }
        return indexs;
    }
}
