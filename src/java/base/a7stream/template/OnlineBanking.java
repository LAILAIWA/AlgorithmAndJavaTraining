package java.base.a7stream.template;

/**
 * 在线银行：用户输入账户，应用从数据库获取用户详细信息，最终完成一些让用户满意的操作，不同分行的满意方式会不同。
 */
abstract class OnlineBanking {
    /**
     * 获取客户提供的ID，然后使客户满意
     * @param id 账户
     */
    public void processCustomer(int id){
        Customer c = DataBase.getCustomerWithId(id);
        makeCustomerHappy(c);
    }


    abstract void makeCustomerHappy(Customer c);

}
