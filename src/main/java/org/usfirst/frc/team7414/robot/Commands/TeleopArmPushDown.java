package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmPushDown extends Command {

    boolean finished = false;
    
    public TeleopArmPushDown() {
        requires(Robot.arm);
    }

    public boolean isFinished() {
        return finished;
    }

    public void execute() {
        Robot.arm.pushDown();
        finished = true;
    }
}