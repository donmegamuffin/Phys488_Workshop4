import java.io.*;
import java.util.Random;

class Task4
{
    static BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in)) ;
    static PrintWriter screen = new PrintWriter(System.out, true);
    static Random randGen = new Random();

    private static double nearGauss(double mean, double sigma)
    {
    // add up 12 random numbers
    double sum = 0.;
    for (int n = 0 ; n < 12; n++) {
        sum = sum + randGen.nextDouble();
    }
    return (mean + sigma*(sum - 6.0));
    }

    public static void main (String [] args ) throws IOException
    {       
        // create an instance of the Class Histogram: 20 bins from 0.0 to 1.0
        Histogram dHist = new Histogram(100, -0.1, 0.3); 
        Histogram Gauss1 = new Histogram(100,-0.1, 0.3);
        Histogram Gauss2 = new Histogram(100,-0.1, 0.3);
        
        //Variables
        double lifeTime = Math.pow(10,-10);
        double speed = (3*Math.pow(10, 8));

        screen.println( "Input the number of random numbers to generate");
        int trials = new Integer(keyboard.readLine()).intValue();
        
        //For D graph
        for(int i=0;i<trials;i++)
        {
            double d_decay = (-lifeTime*speed*Math.log(randGen.nextDouble()));
            dHist.fill(d_decay);
            double d_measure1 = (d_decay+(randGen.nextGaussian()*0.01));
            Gauss1.fill(d_measure1);
            double d_measure2 = (d_decay+(randGen.nextGaussian()*0.05));
            Gauss2.fill(d_measure2);
        }
        
        //Console and file print
        
        dHist.writeToDisk("dHist.csv");   
                  
        Gauss1.writeToDisk("Gauss1.csv");   
                      
        Gauss2.writeToDisk("Gauss2.csv");   
        
        screen.println("Files have been written.");
    }
}
        
