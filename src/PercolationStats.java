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
	public static double[] array;
	public int trials;
	
	
	
	public PercolationStats(int n, int t) { //create object

		int size = n;
		int trials = t;
		if(n<=0 || t<=0) {
			throw new IllegalArgumentException("Out of bounds!!");
		}
		System.out.println(trials);
		array = new double[t];
		for(int j = 0; j<t; j++) {
			IUnionFind finder = new QuickFind();
			IPercolate perc = new PercolationUF(size,finder);
			ArrayList<Integer> orderList = new ArrayList<Integer>();
			for(int i=0;i<(size*size);i++) {
				orderList.add(i);
			}
			Collections.shuffle(orderList,ourRandom);
			for(int each : orderList) {
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
	public double mean() {
        if (array.length == 0) return Double.NaN; 
        double sum = 0.0;
        for(double each : array) {
        	sum += each;
        }
        return sum / (double) array.length;
    }

//
	public double stddev() {
		if (array.length == 0) return Double.NaN;
        double avg = mean();
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            sum += (array[i] - avg) * (array[i] - avg);
        }
        return Math.sqrt(sum / (array.length - 1));
   
    }
	
	   public double confidenceLow() {
		   System.out.println(mean() - ((1.96*(stddev())/Math.sqrt(trials)))); 
			return (this.mean() - (1.96 * this.stddev()) / Math.sqrt(trials)) ;
		}

	   public double confidenceHigh() {
		   System.out.println(mean() + ((1.96*(stddev())/Math.sqrt(trials)))); 
		   return (this.mean() + (1.96 * this.stddev()) / Math.sqrt(trials)) ;
		}

	
	
	public static void main(String[] args) {
		PercolationStats newobj = new PercolationStats(20, 10);
		
//		System.out.println(PercolationStats.mean());
//		System.out.println(PercolationStats.stddev());
//		
	}
}
