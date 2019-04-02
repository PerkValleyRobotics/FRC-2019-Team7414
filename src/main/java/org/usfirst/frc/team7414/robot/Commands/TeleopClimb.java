package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.OIHandler;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopClimb extends Command {

    public TeleopClimb() {
        requires(Robot.climb);
    }
    
    protected boolean isFinished() {
        return false;
    }

    public void execute() {
        if (OIHandler.controller.getAButton()) {
            Robot.climb.liftUp();
        } else if (OIHandler.controller.getBButton()) {
            Robot.climb.returnDown();
        } else {
            Robot.climb.stopLift();
        }
    }

    public void end() {

    }
}