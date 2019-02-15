package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.States.PistonState;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmLift extends Command {

    private boolean finished = false;
    
    public TeleopArmLift() {
        requires(Robot.arm);
        //setInterruptible(true);
    }
    
    @Override
    protected boolean isFinished() {
        return finished;
    }

    protected void execute() {
        Robot.arm.actuateLiftPiston();
        finished = true;
    }

    protected void interrupted() {
        end();
    }

    protected void end() {
        /*if (Robot.arm.liftState.equals(PistonState.IN)) {
            Robot.arm.liftSolenoid.set(Value.kReverse);
        } else {
            Robot.arm.liftSolenoid.set(Value.kForward);
        }*/
    }
}