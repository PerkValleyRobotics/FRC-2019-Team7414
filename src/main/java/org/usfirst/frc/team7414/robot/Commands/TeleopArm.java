package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.States.PistonState;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
        Robot.arm.actuateLiftPiston();
    }

    protected void interrupt() {
        end();
    }

    protected void end() {
        if (Robot.arm.liftState.equals(PistonState.IN)) {
            Robot.arm.liftSolenoid.set(Value.kReverse);
        } else {
            Robot.arm.liftSolenoid.set(Value.kForward);
        }
    }
}