package Binary;
/**
 * Author: Alec Fledderjohann
 * Date: 10/08/2025
 * Purpose: The purpose of the DataSet class is to read a file and create and split the data into arrays of
 * x and y values.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class DataSet {

    // add fields here
    ArrayList<DataRow> rows;
    int independentVariables= 1;

    /**
     * @param filename the name of the file to read the data set from
     */
    public DataSet(String filename) throws FileNotFoundException // added to handle
    {
        rows = new ArrayList<>();
        Scanner s = new Scanner(new File(filename));
        s.nextLine();
        while(s.hasNextLine())
        {
            String line = s.nextLine();
            String[] parts = line.split(",");
            double y = Double.parseDouble(parts[0]);
            double[] x = new double[parts.length-1];
            for(int i = 1; i < parts.length; ++i)
            {
                x[i-1] = Double.parseDouble(parts[i]);
            }
            rows.add(new DataRow(y, x));
        }

    }

    /**
     * @return the list of rows
     */
    public ArrayList<DataRow> getRows()
    {
        // FIXME: return the right thing here
        return rows;
    }

    /**
     * @return the number of independent variables in each row of the data set
     */
    public int getNumIndependentVariables()
    {
        if (rows == null|| rows.isEmpty())
        {
            return 0;
        }
        // FIXME: return the right thing here
        return rows.get(0).getIndependentVariables().length;
    }
}
