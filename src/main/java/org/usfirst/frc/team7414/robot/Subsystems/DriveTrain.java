package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopDrive;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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
	
	private static Encoder leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k1X);
	private static Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k1X);

	private static boolean squaring = true;
	
	public DriveTrain() {
		leftEncoder.setReverseDirection(true); 
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}

	public void drive(double speed, double rotation) {
		squaring = true;
		double kCompensate = 0.132; //old drivetrain value
		//kCompensate = -0.1; //new drivetrain value
		if (speed < 0) {
			kCompensate *= -1;
		}

		if (Robot.oi.getMissile()) { //for better controllable driving at low speeds
			speed /= 2.3;
			rotation /= 2.3;
			squaring = false;
		}
		if (Robot.oi.getButton(PortMap.straightDrive)) {
			straightDrive(kCompensate);
		} else if (Robot.oi.getTrigger()) { //for better control when attempting to go straight
			triggerDrive(speed, rotation, kCompensate);
		} else {
			rotation += kCompensate;
			//this will slow down the speed of the motors based on the joystick w/o button use
			rotation /= 1.4;
			drive.arcadeDrive(speed, rotation, squaring);
		}
	}

	public void moveRight() {
		long millis = System.currentTimeMillis();
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()>-70 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(-0.5, 0.5);
		}
		drive.tankDrive(0, 0);
		sleep(500);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()>-100 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(-0.5, -0.5);
		}
		drive.tankDrive(0, 0);
		sleep(500);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()<65 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(0.5, -0.5);
		}
		drive.tankDrive(0, 0);
		sleep(500);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()<90 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(0.5, 0.5);
		}
	}

	public void moveLeft() {
		long millis = System.currentTimeMillis();
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()<70 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(0.5, -0.5);
		}
		drive.tankDrive(0, 0);
		sleep(500);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()>-100 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(-0.5, -0.5);
		}
		drive.tankDrive(0, 0);
		sleep(500);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()>-75 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(-0.5, 0.5);
		}
		drive.tankDrive(0, 0);
		sleep(500);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance()<85 && System.currentTimeMillis()<millis+10000) {
			drive.tankDrive(0.5, 0.5);
		}
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {

		}
	}

	public void straightDrive(double compensation) {
		drive.arcadeDrive(0.4, compensation);
	}

	public void triggerDrive(double speed, double rotation, double compensation) {
		boolean turning = Math.abs(speed)<.45 && Math.abs(rotation)>.1;
		speed /= 2.0; //curvatureDrive is significantly faster than arcadeDrive, for some reason
		rotation /= 3.0;
		rotation += compensation;
		drive.curvatureDrive(speed, rotation, turning);
	}

	public void stop() {
		drive.arcadeDrive(0, 0);
	}

	public void turnLeft() {
		long millis = System.currentTimeMillis();
		leftEncoder.reset();
		while (leftEncoder.getDistance()>-30 && System.currentTimeMillis()<millis+500) { //arbitrary numbers, needs testing
			drive.tankDrive(-0.4, 0.4);
		}
		stop();
	}

	public void turnRight() {
		long millis = System.currentTimeMillis();
		leftEncoder.reset();
		while (leftEncoder.getDistance()<30 && System.currentTimeMillis()<millis+500) { //arbitrary numbers, needs testing
			drive.tankDrive(0.4, -0.4);
		}
		stop();
	}
}
