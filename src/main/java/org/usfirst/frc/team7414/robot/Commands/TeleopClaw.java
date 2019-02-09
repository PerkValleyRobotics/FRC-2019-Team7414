package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopClaw extends Command {
    
    boolean finished = false;

    public TeleopClaw() {
        requires(Robot.claw);
    }
    
    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void execute() {
        if (Claw.state.equals(ClawState.IN)) {
            Robot.claw.pushOut();
        } else if (Claw.state.equals(ClawState.OUT)) {
            Robot.claw.pullIn();
        }
        finished = true;
    }

    protected void end() {

    }
}