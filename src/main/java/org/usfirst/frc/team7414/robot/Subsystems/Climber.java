package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Commands.TeleopClimb;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class Climber extends Subsystem {

    private PWMVictorSPX liftMotor1;
    private PWMVictorSPX liftMotor2;
    private Encoder liftEncoder;

    public Climber() {
        liftMotor1 = new PWMVictorSPX(PortMap.lift1);
        liftMotor2 = new PWMVictorSPX(PortMap.lift2);
        liftEncoder = new Encoder(PortMap.liftEncoder1, PortMap.liftEncoder2);
    }

    public void liftUp() {
        liftMotor1.set(0.4);
        liftMotor2.set(0.4);
    }

    public void returnDown() {
        if (liftEncoder.get()>0) {
            liftMotor1.set(-0.4);
            liftMotor2.set(-0.4);
        } else {
            stopLift();
        }
    }

    public void stopLift() {
        liftMotor1.set(0);
        liftMotor2.set(0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopClimb());
    }
}