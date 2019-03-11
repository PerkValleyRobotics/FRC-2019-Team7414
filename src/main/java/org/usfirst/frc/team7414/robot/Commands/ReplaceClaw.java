package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class ReplaceClaw extends Command {

    public ReplaceClaw() {
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
                Claw.state = ClawState.OFF;
                Claw.updateTime();
            } else if (Claw.state.equals(ClawState.OFF)) {
                Claw.state = ClawState.IN;
            } else {
                Claw.state = ClawState.OUT;
            }
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