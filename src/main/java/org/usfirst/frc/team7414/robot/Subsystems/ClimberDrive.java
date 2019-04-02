package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.Commands.TeleopClimbDrive;
import org.usfirst.frc.team7414.robot.PortMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class ClimberDrive extends Subsystem {
    
    private PWMVictorSPX driveMotor1;
    private PWMVictorSPX driveMotor2;

    public ClimberDrive() {
        driveMotor1 = new PWMVictorSPX(PortMap.liftDrive1);
        driveMotor2 = new PWMVictorSPX(PortMap.liftDrive2);
    }

    public void drive() {
        driveMotor1.set(0.5);
        driveMotor2.set(0.5);
    }

    public void stopDrive() {
        driveMotor1.set(0);
        driveMotor2.set(0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopClimbDrive());
    }
}