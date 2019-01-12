/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team7414.robot.Subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Watchdog;

public class Robot extends TimedRobot {
	
	public static Joystick joystick = new Joystick(0);
	public static OIHandler oi = new OIHandler();
	public static DriveTrain difDrive = new DriveTrain();
	
	/*public static void main(String[] args) throws InterruptedException {
		Joystick stick = new Joystick(2);
		for (int i=0; i<10; i++) {
			System.out.println(stick.getX());
			TimeUnit.SECONDS.sleep(1);
		}
	}*/

	@Override
	public void robotInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		//Watchdog.getInstance().feed();
		Scheduler.getInstance().run();
	}
	
	public void teleOpDrive() {
		 difDrive.drive(oi.getY(), oi.getX());
	}
}
