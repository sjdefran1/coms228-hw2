package edu.iastate.cs228.hw2;

/**
 *  
 * @author Sam DeFrancisco
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 
import java.io.File;
import java.util.ArrayList;


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	

		boolean programTrigger = false; //trigger for program loop
		Scanner scan; //scanner used w/ in program, closed after loop
		int trial = 0;

		while(!programTrigger)
		{
			Point[] points;
			scan = new Scanner(System.in); //re init helps w buffer from invalid input

			String answer = ""; //used for user input

			boolean choiceTrigger = false; //makes them enter 1, 2, or 3
			System.out.println("Trial " + ++trial + ":");
			while(!choiceTrigger)
			{
				System.out.print("1 for random Points, 2 for file path (3 for exit): ");
				answer = scan.next();
				if(answer.equals("1") || answer.equals("2") || answer.equals("3"))
				{
					choiceTrigger = true;
				}
			}
			

			//randomGen
			if(answer.equals("1"))
			{
				Random rand = new Random();
				int numInts;
				
				try
				{
					System.out.print("Enter num of Points to be created: ");
					numInts = scan.nextInt();
					scan.nextLine();
					System.out.println();
					//generate random points, store in points[]
					points = generateRandomPoints(numInts, rand);
				}
				catch(Exception e)
				{
					System.out.println("Invalid input");
					continue;
				}
				
				
			}
			//filepath
			else if(answer.equals("2"))
			{
				System.out.print("Enter filepath:");
				
				
				File file = new File(scan.next());
				System.out.println();


				//try to scan in from file, readFileForPoints() will throw filenotfound
				ArrayList<Point> results;
				try {
					//call helper method to scan in points
					results = readFileForPoints(file);
				} catch (Exception e) {
					System.out.print("File could not be found..");
					results = null;
				}
				
				//if we couldn't find file, or had uneven ints results = null
				if(results == null)
				{
					points = null; //set points to null to alert something wrong
				}
				else
				{
					//assign points[] w/ ArrayList<Point> results
					points = results.toArray(new Point[results.size()]);
				}
				
			}
			//exit prog
			else if(answer.equals("3"))
			{
				points = null;
				programTrigger = true;			
				System.out.println("Progam Exited");	
				break;
			}
			//wont happen, there for scanners[] initialization
			else
			{
				points = null; 
			}
			

			//something messed up we have no points, but we want to continue to run
			if((points == null) && !programTrigger)
			{
				System.out.println("Something went wrong, Try again...");
			}
			//have points run scanners on them
			else
			{
				PointScanner[] scanners = 
					{new PointScanner(points, Algorithm.SelectionSort), 
					new PointScanner(points, Algorithm.InsertionSort), 
					new PointScanner(points, Algorithm.MergeSort), 
					new PointScanner(points, Algorithm.QuickSort)};

				String[] results = new String[scanners.length];
				int i = -1;
				
				//calls each PointScanner of different algorithm types
				for(PointScanner scanner : scanners)
				{	
					scanner.scan();
					results[++i] = scanner.stats(); 					
				}

				//prints final table
				printResults(results);
				
			}
		
		} //end of program loop

		//close scanner, have to init again because it "may not have been initialized"
		scan = new Scanner(System.in);
		scan.close();	
	}
	

	/**
	 * 
	 * @param results - String[] full of each sorting methods stats() string
	 * 
	 * prints out table from example, final results
	 */
	private static void printResults(String[] results)
	{
		
		System.out.println("algorithm   size  time (ns)\n----------------------------------");
		
		//print results
		for(String result : results)
		{
			System.out.print(result + "\n");
		}
		System.out.print("----------------------------------\n");
	}



	/**
	 * 
	 * @param file 	file to be scanned for coords
	 * @return pointsFound, ArrayList<Point>, all of points found in file
	 * @throws FileNotFoundException if(cant find file)
	 * 
	 * Reads given text file
	 * Stores every 2 int's as a new point in ArrayList<Point>
	 */
	public static ArrayList<Point> readFileForPoints(File file) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(file);
		ArrayList<Point> pointsFound = new ArrayList<Point>();

		while(fileReader.hasNextInt())
			{
				try { //try to create next point
					int xCord = fileReader.nextInt();
					int yCord = fileReader.nextInt();
					Point temp = new Point(xCord,yCord);
					pointsFound.add(temp);
				} catch (Exception e) { //if we have an uneven amount of ints
										//set pointsFound to null and return it
					System.out.println("Uneven amount of integers within text file");
					pointsFound = null;
					break;
				}
			}

		//close our reader and return pointsFound
		fileReader.close();
		return pointsFound;
	}



	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1)
		{
			throw new IllegalArgumentException("numPts needs to be > 1");
		}
		
		
		Point[] ranPoints = new Point[numPts];
		int counter = 0;
		for(Point point : ranPoints)
		{
			
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;
		
			ranPoints[counter] = new Point(x,y);
			counter++;
		}

		return ranPoints; 
		
	}
	
}
