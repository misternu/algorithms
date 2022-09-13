import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF wqu;
    private int width;
    private int origin;
    private int destination;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[n][n];
        wqu = new WeightedQuickUnionUF(n*n+2);
        origin = n*n;
        destination = n*n+1;
        width = n;
    }

    // // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > width || col > width) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) { return; }
        grid[row-1][col-1] = true;
        openSites++;
        int index = getIndex(row, col);
        // if row == 1, union with origin
        if (row == 1) { wqu.union(index, origin); }
        // if left union left
        if (col > 1) {
            if (isOpen(row, col-1)) { wqu.union(index, index-1); }
        }
        // if right union right
        if (col < width) {
            if (isOpen(row, col+1)) { wqu.union(index, index+1); }
        }
        // if down union down
        if (row < width) {
            if (isOpen(row+1, col)) { wqu.union(index, index+width); }
        }
        // if up union up
        if (row > 1) {
            if (isOpen(row-1, col)) { wqu.union(index, index-width); }
        }
        // if row == width union with destination
        if (row == width) { wqu.union(index, destination); }
    }

    // // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > width || col > width) {
            throw new IllegalArgumentException();
        }
        return grid[row-1][col-1];
    }

    // // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > width || col > width) {
            throw new IllegalArgumentException();
        }
        int index = getIndex(row, col);
        return wqu.find(index) == wqu.find(origin);
    }

    // // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // // does the system percolate?
    public boolean percolates() {
        return wqu.find(origin) == wqu.find(destination);
    }

    // // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        System.out.println(perc.isFull(1, 3));
        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(2, 4);
        perc.open(3, 4);
        perc.open(3, 5);
        perc.open(4, 5);
        // // is 4, 5 full?
        System.out.println(perc.isFull(4, 5));
        perc.open(5, 1);
        // // is 5, 1 full?
        System.out.println(perc.isFull(5, 1));
        // does it percolate?
        System.out.println(perc.percolates());
        perc.open(5, 5);
        // does it percolate?
        System.out.println(perc.percolates());
    }

    private int getIndex(int row, int col) {
        return (width * (row-1)) + col - 1;
    }
}
