package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.OIHandler;
import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopTurnLeft extends Command {

    //boolean finished = false;

    public TeleopTurnLeft() {
        requires(Robot.difDrive);
    }
    
    protected boolean isFinished() {
        return !OIHandler.turnLeftButton.get(); 
    }

    protected void execute() {
        Robot.difDrive.turnLeft();
        //finished = true;
    }

    protected void end() {
        Robot.difDrive.stop();
    }
}