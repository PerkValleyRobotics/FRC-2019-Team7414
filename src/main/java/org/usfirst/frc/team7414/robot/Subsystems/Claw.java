package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Commands.TeleopClaw;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

    public static ClawState state = ClawState.OFF;
    public static long millis;

    public Spark clawMotor;

    public Claw() {
        millis = System.currentTimeMillis();
        clawMotor = new Spark(PortMap.claw);
    }
    
    public void pushOut() {
        updateTime();
        clawMotor.set(0.5);
    }

    public void pullIn() {
        updateTime(); 
        clawMotor.set(-0.5);
    }

    public void stop() {
        clawMotor.set(0);
    }

    public static void updateTime() {
        millis = System.currentTimeMillis();
    }


    public String getState() {
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