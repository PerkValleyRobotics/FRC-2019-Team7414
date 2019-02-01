package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Commands.*;
import org.usfirst.frc.team7414.robot.States.ClawState;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

    public static ClawState state = ClawState.OUT;

    private static Encoder clawEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    private static PWMVictorSPX clawMotor = new PWMVictorSPX(PortMap.claw); //

    public void pushOut() {
        state = ClawState.BETWEEN;
        while (clawEncoder.getDistance()<1230847) { //some number of encoder ticks that will be measured later
            clawMotor.set(.02);
        }
        state = ClawState.OUT;
    }

    public void pullIn() {
        state = ClawState.BETWEEN;
        while (clawEncoder.getDistance()>0) {
            clawMotor.set(-.02);
        }
        state = ClawState.IN;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopClaw());
    }
}