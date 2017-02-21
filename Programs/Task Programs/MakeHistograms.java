import java.io.*;
import java.util.Random;

class MakeHistograms
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
        Histogram hist = new Histogram(20, 0.4, 0.9);      
        Histogram gaussHist = new Histogram(50, -2, 2); //<<<TASK3>>>
        Histogram dHist = new Histogram(50, 0, 150); //<<TASK4>>>
        
        
        screen.println( "Input the number of random numbers to generate");
        int trials = new Integer(keyboard.readLine()).intValue();
        for (int i = 0; i < trials; i++) {
            double value = randGen.nextDouble();
            hist.fill(value);
        }
        
        //For Gaussian Graph
        for (int i=0;i<trials;i++) //<<<TASK3>>>
        {
            double value = nearGauss(0,0.5);
            gaussHist.fill(value);
        }
        
        //For D graph
        for(int i=0;i<trials;i++)  //<<<TASK4>>>
        {
            double value = (-15*Math.log(randGen.nextDouble()));
            dHist.fill(value);
        }
        
        
        //Console and file print
        hist.print();
        hist.writeToDisk("test.csv"); //<<<TASK3>>>
        gaussHist.print();              //<<<TASK3>>>
        gaussHist.writeToDisk("gauss_test.csv");   //<<<TASK3>>>
        dHist.writeToDisk("d_test.csv");
    }
}
        
