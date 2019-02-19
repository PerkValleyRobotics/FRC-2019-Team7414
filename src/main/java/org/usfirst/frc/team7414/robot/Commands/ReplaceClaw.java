package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReplaceClaw extends Command {
    
    boolean finished;

    public ReplaceClaw() {
        requires(Robot.claw);
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public void execute() {
        Robot.claw.pushOut();
        try {
			Thread.sleep(2000);
		} catch (Exception e) {

        }
        Robot.claw.stop();
        Robot.oi.getButtonPressed(PortMap.clawToggle);
        while (!Robot.oi.getButtonPressed(PortMap.clawToggle)) {

        }
        Robot.claw.pullIn();
        try {
			Thread.sleep(2000);
		} catch (Exception e) {

        }
        Robot.claw.stop();
    }

    public void end() {

    }
}