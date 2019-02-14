package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.States.ClawState;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

    public static ClawState state = ClawState.OUT;

    public static Spark clawMotor = new Spark(PortMap.claw);

    public void pushOut() {
        long millis = System.currentTimeMillis();
        state = ClawState.BETWEEN;
        while (System.currentTimeMillis()<millis+400) { //arbitrary number, needs testing
            clawMotor.set(0.2);
        }
        clawMotor.set(0.1);
        state = ClawState.OUT;
    }

    public void pullIn() {
        long millis = System.currentTimeMillis();
        state = ClawState.BETWEEN;
        while (System.currentTimeMillis()<millis+400) {// arbitrary number, needs testing
            clawMotor.set(-0.2);
        }
        clawMotor.set(0);
        state = ClawState.IN;
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}