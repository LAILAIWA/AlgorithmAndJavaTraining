package categories.java.a7optional;

import categories.java.a7defaultmethod.diamond.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
//        getCarInsuranceName(new Person());

        //声明一个空的Optional对象
        Optional<Car> optCar1 = Optional.empty();

        //通过一个非空的值来创建Optional对象
        Optional<Car> optCar2 = Optional.of(new Car());

        //创建一个允许null值的Optional对象
        Optional<Car> optCar3 = Optional.ofNullable(null);

        Insurance insurance = optCar2.get().getInsurance().get();
        //使用map从Optional对象中提取和转换值
//        String name = null;
//        if(insurance != null){
//            name = insurance.getName();
//        }

        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);

        //检查保险公司名称是否为XX，首先要判断引用是否为null
        if(insurance != null && "CambridgeInsurance".equals(insurance.getName())){
            System.out.println("OK");
        }
        //filter方法改写
        optInsurance.filter(insurance1 -> "CambridgeInsurance".equals(insurance.getName())).ifPresent(x -> System.out.println("OK"));

        //用Optional封装可能为null的值
        Map<String,Object> map = new HashMap<>();
        //当映射不包含键对应的值会返回null
        Object value = map.get("key");
        //Optional改写
        Optional<Object> optValue = Optional.ofNullable(map.get("key"));

        //异常与Optional的对比
    }

//    public static String getCarInsuranceName(Person person){//防御式检查减少NullPointerException
//        if(person != null){
//            Car car = person.getCar();
//            if(car != null){
//                Insurance insurance = car.getInsurance();
//                if(insurance != null){
//                    return insurance.getName();//业务上设定公司必然有名字，所以可以避免了这一层检查，但不会直接反映在建模中
//                }
//            }
//        }
//        return "Unknown";
//    }

    public Insurance findCheapestInsurance(Person person, Car car){
        Insurance cheapestCompany = null;
        //不同的保险公司提供的查询服务
        //对比所有数据
        return cheapestCompany;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car){
//        if(person.isPresent() && car.isPresent()){
//            return Optional.of(findCheapestInsurance(person.get(),car.get()))
//        }else {
//            return Optional.empty();
//        }
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p,c)));
    }
}
