package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateDrive extends Command {
    private static double SPEED = 0.1;

    private boolean finished = false;

	public CalibrateDrive() {
		requires(Robot.difDrive);
		setInterruptible(true);
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
    }
    
    private void sleep() {
        Robot.difDrive.sleep(500);
    }
	
	protected void execute() {
        // Rotate
        Robot.difDrive.drive(SPEED, -1);
        sleep();
        Robot.difDrive.drive(SPEED, 1);
        sleep();
        // Forward Backward
        Robot.difDrive.drive(SPEED, 0);
        sleep();
        Robot.difDrive.drive(SPEED, 0);
        sleep();
        // Stop
        end();
        this.finished = true;
	}

	protected void interrupted() {
		end();
	}

	protected void end() {
		Robot.difDrive.stop();
	}
}
