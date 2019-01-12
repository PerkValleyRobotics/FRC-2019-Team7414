package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	//assumes that we're using PWMTalon controllers
	private static PWMTalonSRX backLeft = new PWMTalonSRX(PortMap.driveBackLeft);
	private static PWMTalonSRX backRight = new PWMTalonSRX(PortMap.driveBackRight);
	private static PWMTalonSRX midLeft = new PWMTalonSRX(PortMap.driveMidLeft);
	private static PWMTalonSRX midRight = new PWMTalonSRX(PortMap.driveMidRight);
	private static PWMTalonSRX frontLeft = new PWMTalonSRX(PortMap.driveFrontLeft);
	private static PWMTalonSRX frontRight = new PWMTalonSRX(PortMap.driveFrontRight);

	private static SpeedControllerGroup leftSide = new SpeedControllerGroup(backLeft, midLeft, frontLeft);
	private static SpeedControllerGroup rightSide = new SpeedControllerGroup(backRight, midRight, frontRight);
	
	private static DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
	
	public DriveTrain() {
		 
	}
	
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
