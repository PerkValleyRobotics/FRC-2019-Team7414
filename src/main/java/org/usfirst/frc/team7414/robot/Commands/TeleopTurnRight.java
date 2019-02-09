package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopTurnRight extends Command {
    
    boolean finished = false;

    public TeleopTurnRight() {
        requires(Robot.difDrive);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void execute() {
        Robot.difDrive.turnLeft();
        finished = true;
    }

    protected void end() {

    }
}