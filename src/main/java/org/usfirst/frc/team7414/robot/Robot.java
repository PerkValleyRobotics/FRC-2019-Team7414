/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.DriveTrain;
import org.usfirst.frc.team7414.robot.Subsystems.Claw;
import org.usfirst.frc.team7414.robot.Subsystems.Forklift;
import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	public static DoubleSolenoid solenoid;
	public static String vision;
	
	@Override
	public void robotInit() {
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.camera);
		compressor = new Compressor(PortMap.compressor);
		compressor.setClosedLoopControl(true);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		solenoid = new DoubleSolenoid(PortMap.solenoid1, PortMap.solenoid2);
		server.getVideo();
		server.putVideo(vision, 320, 240);
		solenoid.set(Value.kOff);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	    //double proxDistance = proximity.read();
		//System.out.println(proxDistance);
		
		//*Checks the status of the compressors*//
		boolean enabled = compressor.enabled();
        boolean pressureSwitch = compressor.getPressureSwitchValue();
		double current = compressor.getCompressorCurrent();

		Boolean.toString(enabled);
		Boolean.toString(pressureSwitch);
		Double.toString(current);

		//constantly updating value for compressors

		System.out.println("Enabled? " + enabled);
		System.out.print("\r");
		System.out.print("Enabled? " + enabled);
		System.out.println("Pressure Switch Value: " + pressureSwitch);
		System.out.print("\r");
		System.out.print("Pressure Switch Value: " + pressureSwitch);
		System.out.println("Current: " + current);
		System.out.print("\r");
		System.out.print("Current: " + current);
	}
	
	public void teleOpDrive() {
		 difDrive.drive(oi.getY(), oi.getX());
	}
}
