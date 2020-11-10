package java.base.a1enum;

enum Operation {
    PLUS {
        double val(double x, double y) {
            return x + y;
        }
    }, MINUS {
        double val(double x, double y) {
            return x - y;
        }
    }, TIMES {
        double val(double x, double y) {
            return x * y;
        }
    }, DIVIDED_BY {
        double val(double x, double y) {
            return x / y;
        }
    };

    abstract double val(double x, double y);


    public static void main(String[] args) {
        double x = 0.5;
        double y = 0.2;
        for (Operation op : Operation.values())
            System.out.println(x + " " + op + " " + y + " = " + op.val(x, y));
    }
}
