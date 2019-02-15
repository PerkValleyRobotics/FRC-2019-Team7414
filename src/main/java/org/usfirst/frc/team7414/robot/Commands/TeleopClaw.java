package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopClaw extends Command {
    
    //boolean finished = false;

    public TeleopClaw() {
        requires(Robot.claw);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
        //return finished;
    }

    @Override
    protected void execute() {
        if (Robot.oi.getButtonPressed(PortMap.clawToggle)) {
            Claw.updateTime();
            if (Claw.state.equals(ClawState.OUT)) {
                Claw.state = ClawState.IN;
            } else {
                Claw.state = ClawState.OUT;
            }
        }

        if (Claw.millis>1500) {
            Claw.state = ClawState.DONEIN;
        }
        
        if (Claw.state.equals(ClawState.IN)) {
            Robot.claw.pullIn();
        } else if (Claw.state.equals(ClawState.OUT)) {
            Robot.claw.pushOut();
        } else if (Claw.state.equals(ClawState.DONEIN)) {
            Robot.claw.stop();
        }
        //finished = true;
    }

    protected void end() {
        
    }
}