/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.DriveTrain;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.cameraserver.CameraServer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
	
	public static OIHandler oi = new OIHandler();
	public static DriveTrain difDrive = new DriveTrain();
	public static CameraServer server;
	
	@Override
	public void robotInit() {
		server = CameraServer.getInstance();
		//server.setQuality();
		server.startAutomaticCapture(0);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleOpDrive() {
		 difDrive.drive(oi.getY(), oi.getX());
	}
}
