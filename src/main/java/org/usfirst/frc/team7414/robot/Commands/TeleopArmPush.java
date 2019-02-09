package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmPush extends Command {

    boolean finished = false;

    public TeleopArmPush(){
        //requires(Robot.arm);
        setInterruptible(true);
    }

    public boolean isFinished() {
        return finished;
    }

    public void execute() {
        //Robot.arm.actuatePushPistons();
        finished = true;
    }

    protected void interrupted() {
        end();
    }

    protected void end() {
        //Robot.arm.pushSolenoid1.set(Value.kReverse);
        //Robot.arm.pushSolenoid2.set(Value.kReverse);
    }
}