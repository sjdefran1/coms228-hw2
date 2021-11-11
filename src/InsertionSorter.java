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
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "Insertion Sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
	
		long beg = System.nanoTime();
		for(int i = 1; i < points.length; i++)
		{
			int j = i - 1;
		
			if(pointComparator.compare(points[i], points[j]) < 0 ) //points[i].compareTo(points[j]) < 0
			{
				swap(i, j);
				if(j == 0)
				{
					continue;
				}

				while(pointComparator.compare(points[j], points[j-1]) < 0) //points[j].compareTo(points[j-1]) < 0
				{
					j--;
					swap(j, j+1);

					if(j == 0)
					{
					break;
					}
				}
			}
		}
		sortTime = System.nanoTime() - beg;

	}

	@Override
	public String toString()
	{
		return "Insertion Sort";
	}
	
	
	
	
}
