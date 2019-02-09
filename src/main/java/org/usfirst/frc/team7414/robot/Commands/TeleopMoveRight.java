package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopMoveRight extends Command {

    private boolean finished = false;

    public TeleopMoveRight() {
        requires(Robot.difDrive);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void execute() {
        Robot.difDrive.moveLeft();
        finished = true;
    }
}