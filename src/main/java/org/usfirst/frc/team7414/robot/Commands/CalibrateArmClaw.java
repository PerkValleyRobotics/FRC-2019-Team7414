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
        sleep();
        Robot.arm.actuatePushPistons();
        sleep();

        Robot.claw.pushOut();
        sleep();
        Robot.claw.pullIn();
        sleep();
        Robot.claw.pushOut();
        sleep();
        Robot.claw.pullIn();
        sleep();

        Robot.arm.actuatePushPistons();
        sleep();
        Robot.arm.actuateLiftPiston();
        sleep();

        finished = true;
    }

    
    public void sleep() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
    }
    
    protected void interrupted() {
        end();
    }

    protected void end() {
        
    }
}