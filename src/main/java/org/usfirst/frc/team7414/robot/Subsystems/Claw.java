package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.States.ClawState;
import org.usfirst.frc.team7414.robot.Commands.TeleopClaw;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

    public static ClawState state = ClawState.IN;
    public static long millis = System.currentTimeMillis();

    public Spark clawMotor = new Spark(PortMap.claw);

    public void pushOut() {
        //long millis = System.currentTimeMillis();
        //state = ClawState.BETWEEN;
        clawMotor.set(0.5);
        /*while (System.currentTimeMillis()<millis+700) { //arbitrary number, needs testing
            clawMotor.set(0.3);
        }*/
        //state = ClawState.OUT;
    }

    public void pullIn() {
        //long millis = System.currentTimeMillis();
        //state = ClawState.BETWEEN;
        // while (System.currentTimeMillis()<millis+1000) {// arbitrary number, needs testing
        //     clawMotor.set(-0.3);
        // }
        //clawMotor.set(0);
        clawMotor.set(-0.5);
        //state = ClawState.IN;
    }

    public void stop() {
        clawMotor.set(0);
    }

    public static void updateTime() {
        millis = System.currentTimeMillis();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopClaw());
    }
}