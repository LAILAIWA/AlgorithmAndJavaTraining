package java.base.a7stream.template;

public class DataBase {
    public static Customer getCustomerWithId(int id){
        if(id == 1){
            return new Customer(1,"ali");
        }else if(id == 2){
            return new Customer(2,"tom");
        }else {
            return new Customer(-1,"illegal");
        }
    }
}
