/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.*;
import org.usfirst.frc.team7414.robot.Monitors.*;

import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;
import org.usfirst.frc.team7414.robot.Monitors.PCMMonitor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
	
	public static DriveTrain difDrive = new DriveTrain();
	public static Claw claw = new Claw();
	public static Arm arm = new Arm();
	public static OIHandler oi = new OIHandler();

	public static CameraServer server;
	public static Compressor compressor;
	public static ProximitySensor proximity;
	public static String vision;
	public static PCMMonitor pcmmonitor;
	public static DigitalInput HighLimitSwitch;
	public static DigitalInput LowLimitSwitch;
	public static ProximitySensor proximityBack;

	@Override
	public void robotInit() {
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.camera);
		compressor = new Compressor(PortMap.pcm);
		compressor.setClosedLoopControl(true);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		proximityBack = new ProximitySensor(PortMap.proximityBack);
		//LowLimitSwitch = new DigitalInput(1);
		//HighLimitSwitch = new DigitalInput(2);
		
		//server.getVideo();
		//server.putVideo(vision, 320, 240);
		//pcmmonitor = new PCMMonitor(PortMap.pcm);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//constantly updating value for compressors
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Ahead:", Double.toString(proximity.read()));
		SmartDashboard.putString("Behind:", Double.toString(proximityBack.read()));
		//List<Fault> pcmFaults = pcmmonitor.getFaults();
		//SmartDashboard.putNumber("Pneumatic faults: ", pcmFaults.size());
		/*if (LowLimitSwitch.get()) {
			//when lower switch is pressed, something happens
		} else if (HighLimitSwitch.get()) {
			//when upper limit switch is pressed, something else happens
		}*/
	}
}
