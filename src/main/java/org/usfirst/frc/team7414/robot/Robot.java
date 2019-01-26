/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//hi
package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.DriveTrain;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import org.usfirst.frc.team7414.robot.Subsystems.Forklift;
import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;


public class Robot extends TimedRobot {
	
	public static OIHandler oi = new OIHandler();
	public static DriveTrain difDrive = new DriveTrain();
	public static Claw claw = new Claw();
	public static Forklift lift = new Forklift();
	public static CameraServer server;
	public static Compressor compressor;
	public static ProximitySensor proximity;
	public static Solenoid firstSolenoid;
	
	@Override
	public void robotInit() {
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.camera);
		//compressor = new Compressor(PortMap.compressor);
		compressor.setClosedLoopControl(true);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		firstSolenoid.set(true);	
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		double proxDistance = proximity.read();
		//System.out.println(proxDistance);
		
		//*Checks the status of the compressors*//
		// boolean enabled = c1.enabled();
        // boolean pressureSwitch = c1.getPressureSwitchValue();
        // double current = c1.getCompressorCurrent();
        // System.out.println(enabled);
        // System.out.println(pressureSwitch);
        // System.out.println(current);		
	}
	
	public void teleOpDrive() {
		 difDrive.drive(oi.getY(), oi.getX());
	}
}
