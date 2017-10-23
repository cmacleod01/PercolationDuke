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
	
	
	
	public PercolationStats(int n, int t) { //create object for analysis

		int size = n;
		int trials = t;
		if(n<=0 || t<=0) { //check bounds
			throw new IllegalArgumentException("Out of bounds!!");
		}
		array = new double[trials]; //store the values
		for(int j = 0; j<trials; j++) { //store each cell value in list
			IUnionFind finder = new QuickFind();
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

	
	
	public static void main(String[] args) {
//		PercolationStats newobj = new PercolationStats(20, 10); //sample object for testing
//		System.out.println(newobj.mean());
//		System.out.println(PercolationStats.stddev());
//		System.out.println(trials);
//		System.out.println(PercolationStats.confidenceLow());
//		System.out.println(PercolationStats.confidenceHigh());
//		
	}
}
