import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int i = 1;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            double chance = 1.0/i;
            if (StdRandom.bernoulli(chance)) {
                champion = word;
            }
            i = i + 1;
        }
        StdOut.println(champion);
    }
}
