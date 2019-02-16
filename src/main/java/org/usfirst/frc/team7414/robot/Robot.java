/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.*;
import org.usfirst.frc.team7414.robot.Commands.*;

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
		server.startAutomaticCapture(PortMap.cameraHigh);
		compressor = new Compressor(PortMap.pcm);
		compressor.setClosedLoopControl(true);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		SmartDashboard.putData("Calibrate Arm and Claw", new CalibrateArmClaw());
		SmartDashboard.putData("Calibrate Drive", new CalibrateDrive());
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Ahead:", Double.toString(proximity.read()));
		SmartDashboard.putBoolean("In Range:", proximity.read()<40 && proximity.read()>30); //numbers need testing
		SmartDashboard.putString("Claw:", claw.getState());
	}
}
