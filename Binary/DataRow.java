package Binary;

/**
 * Author:Alec Fledderjohann
 * Date: 10/08/2025
 * Purpose: A template class that represents a row of data
 */


public class DataRow {

    private double y;
    private double[] x;

    /**
     * @param y the dependent variable
     * @param x the array of independent variables
     */
    public DataRow(double y, double[] x)
    {
        this.y = y;
        this.x = x;
    }

    /**
     * @return the dependent variable
     */
    public double getDependentVariable() {
        return y;
    }

    /**
     * @return the array of independent variables
     */
    public double[] getIndependentVariables() {
        // FIXME: return the right thing here
        return x;
    }
}
