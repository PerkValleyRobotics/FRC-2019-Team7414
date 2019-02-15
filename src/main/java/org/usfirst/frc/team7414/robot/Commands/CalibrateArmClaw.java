package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateArmClaw extends Command {

    boolean finished = false;

    public CalibrateArmClaw(){
        requires(Robot.arm);
        requires(Robot.claw);
        setInterruptible(true);
    }

    public boolean isFinished() {
        return finished;
    }

    public void execute() {
        Robot.arm.actuateLiftPiston();
        Robot.arm.actuatePushPistons();

        Robot.claw.pushOut();
        Robot.claw.pullIn();
        Robot.claw.pushOut();
        Robot.claw.pullIn();

        Robot.arm.actuatePushPistons();
        Robot.arm.actuateLiftPiston();

        finished = true;
    }

    protected void interrupted() {
        end();
    }

    protected void end() {
        
    }
}