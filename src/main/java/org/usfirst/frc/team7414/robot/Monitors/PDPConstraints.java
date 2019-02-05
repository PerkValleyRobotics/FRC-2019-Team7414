package org.usfirst.frc.team7414.robot.Monitors;

import java.util.Collections;
import java.util.Optional;

/* The first 16 constraints (0 thru 15) are constraints on
 * the current draw of each device on the corrisponding port.
 * Constraints after this are labeled with static names shown below. */
public class PDPConstraints extends Constraints {
    public static int CHANNELS = 16;
    private static int MAXCONSTRAINTS = 19;
    
    // Special Constraints
    public static int TOTALCURRENT = 16;
    public static int INPUTVOLTAGE = 17;
    public static int TEMPERATURE = 18;

    public PDPConstraints() {
        super.constraints = Collections.nCopies(MAXCONSTRAINTS, Optional.empty());
    }
}