package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopArm;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Arm extends Subsystem {
    
    DoubleSolenoid solenoid;
    String state;

    public Arm() {
        solenoid = new DoubleSolenoid(PortMap.solenoid1, PortMap.solenoid2);
        state = PortMap.pistonIn;
    }
    
    protected void initDefaultCommand() {
		setDefaultCommand(new TeleopArm());
    }
    
    public void actuate() {
        if (Robot.oi.getButton(PortMap.pistonActivate) && state.equals(PortMap.pistonIn)) {
            solenoid.set(Value.kForward);
            state = PortMap.pistonOut;
        } else if (Robot.oi.getButton(PortMap.pistonDeActivate) && state.equals(PortMap.pistonOut)) {
            solenoid.set(Value.kOff);
            state = PortMap.pistonIn;
        }
    }
}