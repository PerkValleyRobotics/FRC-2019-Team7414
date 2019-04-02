package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.OIHandler;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopClimbDrive extends Command {

    public TeleopClimbDrive() {
        requires(Robot.climbDrive);
    }

    public void execute() {
        if (OIHandler.controller.getYButton()) {
            Robot.climbDrive.drive();
        } else {
            Robot.climbDrive.stopDrive();
        }
    }

    public void end() {

    }

    protected boolean isFinished() {
        return false;
    }
}