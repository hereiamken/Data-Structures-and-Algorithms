/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
        size = n;
        bottom = size * size + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already

    /**
     * Open box (row i and col j) if not opened
     * Box Opened if value => true
     * Box closed if value => false
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        checkException(row, col);
        opened[row - 1][col - 1] = true; //open the box
        openSites++;

        //Edge Case => If any of the top row boxes are opened => Union (box, top)
        if (row == 1) {
            weightedQuickUnionUF.union(getQuickFindIndex(row, col), TOP);
        }

        // Edge Case => If any of the bot row boxes are opened => Union (box, bot)
        if (row == size) {
            weightedQuickUnionUF.union(getQuickFindIndex(row, col), bottom);
        }

        // If any of the boxes in the middle rows (except top and bottom) are opened then check for neighbouring unions
        // check the neighbour
        if (row > 1 && isOpen(row - 1, col)) {
            weightedQuickUnionUF
                    .union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
        }

        if (row < size && isOpen(row + 1, col)) {
            weightedQuickUnionUF
                    .union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            weightedQuickUnionUF
                    .union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
        }

        if (col < size && isOpen(row, col + 1)) {
            weightedQuickUnionUF
                    .union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ((row > 0 && row <= size) && (col > 0 && col <=size)) {
            return weightedQuickUnionUF.find(TOP) == weightedQuickUnionUF
                    .find(getQuickFindIndex(row, col));
        }
        else throw new IllegalArgumentException();
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.find(TOP) == weightedQuickUnionUF.find(bottom);
    }

    private void checkException(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Retrieves the index of the box from the matrix
     */
    private int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col;
    }
}
