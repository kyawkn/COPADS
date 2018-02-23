/**
 * Goldbach.java
 *
 *
 */

import edu.rit.pj2.Task;
import edu.rit.pj2.Loop;
import edu.rit.pj2.vbl.IntVbl;
import java.math.BigInteger;

public class Goldbach extends Task {

    // command inputs
    int seed;

    // shared answers
    IntVbl counts;
    int  maxX = 0;
    int  minX;

    public void main(String[] args) throws Exception {

        if (args.length != 1) usage(0);

        try {
            seed = Integer.parseInt(args[0]);
	    counts = new IntVbl.Sum(0);
            if (seed < 5) usage(1);
	      minX = seed;	
              parallelFor(1, seed/2) .exec (new Loop () {

                IntVbl localCount;

                public void start() {
                  localCount = threadLocal (counts);
                }

                public void run(int i) {
                  BigInteger x = BigInteger.valueOf(i);
                  BigInteger y = BigInteger.valueOf(seed - i);


                  if(x.isProbablePrime(64) && y.isProbablePrime(64)) {
		     localCount.item ++;
		     maxX = (i > maxX) ? i : maxX;
		     minX = (i < minX) ? i : minX;
                }
		}

              });
	
              if(counts.item == 1) {
		System.out.println("1 solution");
             	 System.out.printf("%d = %d + %d%n", seed, maxX, seed - maxX);

		} else if (counts.item > 1) {
		System.out.printf("%d solutions%n", counts.item);
		System.out.printf("%d = %d + %d%n", seed, minX, seed - minX);
		System.out.printf("%d = %d + %d%n", seed, maxX, seed - maxX);
		}
		 else {
		System.out.println("No solutions");
		 }


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
