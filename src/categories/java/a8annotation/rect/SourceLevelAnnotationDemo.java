package categories.java.a8annotation.rect;


import categories.java.a8annotation.ToStrings;

public class SourceLevelAnnotationDemo
{
   public static void main(String[] args)
   {
      Rectangle rect = new Rectangle(new Point(10, 10), 20, 30);
      System.out.println(ToStrings.toString(rect));
   }
}
