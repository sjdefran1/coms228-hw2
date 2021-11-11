package edu.iastate.cs228.hw2;

/**
 * 
 * @author Sam DeFrancisco
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;   
	
	
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 


	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		
		if(pts == null || pts.length == 0)
		{
			throw new IllegalArgumentException("Points is null or 0");
		}
		
		points = pts;
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File file = new File(inputFileName); //will throw
		
		ArrayList<Point> results = CompareSorters.readFileForPoints(file);

		//results is returned as null if there was a issue reading from file
		if(results == null)
		{
			throw new InputMismatchException("File does not include a even number of integers");
		}
		
		//assign points[] w/ ArrayList<Point> results
		points = results.toArray(new Point[results.size()]);
		sortingAlgorithm = algo;
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
	
		AbstractSorter aSorter;
		
		//Selection Sort
		if(sortingAlgorithm  == Algorithm.SelectionSort)
		{
			aSorter = new SelectionSorter(points);
			medianCoordinatePoint = scanHelper(aSorter);
		}
		
		//Insertion Sort
		if(sortingAlgorithm  == Algorithm.InsertionSort)
		{
			aSorter = new InsertionSorter(points);
			medianCoordinatePoint = scanHelper(aSorter);
		}
		
		//Merge Sort
		if(sortingAlgorithm  == Algorithm.MergeSort)
		{
			aSorter = new MergeSorter(points);
			medianCoordinatePoint = scanHelper(aSorter);
		}
		
		//Quick Sort
		if(sortingAlgorithm  == Algorithm.QuickSort)
		{
			aSorter = new QuickSorter(points);
			medianCoordinatePoint = scanHelper(aSorter);
		}


		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     *d) set the medianCoordinatePoint reference to the object with the correct coordinates.* nani
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
						//time to do x + time to do y
						//sort x, call getRunTime(), sory y, call get runTime(), add the results
		
		
		
	}

	/**
	 * 
	 * @param sorter - type of sorting method to use
	 * @return returns median coord point to be set in scan()
	 * 
	 * Sort's by choosing right Comparator, calling sorter.sort(), then storing median point
	 * First does x, then y
	 * creates medianCoordinatePoint
	 */
	private Point scanHelper(AbstractSorter sorter)
	{
		//choose x coords
		sorter.setComparator(0);
		//sort x 
		sorter.sort();
		//store time of first sort
		long sort1 = sorter.getSortTime();

		//store median point for x values for MCP
		
		int xMed = sorter.getMedian().getX();

		//choose y coords
		sorter.setComparator(1);
		sorter.sort();
		long sort2 = sorter.getSortTime();
		
		int yMed = sorter.getMedian().getY();

		//store scanTime (sort1 + sort2)
		scanTime=0;
		scanTime = sort1 + sort2;
		
		


		//set median coord point with (xMed, yMed)
		return new Point(xMed, yMed);
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		//SelectionSort
		//InsertionSort 
		if(sortingAlgorithm  == Algorithm.SelectionSort || sortingAlgorithm  == Algorithm.InsertionSort )
		{
			return sortingAlgorithm.name() + " " + points.length + "  " +scanTime;
		}
		
		
		//Merge Sort
		//Quick Sort
		if(sortingAlgorithm  == Algorithm.MergeSort || sortingAlgorithm  == Algorithm.QuickSort)
		{
			return sortingAlgorithm.name() + "     " + points.length + "  " +scanTime;
		}

		return "";
	}

	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")"; 
		
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		File file = new File("output.txt");
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(this.toString());
			fileWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
