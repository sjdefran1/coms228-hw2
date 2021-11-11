package edu.iastate.cs228.hw2;

/**
 *  
 * @author Sam DeFrancisco
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator;  

	protected long sortTime; //stores execution time to be sent to PointScanner
	
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0)
		{
			throw new IllegalArgumentException("pts is null or has a length of 0");
		}
		
		points = pts;
	}

		
	
	
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		if(order > 1 || order < 0)
		{
			throw new IllegalArgumentException("Order needs to be 0 or 1");
		}
	
		//sort by x, change each point's xORy to sort by x
		if(order == 0)
		{
			pointComparator = new PointComp();
		}
		
		//sort by y, change each point's xORy to sort by y
		if(order == 1)
		{
			pointComparator = new PointCompY();
		}
	}

	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		pts = points;
	}


	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
		
	}

	/**
	 * Returns the sorting time of one round of sorting to PointScanner so stats() can be called
	 * @return 
	 */
	public long getSortTime()
	{
		return sortTime;
	}

	 
}
