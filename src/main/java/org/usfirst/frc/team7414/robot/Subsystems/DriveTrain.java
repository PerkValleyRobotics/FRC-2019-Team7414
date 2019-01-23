package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;
import org.usfirst.frc.team7414.robot.Commands.TeleopDrive;

import edu.wpi.first.wpilibj.*;
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
	
	public DriveTrain() {
		 
	}
	
	public void drive(double speed, double rotation) {
		double kCompensate = 0.132; //compensates for drifting
		if (speed < 0) {
			kCompensate *= -1;
		}

		if (Robot.oi.getMissile()) { //for better controllable driving at low speeds
			speed /= 1.5;
			rotation /= 1.5;
		}

		if (Math.abs(rotation)<.05) { //get rid of accidental drift
			rotation = 0;
		}

		if (Robot.oi.getButton(4)) { //slow, straight driving mostly for debugging
			speed = .4;
			rotation = Math.abs(kCompensate);
			drive.arcadeDrive(speed, rotation);
		} else if (Robot.oi.getTrigger()) { //for better control when attempting to go straight
			boolean turning = Math.abs(speed)<.15 && Math.abs(rotation)>.2;
			speed /= 2.0; //curvatureDrive is significantly faster than arcadeDrive, for some reason
			rotation /= 3.0;
			rotation += kCompensate;
			drive.curvatureDrive(speed, rotation, turning);
		} else {
			rotation += kCompensate;
			drive.arcadeDrive(speed, rotation);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
}
