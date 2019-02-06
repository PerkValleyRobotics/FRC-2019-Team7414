/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.*;
import org.usfirst.frc.team7414.robot.Monitors.*;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;
import org.usfirst.frc.team7414.robot.Monitors.PCMMonitor;
import org.usfirst.frc.team7414.robot.Commands.TeleopMoveLeft;
import org.usfirst.frc.team7414.robot.Commands.TeleopMoveRight;
import org.usfirst.frc.team7414.robot.Commands.TeleopArmPush;

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
	public static PCMMonitor pcmmonitor;

	@Override
	public void robotInit() {
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.camera);
		compressor = new Compressor(PortMap.pcm);
		compressor.setClosedLoopControl(false);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		//server.getVideo();
		//server.putVideo(vision, 320, 240);
		//pcmmonitor = new PCMMonitor(PortMap.pcm);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (oi.getButtonPressed(PortMap.moveLeft)) {
			Scheduler.getInstance().add(new TeleopMoveLeft());
		}
		if (oi.getButtonPressed(PortMap.moveRight)) {
			Scheduler.getInstance().add(new TeleopMoveRight());	
		}
		if (oi.getButtonPressed(PortMap.pushPistonsToggle)) {
			Scheduler.getInstance().add(new TeleopArmPush());
		}
		//constantly updating value for compressors
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Range:", Double.toString(proximity.read()));
		//List<Fault> pcmFaults = pcmmonitor.getFaults();
		//SmartDashboard.putNumber("Pneumatic faults: ", pcmFaults.size());
	}
}
