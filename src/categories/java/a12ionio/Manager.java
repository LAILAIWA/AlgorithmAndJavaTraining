package categories.java.a12ionio;

import java.io.Serializable;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-22
 */
public class Manager extends Employee implements Serializable {
    private Employee employee;

    public Manager() {
    }

    public Manager(String name, int pay, int year, int month, int day) {
        super(name, pay, year, month, day);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
