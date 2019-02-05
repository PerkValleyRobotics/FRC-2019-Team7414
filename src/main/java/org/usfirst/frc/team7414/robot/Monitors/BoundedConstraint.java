package org.usfirst.frc.team7414.robot.Monitors;

public class BoundedConstraint implements Comparable<Double> {
    private double upperBound;
    private double lowerBound;

    public BoundedConstraint(double upperBound, double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    /* Returns 0 if value is within the constraint.
     * +1 means value is above the constraint.
     * -1 means value is below the constraint. */
    public int compareTo(Double value) {
        if(value > upperBound) return 1;
        if(value < lowerBound) return -1;
        return 0;
    }
}