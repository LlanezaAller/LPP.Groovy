public class MonteCarlo
{
	final static int SEED = 113;

	public static final double integrate(int Num_samples)
	{
		Random R = new Random(SEED);


		int under_curve = 0;
		for (int count=0; count<Num_samples; count++)
		{
			double x= R.nextDouble();
			double y= R.nextDouble();

			if ( x*x + y*y <= 1.0)
				 under_curve ++;
			
		}
		
		return ((double) under_curve / Num_samples) * 4.0;
	}

	
}
