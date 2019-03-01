package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopClaw extends Command {

    public TeleopClaw() {
        requires(Robot.claw);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute() {
        if (Robot.oi.getButtonPressed(PortMap.clawToggle)) {
            if (Claw.state.equals(ClawState.OUT)) {
                Claw.state = ClawState.IN;
                Claw.updateTime();
            } else {
                Claw.state = ClawState.OUT;
            }
        }

        if (Claw.millis+1700<System.currentTimeMillis() && Claw.state.equals(ClawState.IN)) {
            Claw.state = ClawState.OFF;
        }
        
        if (Claw.state.equals(ClawState.IN)) {
            Robot.claw.pullIn();
        } else if (Claw.state.equals(ClawState.OUT)) {
            Robot.claw.pushOut();
        } else if (Claw.state.equals(ClawState.OFF)) {
            Robot.claw.stop();
        }
    }

    protected void end() {
        
    }
}