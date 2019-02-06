package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopArm;
import org.usfirst.frc.team7414.robot.States.PistonState;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Arm extends Subsystem {
    
    public DoubleSolenoid liftSolenoid;
    public DoubleSolenoid pushSolenoid1;
    public DoubleSolenoid pushSolenoid2;
    public PistonState liftState;
    public PistonState pushState;

    public Arm() {
        liftSolenoid = new DoubleSolenoid(PortMap.pcm, PortMap.liftSolenoid1, PortMap.liftSolenoid2);
        pushSolenoid1 = new DoubleSolenoid(PortMap.pcm, PortMap.pushSolenoid1, PortMap.pushSolenoid2);
        pushSolenoid2 = new DoubleSolenoid(PortMap.pcm, PortMap.pushSolenoid3, PortMap.pushSolenoid4);
        liftSolenoid.set(Value.kReverse);
        pushSolenoid1.set(Value.kReverse);
        pushSolenoid2.set(Value.kReverse);
        liftState = PistonState.IN;
        pushState = PistonState.IN;
    }
    
    protected void initDefaultCommand() {
		setDefaultCommand(new TeleopArm());
    }

    public void actuatePushPistons() {
        if (pushState.equals(PistonState.IN)) {
            pushSolenoid1.set(Value.kForward);
            pushSolenoid2.set(Value.kForward);
            pushState = PistonState.OUT;
        } else {
            pushSolenoid1.set(Value.kReverse);
            pushSolenoid2.set(Value.kReverse);
            pushState = PistonState.IN;
        }
    }
    
    public void actuateLiftPiston() {
        if (Robot.oi.getButtonPressed(PortMap.liftPistonToggle)) {
            if (liftState.equals(PistonState.IN)) {
                liftSolenoid.set(Value.kForward);
            } else {
                liftSolenoid.set(Value.kReverse);
            }
        }

        /*if (Robot.oi.getButton(PortMap.liftPistonActivate)) {
            liftSolenoid.set(Value.kForward);
            liftState = PistonState.OUT;
        } else if (Robot.oi.getButton(PortMap.liftPistonDeActivate)) {
            liftSolenoid.set(Value.kReverse);
            liftState = PistonState.IN;
        } else {
            liftSolenoid.set(Value.kOff);
        }*/
    }
}