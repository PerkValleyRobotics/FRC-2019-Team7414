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

    /* This is probably not how we should handle commands in the future
    because it pulls all of the methods and properties out of the subsystem
    itself. It's also bad because it runs constantly and checks for button 
    presses, rather than running once to complete a singular action.
    Best is probably to have execute() call a method within the Claw.java
    class that would handle all of the logic. */
    @Override
    protected void execute() {
        //if the button has been pressed:
        //If claw is out, retract it. Otherwise, push it out.
        if (Robot.oi.getButtonPressed(PortMap.clawToggle)) {
            if (Claw.state.equals(ClawState.OUT)) {
                Claw.state = ClawState.IN;
                Claw.updateTime();
            } else {
                Claw.state = ClawState.OUT;
            }
        }

        //to prevent the claw from torquing in on itself too much, shut down the motor
        //after enough time has passed for the claw to fully retract.
        if (Claw.millis+1700<System.currentTimeMillis() && Claw.state.equals(ClawState.IN)) {
            Claw.state = ClawState.OFF;
        }
        
        //based on the ClawState that has been set previously in the function's call,
        //tell the claw motor how to actually respond.
        if (Claw.state.equals(ClawState.IN)) {
            Robot.claw.pullIn();
        } else if (Claw.state.equals(ClawState.OUT)) {
            Robot.claw.pushOut();
        } else if (Claw.state.equals(ClawState.OFF)) {
            Robot.claw.stop();
        }
    }

    protected void interrupt() {
        //if this ever gets interrupted for some reason, retract the claw to keep it safe
        Robot.claw.pullIn();
    }

    protected void end() {
        
    }
}