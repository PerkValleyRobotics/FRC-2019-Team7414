package org.usfirst.frc.team7414.robot.Commands;
import org.usfirst.frc.team7414.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopClawDrop extends Command {
   
    TeleopClawDrop() {
        requires(Robot.claw);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute() {
        
    }
}