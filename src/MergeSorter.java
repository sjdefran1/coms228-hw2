package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

import java.util.Arrays;

/**
 *  
 * @author Sam DeFrancisco
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "Merge Sort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		long beg = System.nanoTime();
		mergeSortRec(points);
		sortTime = System.nanoTime() - beg;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		
		if(pts.length < 2)
		{
			return;
		}
		
		int midpoint = pts.length / 2;
		int start = 0;
		int end = pts.length;

		Point[] lhs = Arrays.copyOfRange(pts, start, midpoint);
		Point[] rhs = Arrays.copyOfRange(pts, midpoint, end);

		mergeSortRec(lhs);
		mergeSortRec(rhs);

		merge(lhs, rhs, pts);

	}

	
	// Other private methods if needed ...


	private void merge(Point[] lhs, Point[] rhs, Point[] pts)
	{
		int l=0, r = 0, p=0;
		int rSize = rhs.length;
		int lSize = lhs.length;

		while(l < lSize && r < rSize)
		{
			if(pointComparator.compare(lhs[l], rhs[r]) < 0) //pointComparator.compare(lhs[l], rhs[r]) //lhs[l].compareTo(rhs[r]) < 0
			{
				pts[p++] = lhs[l++];
			}
			else{
				pts[p++] = rhs[r++];
			}
		}

		while(l<lSize)
		{
			pts[p++] = lhs[l++];
		}
		while(r<rSize)
		{
			pts[p++] = rhs[r++];
		}

	}


	@Override
	public String toString()
	{
		return "Merge Sort";
	}

}
