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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "Selection Sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		long beg = System.nanoTime();
		int minIndex = 0;
		boolean swap = false;
		
		
		for(int i = 0; i < points.length; i++)
		{
			Point min = points[i]; //first part of right side that hasnt been sorted 		
			for(int j = i; j < points.length; j++)
			{
				if(pointComparator.compare(points[j], min) < 0) // points[j].compareTo(min) < 0
				{
					min = points[j];
					minIndex = j;
					swap = true;
				}
			}
		
			//swap min into curr's position
			if(swap)
			{
				swap(i, minIndex);
				swap = false;
			}
		}

		sortTime = System.nanoTime() - beg;
	}
	
	
	@Override
	public String toString()
	{
		return "Selection Sort";
	}
}
