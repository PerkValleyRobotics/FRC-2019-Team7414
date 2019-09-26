package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Commands.TeleopClaw;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

    public static ClawState state;
    public static long millis;

    public Spark clawMotor;

    public Claw() {
        //Claw must grab the hatch upon startup
        state = ClawState.OUT;
        millis = System.currentTimeMillis();
        clawMotor = new Spark(PortMap.claw);
    }
    
    public void pushOut() {
        //claw extends (grabs a hatch)
        clawMotor.set(-0.23);
    }

    public void pullIn() {
        //claw retracts (drops a hatch)
        clawMotor.set(0.20);
    }

    public void stop() {
        //claw motor stops applying pressure
        clawMotor.set(0);
    }

    public static void updateTime() {
        millis = System.currentTimeMillis();
    }

    public String getState() {
        //returns the state of the claw
        if (state.equals(ClawState.IN)) {
            return "IN";
        } else if (state.equals(ClawState.OUT)) {
            return "OUT";
        } else {
            return "OFF";
        }
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopClaw());
    }
}