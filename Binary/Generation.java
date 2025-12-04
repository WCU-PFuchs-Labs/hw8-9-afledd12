package Binary;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generation
{
    private GPTree[] trees;
    private DataSet data;
    private int maxDepth;
    private Random rand;
    private NodeFactory nf;
    private Binop[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
    private int numIndepVars;

    public Generation(int size, int maxDepth, String filename) throws FileNotFoundException
    {
        this.maxDepth = maxDepth;
        this.data = new DataSet(filename);
        this.rand = new Random();
        this.numIndepVars = data.getNumIndependentVariables();
        this.nf = new NodeFactory(ops, numIndepVars);
        this.trees = new GPTree[size];

        for (int i = 0; i < size; ++i)
        {
            trees[i] = new GPTree(nf, maxDepth, rand);
        }
    }

    public void evalAll()
    {
        for (int i = 0; i < trees.length; i++)
        {
            trees[i].evalFitness(data);
        }
        Arrays.sort(trees);
    }

    public ArrayList<GPTree> getTopTen()
    {
        ArrayList<GPTree> topTen = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            topTen.add(trees[i]);
        }
        return topTen;
    }

    public void printBestFitness()
    {
        //added to make sure trees are not null
        if (trees == null || trees.length == 0) {
            System.out.println("No trees in generation");
        }
        else
        {
            System.out.println("Best Fitness: " + trees[0].getFitness());
        }
    }

    public void printBestTree() {
        if (trees == null || trees.length == 0)
        {
            System.out.println("No trees in generation");
        }
        else
        {
            System.out.println("Best Tree: " + trees[0]);
        }
    }

    public void evolve() {
        GPTree[] newTrees = new GPTree[trees.length];
        int n = trees.length;

        for (int i = 0; i < n; i = i +2)
        {
            int first = rand.nextInt(n);
            int second = rand.nextInt(n);

            GPTree tree1 = trees[first];
            GPTree tree2 = trees[second];
            GPTree child1 = (GPTree) tree1.clone();
            GPTree child2 = (GPTree) tree2.clone();

            child1.crossover(child2, rand);
            newTrees[i] = child1;
            if (i + 1 < n)
            {
                newTrees[i + 1] = child2;
            }
        }
            trees = newTrees;
    }
}
