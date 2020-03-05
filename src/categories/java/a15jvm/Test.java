package categories.java.a15jvm;

public class Test {
    public static void main(String[] args) {
        System.out.println(inc());
    }

    public static int inc(){
        int x;
        try{
            x = 1;
            throw new Exception();
            //return x;
        } catch(Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
