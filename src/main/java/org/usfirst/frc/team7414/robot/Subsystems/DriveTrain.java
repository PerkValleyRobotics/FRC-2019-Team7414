package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopDrive;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	private static PWMVictorSPX backLeft = new PWMVictorSPX(PortMap.driveBackLeft);
	private static PWMVictorSPX frontLeft = new PWMVictorSPX(PortMap.driveFrontLeft);
	private static PWMVictorSPX backRight = new PWMVictorSPX(PortMap.driveBackRight);
	private static PWMVictorSPX frontRight = new PWMVictorSPX(PortMap.driveFrontRight);

	private static SpeedControllerGroup leftSide = new SpeedControllerGroup(backLeft, frontLeft);
	private static SpeedControllerGroup rightSide = new SpeedControllerGroup(backRight, frontRight);
	
	private static DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
	
	private static boolean squaring = true;
	
	public DriveTrain() {
	
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}

	public void drive(double speed, double rotation) {
		squaring = true;
		double kCompensate = -0.130; //the drivetrain is uneven, so a constant steering adjustment is necessary
		
		//flip the steering compensation if going backwards
		if (speed < 0) {
			kCompensate *= -1;
		}

		
		if (Robot.oi.getMissile()) { //slow speed and rotation for better control
			speed /= 1.8;
			rotation /= 1.8;
			squaring = false;
		}

		if (Robot.oi.getButton(PortMap.straightDrive)) { //drive straight forward slowly
			straightDrive(kCompensate);
		} else if (Robot.oi.getButton(PortMap.straightBack)) { //drive straight backward slowly
			straightBackup(kCompensate);
		} else if (Robot.oi.getTrigger()) { //for better control when attempting to go straight
			triggerDrive(speed, rotation, kCompensate);
		} else {
			rotation += kCompensate;
			//this will slow down the speed of the motors based on the joystick w/o button use
			rotation /= 1.4;
			drive.arcadeDrive(speed, rotation, squaring);
		}
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis); //a fancy way of telling the program to wait
		} catch (Exception e) {

		}
	}

	//drives straight forward slowly, accounting for drift
	public void straightDrive(double compensation) {
		drive.tankDrive(0.4, 0.45);
	}

	//drives backward slowly, accounting for drift
	public void straightBackup(double compensation) {
		drive.tankDrive(-0.4,-0.45);
	}

	public void triggerDrive(double speed, double rotation, double compensation) {
		boolean turning = Math.abs(speed)<.45 && Math.abs(rotation)>.1;
		speed /= 2.0; //curvatureDrive is significantly faster than arcadeDrive, for some reason
		rotation /= 3.0;
		rotation += compensation;
		drive.curvatureDrive(speed, rotation, turning); //typically gives better steering at high speeds
	}

	//shut off motors
	public void stop() {
		drive.arcadeDrive(0, 0);
	}

	//turn slowly to the left
	public void turnLeft() {
		drive.tankDrive(-0.27, 0.27, false);		
	}

	//turn slowly to the right
	public void turnRight() {
		drive.tankDrive(0.27, -0.27, false);
	}
}
