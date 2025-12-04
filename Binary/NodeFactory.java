package Binary;

import java.util.Random;

public class NodeFactory
{
    private int numIndepVars;
    private Binop[] currentOps;
    public NodeFactory(Binop[] b, int numVars)
    {
        currentOps = b;
        this.numIndepVars = numVars;
    }
    public Node getOperator(Random rand)
    {
        rand = new Random();
        int index = rand.nextInt(currentOps.length);
         return new Node((Binop) currentOps[index].clone(),null,null);
    }
    public int getNumOps()
    {
        // add
        return currentOps.length;
    }
    public Node getTerminal(Random rand)
    {
        // add
        if (numIndepVars <= 0)
        {
            return new Node(new Const(rand.nextDouble()));
        }

        int value = rand.nextInt(numIndepVars + 1);

        if (value == numIndepVars)
        {
            return new Node(new Const(rand.nextDouble()));
        }
        else
        {
            return new Node(new Variable(value));
        }
    }
    public int getNumIndepVars() {
        // add
        return numIndepVars;
    }
}
