package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.*;
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
        if (Claw.state.equals(PortMap.clawIn)) {
            Robot.claw.pushOut();
        } else if (Claw.state.equals(PortMap.clawOut)) {
            Robot.claw.pullIn();
        } else if (Claw.state.equals(PortMap.clawBetween)) {
            Robot.claw.pullIn();
        }
    }
}