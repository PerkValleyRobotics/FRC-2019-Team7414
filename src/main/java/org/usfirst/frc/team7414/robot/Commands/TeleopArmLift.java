package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmLift extends Command {

    private boolean finished = false;
    
    public TeleopArmLift() {
        requires(Robot.arm);
    }
    
    @Override
    protected boolean isFinished() {
        return finished;
    }

    protected void execute() {
        //mechanics of toggling piston is handled by Arm.java class
        Robot.arm.actuateLiftPiston();
        finished = true;
    }

    protected void interrupted() {
        end();
    }

    protected void end() {

    }
}