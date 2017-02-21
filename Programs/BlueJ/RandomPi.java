import java.io.*;
import java.util.Random;

class RandomPi
{
    static PrintWriter screen = new PrintWriter( System.out, true);

    private static int seed = 1234;
    private static double myRandom()
    {
        final double maxint = 2147483647.;
        seed = seed * 1664525 + 1013904223;
        return (seed/maxint);
    }
    
    public static void main (String [] args )
    {
 	Random randomGenerator = new Random();

	long in = 0;
	double mypi, delta;
	final long startinterval = 1000000L;
	long interval = startinterval*10L;
	
	final long trials = 1000000000;
	for (long n = 0; true; n++) {
	    // double x = randomGenerator.nextDouble();
	    // double y = randomGenerator.nextDouble();
	    double x = myRandom();
	    double y = myRandom();
	    if (Math.sqrt(x*x+y*y) < 1) {
		in++;
	    }

	    if (n % interval == 0) {
		mypi = (double) in / (double) n;
		delta = Math.sqrt(mypi*(1-mypi)/n);
		mypi *= 4.;
		delta *= 4.;
		screen.println(n + ": " + mypi + " +- " + delta);
		screen.println("pull: " + ((mypi-Math.PI)/delta) );

		interval = ((long)((interval*1.2))/startinterval)*startinterval;
	    }
	}

    }
}
