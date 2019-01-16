package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team7414.robot.Commands.TeleopDrive;

public class DriveTrain extends Subsystem {

	//assumes that we're using PWMVictor controllers
	private static PWMVictorSPX backLeft = new PWMVictorSPX(PortMap.driveBackLeft);
	private static PWMVictorSPX frontLeft = new PWMVictorSPX(PortMap.driveFrontLeft);
	private static PWMVictorSPX backRight = new PWMVictorSPX(PortMap.driveBackRight);
	private static PWMVictorSPX frontRight = new PWMVictorSPX(PortMap.driveFrontRight);

	private static SpeedControllerGroup leftSide = new SpeedControllerGroup(backLeft, frontLeft);
	private static SpeedControllerGroup rightSide = new SpeedControllerGroup(backRight, frontRight);
	
	private static DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
	
	public DriveTrain() {
		 
	}
	
	public void drive(double left, double right) {
		drive.arcadeDrive(left, right);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
}
