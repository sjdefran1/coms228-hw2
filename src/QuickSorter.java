package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Sam DeFrancisco
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
			
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "Quick Sorter";

	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{	
		long beg = System.nanoTime();
		quickSortRec(0, points.length - 1);
		sortTime = System.nanoTime() - beg;
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first < last)
		{
			int partIndex = partition(first, last);


			//sort before and after partition w recursion
			quickSortRec(first, partIndex - 1);
			quickSortRec(partIndex + 1, last);
		}


	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		
		//sets are pivot to the last element in subarray
		Point pivot = points[last];

		//used for swapping, incremets everytime a ponint is less than our pivot
		int i = (first-1);

		for(int j = first; j <= last -1; j++)
		{
			// points[j] < pivot
			if(pointComparator.compare(points[j], pivot) < 0) //points[j].compareTo(pivot) < 0
			{
				i++;
				swap(i, j);
			}
		}
		
		//swap our pivot and one after last sorted element
		swap(i+1, last);

		return (i+1); //return where to start next sortRec()
		
	}	
		


	@Override
	public String toString()
	{
		return "Quick Sort";
	}
	// Other private methods if needed ...
}
