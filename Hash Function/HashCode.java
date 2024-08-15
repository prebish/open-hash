
import java.io.*;
import java.util.*;
import java.text.*;

public class HashCode
{
	private int numBuckets;
	// private Bucket[] buckets; // DESIGN THIS CLASS FOR PROJECT main
	private int[] bucketSizes;
	private int idealBucketSize;
	private int size; // # keys stored in set

	public HashCode( int numBuckets, int idealBucketSize )
	{
		size=0;
		this.numBuckets = numBuckets;
		this.idealBucketSize = idealBucketSize;
		bucketSizes = new int[numBuckets]; // OF KEYS IN EACH [i] BUCKET
	}
	// KEYS ARE -NOT- REALLY BEING ADDED. JUST HASHED, AND BUCKET COUNTER INCREMENTED
	public boolean add( String key )
	{
		int h = hashOf( key, numBuckets ); // h MUST BE IN [0..numBuckets-1]
		++bucketSizes[h];
		++size;
		return true;
	}
	public double printStats()
	{	System.out.format
		("#OfBuckets:(%s) x idealBucketSize:(%d)  =  #OfKeysHashed:(%s)\n",
		  NumberFormat.getNumberInstance(Locale.US).format(bucketSizes.length), idealBucketSize,
		  NumberFormat.getNumberInstance(Locale.US).format(size())
		);

		if (bucketSizes.length < 100 ) System.out.println("Bucket  Size   +/- ");
		if (bucketSizes.length < 100 ) System.out.println("-------------------");
		int minBucketSize=bucketSizes[0], maxBucketSize=bucketSizes[0];

		for (int i=0 ; i<numBuckets ; ++i)
		{
			if (bucketSizes.length < 100 ) System.out.format("%5d %5d  %5d\n",i,bucketSizes[i],bucketSizes[i]-idealBucketSize );
			if ( bucketSizes[i] > idealBucketSize )
				maxBucketSize=bucketSizes[i];
			else if ( bucketSizes[i] < minBucketSize )
				minBucketSize=bucketSizes[i];
		}
		if (bucketSizes.length < 100 )  System.out.println("-------------------");
		System.out.format("actual_minBucketSize %d  actual_maxBucketSize %d\n",minBucketSize,maxBucketSize);
		return variance(bucketSizes);
	}

	// Function for calculating variance
	double variance(int a[])
	{	int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		double mean = (double)sum / (double)a.length;

		double sqDiff = 0.0;
		for (int i = 0; i < a.length; i++)
			sqDiff += (a[i] - mean) * (a[i] - mean);
		return sqDiff / a.length;
	}

	double stdDev(int a[])
	{
		return Math.sqrt(variance(a));
	}
	public int size()
	{
		return size;
	}

	// RETURNS INT IN RANGE [0..numBuckets-1]
	private int hashOf( String key, int numBuckets ) // h MUST BE IN [0..numBuckets-1]
	{
		int total = 0;
		int keyLength = key.length();
		
		for (int i = 0; i<keyLength; i++)
		{
			total += key.charAt(i);	
			total *= 26; // Best time so far: 7 , 26	
			total += 29; // Best variance so far: 29 
		}
		return Math.abs(total) % numBuckets;
	}

} //END CLASS


