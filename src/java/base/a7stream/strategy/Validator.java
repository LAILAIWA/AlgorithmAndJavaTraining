package java.base.a7stream.strategy;

/**
 * 策略接口实现类-校验器
 */
public class Validator {
    private final ValidationStratery stratery;

    public Validator(ValidationStratery v){
        this.stratery = v;
    }

    public boolean validate(String s){
        return stratery.execute(s);
    }
}
