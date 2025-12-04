package Binary;
/**
 * Minus class by Alec Fledderjohann
 */

import java.lang.Math;

public class Minus extends Binop {
   /**
    * @param left the left value
    * @param right the right value
    * @return the result of adding
    * left to right
    */
   public double eval(double left, double right)
   {

       double diff = left - right;
       return (double) Math.round(diff * 100) / 100;
   }
   public String toString()
   {
      return "-";
   }
}
