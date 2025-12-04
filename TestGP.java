import Binary.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestGP
{

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter data file name: ");
        String fileName = in.nextLine();
        int size = 500;
        int maxDepth = 3;

        Generation gen = new Generation(size, maxDepth, fileName);

        for (int g = 1; g <= 50; g++)
        {
            System.out.println();
            System.out.println("Generation " + g + ":");
            gen.evalAll();
            gen.printBestTree();
            gen.printBestFitness();
            if (g < 50)
            {
                gen.evolve();
            }
        }
    }
}
