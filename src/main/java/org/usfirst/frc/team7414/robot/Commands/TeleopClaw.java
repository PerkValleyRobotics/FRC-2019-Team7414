package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopClaw extends Command {
    
    public TeleopClaw() {
        //requires(Robot.claw);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute() {
        if (Claw.state.equals(ClawState.IN)) {
            //Robot.claw.pushOut();
        } else if (Claw.state.equals(ClawState.OUT)) {
            //Robot.claw.pullIn();
        } else if (Claw.state.equals(ClawState.BETWEEN)) {
            //Robot.claw.pullIn();
        }
    }
}