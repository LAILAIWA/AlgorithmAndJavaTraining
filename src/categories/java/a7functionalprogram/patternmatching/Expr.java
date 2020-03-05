package categories.java.a7functionalprogram.patternmatching;

import java.util.HashMap;

public class Expr {
    private String name;
    private int id;

    @Override
    public boolean equals(Object obj) {
        Expr ex = (Expr) obj;
        return this.id == ex.getId();
    }

//    @Override
//    public int hashCode() {
//        return this.id;
//    }

    public Expr(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
