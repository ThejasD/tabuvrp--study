package tabuvrp.vrp;

import tabuvrp.core.Graph;
import tabuvrp.core.Node;
import tabuvrp.core.Edge;
import java.util.Arrays;
import java.util.Comparator;


public class VRP implements Graph {

    
    /**************************************************************************/
    class Util implements Comparator<Integer> {

        final Edge[][] edges;
        final int i;

        Util(Edge[][] edges, int i) {
            this.edges = edges;
            this.i = i;
        }

        public int compare(Integer j1, Integer j2) {
            return Double.valueOf(edges[i][j1].getCost()).compareTo(edges[i][j2].getCost());
        }
    }
    /**************************************************************************/

    
    protected Node[] nodes;
    protected Edge[][] edges;
    protected Integer[][] neighbourhood;
    protected final int nodeCount;
    protected double mTilde;

    public VRP(Integer[] nodeValues, double[] x, double[] y) {
        if (   nodeValues.length != x.length
            || x.length != y.length) {
            throw new IllegalArgumentException("size mismatch in node definition");
        }
        int n = nodeValues.length;

        this.nodes = new Node[n];

        this.edges = new Edge[n][n];
        neighbourhood = new Integer[n][n - 1];
        mTilde = 0;

        for (int i = 0; i < n; ++i) {
            nodes[i] = new Node(x[i], y[i], nodeValues[i]);
            if (i > 0) {
                mTilde += nodeValues[i];
            }
        }
        mTilde /= n;

        for (int i = 0; i < n; ++i) {
            Comparator<Integer> comp = new Util(edges, i);
            int j;
            int k = 0;
            for (j = 0; j < n; ++j) {
                if (i == j) {
                    this.edges[i][j] = new Edge(0);
                } else {
                    this.edges[i][j] = new Edge(Node.distance(nodes[i], nodes[j]));
                    neighbourhood[i][k] = j;
                    k += 1;
                }
            }
            Arrays.sort(neighbourhood[i], comp);

        }
        nodeCount = n;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public double getMTilde() {
        return mTilde;
    }

    public Node getNode(int n) {
        return nodes[n];
    }

    public Edge getEdge(int i, int j) {
        return edges[i][j];
    }

    public Integer[][] getNeighbourhood() {
        Integer[][] tmp = new Integer[nodeCount][nodeCount - 1];
        for (int i = 0; i < neighbourhood.length; ++i) {
            tmp[i] = Arrays.copyOf(neighbourhood[i], neighbourhood[i].length);
        }
        return tmp;
    }
}