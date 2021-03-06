import java.util.Scanner;

public class WeightQuickUnion {
    private int[] parent; // parent[i] = parent of i
    private int[] size; // size[i] = number of elements in subtree rooted at i
    private int count; // number of components

    public WeightQuickUnion(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // return the number of sets
    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        WeightQuickUnion weightQuickUnion = new WeightQuickUnion(n);
        while (in.hasNextInt()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (weightQuickUnion.find(p) == weightQuickUnion.find(q)) continue;
            weightQuickUnion.union(p, q);
            System.out.println(p + " " + q);          // and print connection
        }
        System.out.println(weightQuickUnion.count() + " components");
    }
}
