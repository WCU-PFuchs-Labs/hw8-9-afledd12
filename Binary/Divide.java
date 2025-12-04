package Binary;
import java.lang.Math;
/**
 * Divide class by Alec Fledderjohann
 */

public class Divide extends Binop {
   /**
    * @param left the left value
    * @param right the right value
    * @return the result of adding
    * left to right
    */
   public double eval(double left, double right)
   {
       if (right < 0.0001)
       {
           return 1.0;
       }
       double quotient = left / right;
      return (double) Math.round(quotient * 100) / 100;
   }
   public String toString()
   {
      return "/";
   }
}
