package categories.java.a7stream.template;

import java.util.function.Consumer;

public class OnlineBankingNotAbs {

    /**
     * 重载processCustomer，引入函数式接口参数
     * @param id 账户
     * @param makeCustomerHappy 不同实现
     */
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = DataBase.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    public static void main(String[] args) {
        new OnlineBankingNotAbs().processCustomer(1,(Customer c) -> System.out.println("Hello " + c.getName()));
    }
}
