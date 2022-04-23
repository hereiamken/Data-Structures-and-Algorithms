import java.util.Scanner;

public class QuickUnion {
    private int[] id; // id[i] = component identifier of i
    private int count; // number of components

    // set id ò each to itself (N array access)
    public QuickUnion(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    // returns the number of sets.
    public int count() {
        return count;
    }

    // chase parent pointer until reach root (depth of i array accesses)
    // will be replaced by function validate() below
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    // returns the canonical element of the set containing element
    public int find(int p) {
        validate(p);
        return id[p];
    }

    // check if p & q are same root (depth of p & q array accesses)
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // change root of p to point to root of q
    public void union(int p, int q) {
        validate(p);
        validate(q);

        // needed for correctness to reduce the number of array accesses
        int pID = id[p];
        int qID = id[q];

        // if p & q are in the same component
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
            ;
        }
        count--;
    }

    /**
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        QuickUnion qu = new QuickUnion(n);
        while (in.hasNextInt()) {
            int p = in.nextInt();
            int q = in.nextInt(); // Read pair to connect
            if (qu.find(p) == qu.find(q)) {
                continue;
            } // Ignore if connected
            qu.union(p, q);                   // Combine components
            System.out.println(p + " " + q);          // and print connection
        }
        System.out.println(qu.count() + " components");
    }
}
