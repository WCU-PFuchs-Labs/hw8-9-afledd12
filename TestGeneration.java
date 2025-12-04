import Binary.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class TestGeneration
{

    public static void main(String[] args) throws FileNotFoundException
    {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter data file name: ");
        String fileName = in.nextLine();
        Generation gen = new Generation(500, 3, fileName);
        gen.evalAll();
        gen.printBestTree();
        gen.printBestFitness();
        ArrayList<GPTree> topTen = gen.getTopTen();
        System.out.print("\nTop Ten Fitness Values:");

        for (int i = 0; i < topTen.size(); i++)
            {
                double f = topTen.get(i).getFitness();
                System.out.printf("%.2f", f);
                if (i < topTen.size() - 1)
                    {
                        System.out.print(", ");
                    }
            }
    }

}
