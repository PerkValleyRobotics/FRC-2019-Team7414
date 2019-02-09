package org.usfirst.frc.team7414.robot.Monitors;

import java.util.List;
import java.util.Optional;

public abstract class Constraints {
    protected List<Optional<BoundedConstraint>> constraints;

    private boolean isValidIndex(int i) {
        return i >= 0 && i < constraints.size();
    }

    public Optional<BoundedConstraint> get(int i) {
        if(isValidIndex(i)) return constraints.get(i);
        return Optional.empty();
    }

    // See specific constraint subclass for details on how to set constraints.
    public boolean set(int i, BoundedConstraint c) {
        if(isValidIndex(i)) {
            constraints.set(i, Optional.of(c));
            return true;
        }
        return false;
    }

    // Evaluates the i^th constraint and returns a fault if value is under/over it.
    public Optional<Fault> getFault(int i, double value, Fault under, Fault over) {
        Optional<BoundedConstraint> bc = get(i);
        if(bc.isPresent()) {
            int cmpt = bc.get().compareTo(value);
            switch(cmpt) {
                case -1:
                    return Optional.of(under);
                case 1:
                    return Optional.of(over);
                }
        }
        return Optional.empty();
    }
}