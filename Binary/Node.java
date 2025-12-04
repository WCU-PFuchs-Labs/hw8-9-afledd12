package Binary;
import java.util.Random;
/**
 * Code Template Author: David G. Cooper
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
public class Node implements Cloneable
{
   private Node left;
   private Node right;
   private Op operation;
   protected int depth;

   public Node(Unop operation)
   {
      this.operation = operation;
      this.depth = 0;
   }

   public Node(Binop operation, Node left, Node right)
   {
      this.left = left;
      this.right = right;
      this.operation = operation;
      this.depth = 0;
   }

    public double eval(double[] values)
    {
      if (operation instanceof Unop)
      {
         return ((Unop) operation).eval(values);
      }
      else if (operation instanceof Binop)
      {
         return ((Binop) operation).eval(left.eval(values), right.eval(values));
      }
      else
      {
         System.err.println("Error operation is not a Unop or a Binop!");
         return 0.0;
      }
   }

    public Object clone()
    {
        Object o = null;
        try {
            o =  super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println("Op can't clone.");
        }
        Node b = (Node) o;
        if(left != null)
        {
            assert b != null;
            b.left = (Node) left.clone();
        }
        if(right != null)
        {
            b.right = (Node) right.clone();
        }
        if(operation != null)
        {
            b.operation = (Op) operation.clone();
        }
        return o;
    }

    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand)
    {
        // use the [above algorithm](#node)
        int ops = nf.getNumOps();
        int var = nf.getNumIndepVars();
        int range = ops + var;
        int lefts = rand.nextInt(range);
        int rights = rand.nextInt(range);

        if (maxDepth == depth)
        {
            left = nf.getTerminal(rand);
            right = nf.getTerminal(rand);
            return;
        }

        if (lefts > ops)
        {
            left = nf.getOperator(rand);
            left.depth = depth + 1;
            left.addRandomKids(nf, maxDepth, rand  );
        }
        else
        {
            left = nf.getTerminal(rand);
        }

        if (rights > ops)
        {
            right = nf.getOperator(rand);
            right.depth = depth + 1;
            right.addRandomKids(nf, maxDepth, rand  );
        }
        else
        {
            right = nf.getTerminal(rand);
        }
    }
    /**
     * collect using preorder traversal.
     */
    public void traverse(Collector c)
    {
        c.collect(this);
        if (left != null)
        {
            left.traverse(c);
        }
        if (right != null)
        {
            right.traverse(c);
        }
    }

    /**
     * swap this left child node with trunk left child node
     */
    public void swapLeft(Node trunk)
    {
        trunk.left = this.left;

    }

    /**
     * swap this right child node with trunk right child node
     */
    public void swapRight(Node trunk)
    {
        trunk.right = this.right;
    }

    /**
     * return true if operation is s Unop
     */
    public boolean isLeaf()
    {
        // return true if operation is a Unop
        if (operation instanceof Unop)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Override
   public String toString()
   {
      if (operation instanceof Binop)
      {
          //changed right.eval() to right.toString() so that it will show both arithmetic operations happening
          // I made this change so that it follows the read me output to hopefully pass the tests
         return "(" + left.toString() + " " + operation.toString() + " " + right.toString() + ")";
      }
      if (operation instanceof Unop)
      {
          return operation.toString();
      }
   
      return "";
   }
}