import java.io.*;
import java.util.Random;

class ThrowDist
{
    static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter screen = new PrintWriter(System.out, true);
    static Random randomGenerator = new Random();

    private static double p(double x) 
    {
	final double norm = 1.; // to be adjusted
	return (x*Math.exp(-x*x*x)/norm);
    }

    private static double Integrate(int nsteps, double xmin, double xmax)
    {
	double sum = 0;
	double deltaX = (xmax - xmin)/(double)nsteps;
	double x = xmin + deltaX/2.;
	for (int n = 0; n < nsteps; n++) {
	    sum += p(x)*deltaX; 
	    x += deltaX;
	}
	return sum;
    }

    private static double throwValueInt(int nsteps, double xmin, double xmax)
    { 
	double sum = 0; 
	double deltaX = (xmax - xmin)/(double)nsteps;
	double x = xmin + deltaX/2.;

	double rand = randomGenerator.nextDouble(); // generate a random number uniformly distributed in [0, 1)

	// integrate func(x) until sum > rand
	for (int n = 0; n < nsteps; n++) {
	    sum += p(x)*deltaX;
	    // abort loop when sum > rand
	    if (sum > rand) {
		break; 
	    }
	    x += deltaX;
	}
	// now x holds the wished-for random number
	return x ; 
    }

    private static double throwValueHitMiss(double xmin, double xmax, double ymin, double ymax)
    { 
	boolean miss = true;
	double randX = 0;
	double randY = 0;

	while (miss) {
	    // generate two random numbers, uniformly distributed in [xmin, xmax) and [ymin, ymax)
	    randX = randomGenerator.nextDouble()*(xmax-xmin)+xmin;
	    randY = randomGenerator.nextDouble()*(ymax-ymin)+ymin;
	    
	    // Hit or Miss?
	    miss = randY > p(randX);
	}
	// now randX holds the wished-for random number
	return randX; 
    }

    public static void main (String [] args ) throws IOException
    {
	final int nsteps = 10; // to be adjusted
	final double xmin = 0;
	final double xmax = 3;

	final double ymin = 0;
	final double ymax = 1; // to be adjusted

	double sum = Integrate(nsteps, xmin, xmax);
        screen.println("Integral is " + sum);

 	Histogram histInt = new Histogram(50, xmin, xmax);
 	Histogram histHM = new Histogram(50, xmin, xmax);

	screen.println("Input the number of random numbers to generate");
	int trials = new Integer(keyboard.readLine()).intValue();

	for (int i = 0; i < trials; i++) {
	    histInt.fill(throwValueInt(nsteps, xmin, xmax));
	    histHM.fill(throwValueHitMiss(xmin, xmax, ymin, ymax));
       	}

	histInt.print(screen);
	histInt.writeToDisk("histInt.csv");

	histHM.print(screen);
	histHM.writeToDisk("histHM.csv");
    }
}
