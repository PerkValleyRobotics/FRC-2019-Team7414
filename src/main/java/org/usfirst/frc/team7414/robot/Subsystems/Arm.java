package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopArm;
import org.usfirst.frc.team7414.robot.States.PistonState;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Arm extends Subsystem {
    
    DoubleSolenoid solenoid;
    DoubleSolenoid solenoid2;
    PistonState liftState;
    PistonState pushState;

    public Arm() {
        solenoid = new DoubleSolenoid(PortMap.pcm, PortMap.solenoid1, PortMap.solenoid2);
        solenoid2 = new DoubleSolenoid(PortMap.pcm, PortMap.solenoid3, PortMap.solenoid4);
        solenoid.set(Value.kReverse);
        solenoid2.set(Value.kReverse);
        liftState = PistonState.IN;
    }
    
    protected void initDefaultCommand() {
		setDefaultCommand(new TeleopArm());
    }

    public void toggleArmPiston() {
        if (pushState == PistonState.IN) {
            solenoid2.set(Value.kForward);
            pushState = PistonState.OUT;
        } else {
            solenoid2.set(Value.kReverse);
            pushState = PistonState.IN;
        }
    }
    
    public void actuate() {
        if (Robot.oi.getButton(PortMap.pistonActivate)) {
            solenoid.set(Value.kForward);
            liftState = PistonState.OUT;
        } else if (Robot.oi.getButton(PortMap.pistonDeActivate)) {
            solenoid.set(Value.kReverse);
            liftState = PistonState.IN;
        } else {
            solenoid.set(Value.kOff);
        }
    }

    
}