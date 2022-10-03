import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private byte[] status;
    private WeightedQuickUnionUF wqu;
    private int width;
    private int openSites;
    private boolean percolated;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) { throw new IllegalArgumentException(); }
        grid = new boolean[n][n];
        status = new byte[n*n];
        wqu = new WeightedQuickUnionUF(n*n);
        width = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > width || col > width) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) { return; }
        grid[row-1][col-1] = true;
        openSites++;
        int index = getIndex(row, col);
        if (row == 1) { unionOrigin(index); }
        if (col > 1) { unionLeft(row, col, index); }
        if (col < width) { unionRight(row, col, index); }
        if (row < width) { unionDown(row, col, index); }
        if (row > 1) { unionUp(row, col, index); }
        if (row == width) { unionDestination(index); }
        int root = wqu.find(index);
        if (status[root] == 7) { percolated = true; }
    }

    private void unionOrigin(int index) {
        int root = wqu.find(index);
        status[root] = (byte) (status[root] | 6);
    }

    private void unionLeft(int row, int col, int index) {
        if (!isOpen(row, col-1)) { return; }
        // System.out.println("left");
        union(index, index-1);
    }

    private void unionRight(int row, int col, int index) {
        if (!isOpen(row, col+1)) { return; }
        // System.out.println("right");
        union(index, index+1);
    }

    private void unionDown(int row, int col, int index) {
        if (!isOpen(row+1, col)) { return; }
        // System.out.println("down");
        union(index, index+width);
    }

    private void unionUp(int row, int col, int index) {
        if (!isOpen(row-1, col)) { return; }
        // System.out.println("up");
        union(index, index-width);
    }

    private void unionDestination(int index) {
        int root = wqu.find(index);
        status[root] = (byte) (status[root] | 5);
    }

    private void union(int index, int offset) {
        int root = wqu.find(index);
        int currentStatus = status[root];
        int otherRoot = wqu.find(offset);
        byte otherStatus = status[otherRoot];
        wqu.union(index, offset);
        root = wqu.find(index);
        status[root] = (byte) ((currentStatus | 4) | otherStatus);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > width || col > width) {
            throw new IllegalArgumentException();
        }
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > width || col > width) {
            throw new IllegalArgumentException();
        }
        int index = getIndex(row, col);
        int rootIndex = wqu.find(index);
        byte b = status[rootIndex];
        return ((b >> 2) & 1) == 1 && ((b >> 1) & 1) == 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolated;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        perc.open(1, 3);
        System.out.println(perc.isFull(1, 3));
        perc.open(2, 3);
        System.out.println(perc.isFull(2, 3));
        perc.open(2, 4);
        System.out.println(perc.isFull(2, 4));
        perc.open(3, 4);
        System.out.println(perc.isFull(3, 4));
        perc.open(3, 5);
        System.out.println(perc.isFull(3, 5));
        perc.open(4, 5);
        System.out.println(perc.isFull(4, 5));
        perc.open(5, 5);
        System.out.println(perc.isFull(5, 5));
        System.out.println(perc.percolates());
        perc.open(5, 1);
        System.out.println(!perc.isFull(5, 1));
    }

    private int getIndex(int row, int col) {
        return (width * (row-1)) + col - 1;
    }
}
