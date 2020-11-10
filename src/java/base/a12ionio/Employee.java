package java.base.a12ionio;

import java.io.Serializable;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-22
 */
public class Employee implements Serializable {
    private String name;
    private int pay;
    private int year;
    private int month;
    private int day;

    public Employee() {
    }

    public Employee(String name, int pay, int year, int month, int day) {
        this.name = name;
        this.pay = pay;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "name[" + name + "] pay[" + pay + "] year[" + year + "] month[" + month + "] day[" + day + "]";
    }
}
