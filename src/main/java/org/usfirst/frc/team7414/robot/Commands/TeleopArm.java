package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopArm extends Command {

    public TeleopArm() {
        requires(Robot.arm);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void execute() {
        Robot.arm.actuate();
    }
}