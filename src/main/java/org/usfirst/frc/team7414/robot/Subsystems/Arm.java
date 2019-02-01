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
    PistonState state;

    public Arm() {
        solenoid = new DoubleSolenoid(PortMap.solenoid1, PortMap.solenoid2);
        state = PistonState.IN;
    }
    
    protected void initDefaultCommand() {
		setDefaultCommand(new TeleopArm());
    }
    
    public void actuate() {
        if (Robot.oi.getButton(PortMap.pistonActivate) && state.equals(PistonState.IN)) {
            solenoid.set(Value.kForward);
            state = PistonState.OUT;
        } else if (Robot.oi.getButton(PortMap.pistonDeActivate) && state.equals(PistonState.OUT)) {
            solenoid.set(Value.kOff);
            state = PistonState.IN;
        }
    }
}