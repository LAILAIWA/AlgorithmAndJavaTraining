package categories.java.a4error;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-16
 */
public class CustomException extends Exception {

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
