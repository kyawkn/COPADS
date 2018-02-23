/**
 * Goldbach.java
 *
 *
 */

import edu.rit.pj2.Task;
import edu.rit.pj2.Loop;
import edu.rit.pj2.vbl.LongVbl;
import java.math.BigInteger;

public class Goldbach extends Task {

    // command inputs
    int seed;

    // shared answers
    int counts;
    BigInteger maxX = 0;
    BigInteger minX =-0;

    public static void main(String[] args) throws Exception {

        if (args.length != 1) usage(0);

        try {
            seed = Integer.parseInt(args[0]);

            if (seed < 5) usage(1);

              counts = new LongVbl.Sum(0);

              parallelFor(1, seed/2) .exec (new Loop () {

                int localCount;

                public void start() {
                  localCount = threadCount (counts);
                }

                public void run(Integer i) {
                  BigInteger x = BigInteger.valueOf(i);
                  BigInteger y = BigInteger.valueOf(seed - i);


                  if(x.isProbablePrime(64) && y.isProbablePrime(64)) ++ localCount.item;

                }

              });

              System.out.println(counts);


        } catch (Exception ex) {
          System.err.println("Error while parsing the input: " + ex);
          System.exit(1);
        }

    }

    private static void usage(int errno) {

        System.err.println("Usage: java pj2 Goldbach <n>");
        if (errno == 1) {
          System.out.println("<n> must be an even number > 4 (type int).");
        }
        System.exit(1);
    }



}
