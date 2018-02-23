/**
 * Goldbach.java
 * author @ Kyaw Khant Nyar (kxk3035@rit.edu)
 * Goldbach is a parallel program that uses brute force to find Goldbach numbers
 * from 4 to given even value.
 * version @ Feb-22-2018
 */

// imports
import edu.rit.pj2.Task;
import edu.rit.pj2.Loop;
import edu.rit.pj2.vbl.IntVbl;
import java.math.BigInteger;

// Goldbach class
public class Goldbach extends Task {

    // command input
    int seed;

    // shared variables
    IntVbl counts;
    // min and max x values
    int maxX;
    int minX;

    // main method
    public void main(String[] args) throws Exception {

        if (args.length != 1) usage(0);

        try {

            seed = Integer.parseInt(args[0]);
            counts = new IntVbl.Sum(0);

            // error check
            if (seed < 5) usage(1);
            if (seed % 2 != 0) usage(1);

            // initial minX and maxX value
            maxX = 0;
            minX = seed;
            parallelFor(1, seed / 2).exec(new Loop() {

                // local count
                IntVbl localCount;

                // threads start
                public void start() {
                    localCount = threadLocal(counts);
                }


                // run
                public void run(int i) {
                    BigInteger x = BigInteger.valueOf(i);
                    BigInteger y = BigInteger.valueOf(seed - i);

                    // heart of the program
                    if (x.isProbablePrime(64) && y.isProbablePrime(64)) {
                        localCount.item++;
                        // assign min and max
                        maxX = (i > maxX) ? i : maxX;
                        minX = (i < minX) ? i : minX;
                    }
                }

            });


            // output answers
            if (counts.item == 1) {
                System.out.println("1 solution");
                // only max value is used bc there is only one value
                System.out.printf("%d = %d + %d%n", seed, maxX, seed - maxX);

            } else if (counts.item > 1) {
                System.out.printf("%d solutions%n", counts.item);
                System.out.printf("%d = %d + %d%n", seed, minX, seed - minX);
                System.out.printf("%d = %d + %d%n", seed, maxX, seed - maxX);
            } else {
                System.out.println("No solutions");
            }


        } catch (Exception ex) {
            System.err.println("Error while parsing the input: " + ex);
            usage(1);
        }

    }
    // error printout method
    private static void usage(int errno) {

        System.err.println("Usage error.");
        System.err.println("Usage: java pj2 Goldbach <n>");
        if (errno == 1) {
            System.err.println("<n> must be an even number > 4 (type int).");
        }
        System.exit(1);
    }


}
