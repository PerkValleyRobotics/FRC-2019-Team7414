package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ThirdLevelHatch extends Command {
    
    boolean finished;

    public ThirdLevelHatch() {
        requires(Robot.arm);
        requires(Robot.claw);
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public void execute() {
        Scheduler.getInstance().add(new TeleopArmPush());
        Scheduler.getInstance().add(new TeleopArmLift());
        Scheduler.getInstance().add(new TeleopClaw()); 
        finished = true;
    }
}