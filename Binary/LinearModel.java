package Binary;

/**
 * Author: Alec Fledderjohann
 * Date: 10/08/2025
 * Purpose: The purpose of the LinearModel class is to implement a linear regression model. It trains on a set of data
 * and predicts the dependent variable based on the coefficients and intercept.
 */

import java.util.ArrayList;

public class LinearModel extends Model
{
    private double[] coeff;
    private double intercept;
    private double changeRate;

    /**
     * 
     * @param rate - the update/learning rate
     * @param training - the training data
     */
    public LinearModel(double rate, DataSet training)
    {
        super(training);
        // initialize changeRate based on parameters
        changeRate = rate;
        // call initCoefficients to initialize coeff and intercept
        initCoefficients();
        // call updateCoeff() 1000 times
        for(int i = 0; i < 1000; ++i)
        {
            updateCoeff();

            // this is what I did to debug and check the error for every 100 iterations.
//            if(i % 100 == 0)
//            {
//                System.out.println("iteration " + i + " error: " + sumSquaredError());
//            }
        }
        // when testing this, you may want to print some
        // debugging output so that you can track the progress
        // of the regression.

    }

    
    /**
     * helper function to initialize coeff and intercept
     */
    private void initCoefficients()
    {
        // instantiate an array of size n for coeff, where n is
        // the number of independent variables in the training data
        int n = trainingData.getNumIndependentVariables();
        // initialize each value in coeff to be a random double 
        // between -2 and 2
        coeff = new double[n];

        // initialize intercept to be a random double 
        // between -2 and 2
        for(int i = 0; i < n; ++i)
        {
            coeff[i] = Math.random() * 4 - 2;
        }
        intercept = Math.random() * 4 - 2;
    }

    /**
     * This makes a prediction based on the current 
     * coefficients and intercept
     * @param x
     * @return
     */
    public double predict(double[] x)
    {
        // compute the sum of each coefficient times 
        // the corresponding x, then add the intercept
        // and return the result.
        double sum = 0;
        if (x.length != coeff.length)
        {
            return 0;
        }
        for(int i = 0; i < coeff.length; ++i)
        {
            sum += coeff[i] * x[i];
        }

        //FIXME:
        return sum + intercept;
    }

    /**
     * helper function that does one iteration of computing 
     * the error and updating the coefficients and the intercept
     * 
     */
    private void updateCoeff() {
        // get the predictions for each row by calling predict()
        double[] pred = predict();

        // get the data rows
        ArrayList<DataRow> rows = trainingData.getRows();

        // for each coefficient 
        // compute the sum of the error times the variable
        // then multiply by 2 and divide by the number of predictions
        for(int i = 0; i < coeff.length; ++i)
        {
            double sum = 0;
            for(int j = 0; j < pred.length; ++j)
            {
                DataRow row = rows.get(j);
                double x_i = row.getIndependentVariables()[i];
                sum += (pred[j] - row.getDependentVariable()) * x_i;
            }
            coeff[i] = coeff[i] - changeRate * sum * 2.0 / pred.length;
        }

        // for the intercept 
        double sum = 0;
        for(int j = 0; j < pred.length; ++j)
        {
            DataRow row = rows.get(j);
            sum += pred[j] - row.getDependentVariable();
        }
        intercept = intercept - changeRate * sum * 2.0 / pred.length;
    }

    //toString method to print the coefficients and intercept and format them to display the output described in the
    //Readme.
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coeff.length; i++)
        {
            sb.append(String.format("%.3f*X%d", coeff[i], i));

            if (i < coeff.length - 1)
            {
                sb.append(" + ");
            }
        }
        if (intercept >= 0)
        {
            sb.append(" + ").append(String.format("%.3f", intercept));
        }
        else
        {
            sb.append(" - ").append(String.format("%.3f", Math.abs(intercept)));
        }

        return sb.toString();
    }


}
