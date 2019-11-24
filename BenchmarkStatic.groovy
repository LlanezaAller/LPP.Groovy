import java.util.*;

/**
	Runs the MonteCarlo benchmark for startup and steady-state measurements.
	This file is a wrapper that allows using the startup / steady-state methodology defined by Georges et al.
	The actual benchmark is in the MonteCarlo.java file.

	Parameters of the program:
	- No parameters => startup execution => just runs the benchmark.
	- 3 parameters => steady state:
	  · Param 1: max number of iterations 
	  · Param 2: k (the number of last measurements to be considered)
	  · Param 3: CoV (coefficient of variation).
	- Return value (to de operating system): mean of the last k executions, in milliseconds.
	  It will be return as the las line printed in the console.
*/

public class BenchmarkStatic {


	/**
	 * One invocation to the benchmark
	 */
	static void benchmark(int mode, int groovyStatic) { 
		boolean isStatic = true;
		if(groovyStatic == 0){
			isStatic = false;
		}
		if(mode == 0){
			if(isStatic){
				print("MonteCarloStatic: ");
				MonteCarloStatic.integrate(50000000);
			}else{
				print("MonteCarlo: ");
				MonteCarlo.integrate(50000000);
			}
		}else if (mode == 1){
			if(isStatic){
				print("BinaryTreesStatic: ");
				BinaryTreesStatic.main();
			}else {
				print("BinaryTrees: ");
				BinaryTrees.main();
			}
		}else if(mode == 2){
			if(isStatic){
				print("FibStatic: ");
				FibStatic.main();
			}
			else{
				print("Fib: ");
				Fib.main();
			}
		}
	}
	
	public static void main(String...args) {
		int mode = Integer.parseInt(args[0]);
		int groovyStatic = Integer.parseInt(args[1]); 
		if (args.length == 2) {
			// startup
			benchmark(mode, groovyStatic);
			return;
		}
		// Steady state
		if (args.length<5) {
			System.err.println("I need 1) the max number of iterations 2) the k 3) the CoV.");
			System.exit(-1);
		}
		
		int maxNumberIterations = Integer.parseInt(args[2]); // 30
		int k = Integer.parseInt(args[3]); // 10
		double CoV = Double.parseDouble(args[4]); // 0.02
		
		List<Long> executionTimes = new ArrayList<Long>();
		long before, after, time;
		for(int i=1; i<=maxNumberIterations; i++) {
			before = System.currentTimeMillis();
			benchmark(mode, groovyStatic);
			after = System.currentTimeMillis();
			time = after-before;
			println("Iteration " + i + ", time "+ time);
			executionTimes.add(time);
			if (areWeDone(executionTimes, k, CoV))
				break;
		}
		int result = (int)getMean(executionTimes, k);
		println(result); // value returned by this application
	}
	
	private static boolean areWeDone(List<Long> executionTimes, int k, double CoV) {
		if (executionTimes.size()<k)
			return false;
		double summation = 0;
		long mean = getMean(executionTimes, k);
		for(int i=executionTimes.size()-k; i<executionTimes.size(); i++)
			summation += Math.pow(executionTimes.get(i)-mean, 2);
		double stdDeviation = Math.sqrt(summation / k);
		//println("StdDev "+ stdDeviation+", mean "+ mean+", CoV "+stdDeviation / mean);
		return stdDeviation / mean < CoV;
	}
	
	private static long getMean(List<Long> executionTimes, int k) {
		long summation = 0;
		for(int i=executionTimes.size()-k; i<executionTimes.size(); i++)
			summation += executionTimes.get(i);
		return summation / k;
	}
	
}
