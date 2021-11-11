package edu.iastate.cs228.hw2;

import java.util.Comparator;




/**
 * 
 * @author Sam DeFrancisco
 * implements Comparator
 * Changes Point class's static var xORy so we can sort by only x values
 */
public class PointComp implements Comparator<Point> {
    
    @Override
    public int compare(Point a, Point b)
    {
        Point.xORy = true;
        return a.compareTo(b);
    }
    
}
