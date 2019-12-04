package categories.java.a7stream.strategy;

public class Test {
    public static void main(String[] args) {
        //多个使用策略对象的客户
        Validator numberValidator = new Validator(new IsNumeric());
        boolean b1 = numberValidator.validate("aaaa");
        System.out.println("b1 : " + b1);//false
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println("b2 : " + b2);//true

        //通过Lambda表达式
        Validator numberValidatorL = new Validator((String s) -> s.matches("\\d+"));
        boolean b3 = numberValidatorL.validate("aaaa");
        System.out.println("b3 : " + b3);//false
        Validator lowerCaseValidatorL = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b4 = lowerCaseValidatorL.validate("bbbb");
        System.out.println("b4 : " + b4);//true
    }
}
