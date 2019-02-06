package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	public TeleopDrive() {
		requires(Robot.difDrive);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void execute() {
		Robot.difDrive.drive(Robot.oi.getY(), Robot.oi.getX());
	}

	protected void interrupted() {
		end();
	}

	protected void end() {
		Robot.difDrive.stop();
	}
}
