package Binary;

import java.lang.Math;
/**
 * Mult class by Alec Fledderjohann
 */

public class Mult extends Binop {
   /**
    * @param left the left value
    * @param right the right value
    * @return the result of adding
    * left to right
    */
   public double eval(double left, double right)
   {
      double product = left * right;
      return (double) Math.round(product * 100) / 100;
   }
   public String toString()
   {
      return "*";
   }
}
