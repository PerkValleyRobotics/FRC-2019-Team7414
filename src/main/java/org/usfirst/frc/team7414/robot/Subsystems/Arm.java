package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.States.PistonState;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Arm extends Subsystem {
    
    public DoubleSolenoid liftSolenoid;
    public DoubleSolenoid pushSolenoid;
    public PistonState liftState;
    public PistonState pushState;

    public Arm() {
        liftSolenoid = new DoubleSolenoid(PortMap.pcm, PortMap.liftSolenoid1, PortMap.liftSolenoid2);
        pushSolenoid = new DoubleSolenoid(PortMap.pcm, PortMap.pushSolenoid1, PortMap.pushSolenoid2);
        liftSolenoid.set(Value.kForward);
        pushSolenoid.set(Value.kForward);
        liftState = PistonState.IN;
        pushState = PistonState.IN;
    }

    public void actuatePushPistons() {
        if (pushState.equals(PistonState.IN)) {
            pushSolenoid.set(Value.kReverse);
            pushState = PistonState.OUT;
            System.out.println("pushState is: " + pushState);
        } else {
            pushSolenoid.set(Value.kForward);
            pushState = PistonState.IN;
            System.out.println("pushState is: " + pushState);
        }
    }
    
    public void actuateLiftPiston() {
        if (liftState.equals(PistonState.IN)) {
            liftSolenoid.set(Value.kForward);
            liftState = PistonState.OUT;
        } else {
            liftSolenoid.set(Value.kReverse);
            liftState = PistonState.IN;
        }
    }

    public void pushOut() {
        pushSolenoid.set(Value.kReverse);
    }

    public void pullIn() {
        pushSolenoid.set(Value.kForward);
    }

    public void pushUp() {
        liftSolenoid.set(Value.kReverse);
    }

    public void pushDown() {
        liftSolenoid.set(Value.kForward);
    }

    protected void initDefaultCommand() {
		
    }
}