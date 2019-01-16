/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. 
All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	
	public static Joystick joystick = new Joystick(0);
	public static OIHandler oi = new OIHandler();
	public static DriveTrain difDrive = new DriveTrain();

	@Override
	public void robotInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleOpDrive() {
		 difDrive.drive(oi.getY(), oi.getX());
	}
}
