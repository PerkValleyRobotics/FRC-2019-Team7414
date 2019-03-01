package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.OIHandler;
import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopTurnRight extends Command {

    public TeleopTurnRight() {
        requires(Robot.difDrive);
    }

    protected boolean isFinished() {
        return !OIHandler.turnRightButton.get();
    }

    protected void execute() {
        Robot.difDrive.turnRight();
    }

    protected void end() {
        Robot.difDrive.stop();
    }
}