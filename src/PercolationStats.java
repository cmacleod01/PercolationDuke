//Carolyn Macleod
import java.util.*;


/**
 * Compute statistics on Percolation after performing T independent experiments on an N-by-N grid.
 * Compute 95% confidence interval for the percolation threshold, and  mean and std. deviation
 * Compute and print timings
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 * @author Josh Hug
 */

public class PercolationStats {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);
	public static double[] array; //array to store values
	public static int trials; //initialize number of trials
	public static int size; //initalize the size
	
	
	
	public PercolationStats(int n, int t) { //create object for analysis

		size = n;
		trials = t;
		if(n<=0 || t<=0) { //check bounds
			throw new IllegalArgumentException("Out of bounds!!");
		}
		array = new double[trials]; //store the values
		for(int j = 0; j<trials; j++) { //store each cell value in list
			IUnionFind finder = new QuickFind();
//			IUnionFind finder = new QuickUWPC();
			
//			IPercolate perc = new PercolationDFS(size);
//			IPercolate perc = new PercolationDFSFast(size,finder);
			IPercolate perc = new PercolationUF(size,finder);
			ArrayList<Integer> orderList = new ArrayList<Integer>();
			for(int i=0;i<(size*size);i++) {
				orderList.add(i);
			}
			Collections.shuffle(orderList,ourRandom); //random order of cells
			for(int each : orderList) { //randomly open cells
				perc.open(each/size, each%size);
				if(perc.percolates()) {
					double openSites = perc.numberOfOpenSites();
					double totalOpen = (size*size);
					array[j] = openSites/totalOpen;
					break;
				}
			}
		}
	}
	public static double mean() { //calculate mean of all values
        if (array.length == 0) return Double.NaN; 
        double sum = 0.0;
        for(double each : array) {
        	sum += each;
        }
        return sum / (double) array.length;
    }

//
	public static double stddev() { //calculate std dev of all values
		if (array.length == 0) return Double.NaN;
        double avg = mean();
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            sum += (array[i] - avg) * (array[i] - avg);
        }
        return Math.sqrt(sum / (array.length - 1));
   
    }
	
	   public static double confidenceLow() { //low confidence calculate
//		   System.out.println(mean() - ((1.96*(stddev())/Math.sqrt(trials)))); 
			return mean() - (1.96 * stddev()) / Math.sqrt(trials) ;
			
		}

	   public static double confidenceHigh() { //high confidence calculate
//		   System.out.println(mean() + ((1.96*(stddev())/Math.sqrt(trials)))); 
		   return (mean() + (1.96 * stddev()) / Math.sqrt(trials)) ;
		}

	
	
	public static void main(String[] args) { //execute
		double start = System.nanoTime();
//		PercolationStats tester = new PercolationStats(size,trials);
//		PercolationStats tester = new PercolationStats(100,50);
		double end = System.nanoTime();
		double time = (end-start)/1e9; // total time 
	
		System.out.printf("mean: %1.4f, time: %1.4f\n",mean(),time);
		
		
//		System.out.print("Standard deviation: ");
//		System.out.println(tester.stddev());
//		
//		System.out.print("Lower confidence bound: ");
//		System.out.println(tester.confidenceLow());
//		
//		System.out.print("Upper confidence bound: ");
//		System.out.println(tester.confidenceHigh());
//		
//		double seconds = time / 1000.0;
//		System.out.println("time = " + seconds + " seconds");
//		System.out.println(time);

//		
//		
	}
}
