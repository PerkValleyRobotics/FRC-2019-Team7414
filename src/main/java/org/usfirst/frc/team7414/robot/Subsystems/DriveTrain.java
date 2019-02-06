package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopDrive;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;

public class DriveTrain extends Subsystem {

	private static PWMVictorSPX backLeft = new PWMVictorSPX(PortMap.driveBackLeft);
	private static PWMVictorSPX frontLeft = new PWMVictorSPX(PortMap.driveFrontLeft);
	private static PWMVictorSPX backRight = new PWMVictorSPX(PortMap.driveBackRight);
	private static PWMVictorSPX frontRight = new PWMVictorSPX(PortMap.driveFrontRight);

	private static SpeedControllerGroup leftSide = new SpeedControllerGroup(backLeft, frontLeft);
	private static SpeedControllerGroup rightSide = new SpeedControllerGroup(backRight, frontRight);
	
	private static DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
	
	private static Encoder leftEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	private static Encoder rightEncoder = new Encoder(4, 5, false, Encoder.EncodingType.k4X);

	public DriveTrain() {
		 
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}

	public void drive(double speed, double rotation) {
		double kCompensate = 0.132; //old drivetrain value
		kCompensate = -0.1; //new drivetrain value
		if (speed < 0) {
			kCompensate *= -1;
		}

		if (Robot.oi.getMissile()) { //for better controllable driving at low speeds
			speed /= 1.75;
			rotation /= 1.3;
		}
		if (Robot.oi.getButton(11)) {
			moveLeft();
		} else if (Robot.oi.getButton(12)) {
			moveRight();
		} else if (Robot.oi.getButton(PortMap.straightDrive)) {
			straightDrive(kCompensate);
		} else if (Robot.oi.getTrigger()) { //for better control when attempting to go straight
			triggerDrive(speed, rotation, kCompensate);
		} else {
			rotation += kCompensate;
			//this will slow down the speed of the motors based on the joystick w/o button use
			rotation /= 1.4;
			drive.arcadeDrive(speed, rotation);
		}
	}

	public void moveLeft() {
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.get()>-2) { //arbitrary number, needs to be tested
			drive.tankDrive(-0.4, 0.3);
		}
		drive.tankDrive(0, 0);
		leftEncoder.reset();
		rightEncoder.reset();
		while (rightEncoder.get()>-2) { //arbitrary number, needs to be tested
			drive.tankDrive(0.3, -0.4);
		}
	}

	public void moveRight() {
		leftEncoder.reset();
		rightEncoder.reset();
		while (rightEncoder.get()<2) { //arbitrary number, needs to be tested
			drive.tankDrive(-0.3, 0.4);
		}
		drive.tankDrive(0, 0);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.get()<2) { //arbitrary number, needs to be tested
			drive.tankDrive(0.4, -0.3);
		}
	}

	public void straightDrive(double compensation) {
		drive.arcadeDrive(0.4, compensation);
	}

	public void triggerDrive(double speed, double rotation, double compensation) {
		boolean turning = Math.abs(speed)<.35 && Math.abs(rotation)>.1;
		speed /= 2.0; //curvatureDrive is significantly faster than arcadeDrive, for some reason
		rotation /= 3.0;
		rotation += compensation;
		drive.curvatureDrive(speed, rotation, turning);
	}
}
