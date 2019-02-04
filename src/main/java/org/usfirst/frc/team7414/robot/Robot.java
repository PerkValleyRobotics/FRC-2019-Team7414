/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.*;

import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
	
	public static OIHandler oi = new OIHandler();
	public static DriveTrain difDrive = new DriveTrain();
	//public static Claw claw = new Claw();
	public static Arm arm = new Arm();
	
	public static CameraServer server;
	public static Compressor compressor;
	public static ProximitySensor proximity;
	public static String vision;
	
	@Override
	public void robotInit() {
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.camera);
		compressor = new Compressor(PortMap.pcm);
		compressor.setClosedLoopControl(false);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		//server.getVideo();
		//server.putVideo(vision, 320, 240);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//constantly updating value for compressors
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Range:", Double.toString(proximity.read()));
		/*System.out.println("Enabled? " + enabled);
		System.out.print("\r");
		System.out.print("Enabled? " + enabled);
		System.out.println("Pressure Switch Value: " + pressureSwitch);
		System.out.print("\r");
		System.out.print("Pressure Switch Value: " + pressureSwitch);
		System.out.println("Current: " + current);
		System.out.print("\r");
		System.out.print("Current: " + current);*/
	}
}
