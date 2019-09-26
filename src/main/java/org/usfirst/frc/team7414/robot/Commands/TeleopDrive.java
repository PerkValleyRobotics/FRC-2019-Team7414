package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	public TeleopDrive() {
		requires(Robot.difDrive);
		//This is the default command, so it needs to be interruptible in order for other commands to run when desired
		setInterruptible(true);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void execute() {
		//pass the joystick values to the drivetrain class
		Robot.difDrive.drive(Robot.oi.getY(), Robot.oi.getX());
	}

	protected void interrupted() {
		end();
	}

	protected void end() {
		Robot.difDrive.stop();
	}
}
