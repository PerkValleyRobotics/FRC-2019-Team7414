/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Subsystems.*;
import org.usfirst.frc.team7414.robot.Commands.CalibrateArmClaw;
import org.usfirst.frc.team7414.robot.Commands.CalibrateDrive;
import org.usfirst.frc.team7414.robot.Commands.ReplaceClaw;
import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;
import org.usfirst.frc.team7414.robot.Monitors.PCMMonitor;
import org.usfirst.frc.team7414.robot.Hardware.UltrasonicSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
	
	public static DriveTrain difDrive;
	public static Claw claw;
	public static Arm arm;
	// public static UltrasonicSensor ultrasonic;
	public static OIHandler oi;

	public static CameraServer server;
	public static Compressor compressor;
	public static ProximitySensor proximity;
	public static String vision;
	public static PCMMonitor pcmmonitor;
	public static DigitalInput HighLimitSwitch;
	public static DigitalInput LowLimitSwitch;
	public static DigitalOutput lighting1;
	public static DigitalOutput lighting2;
	public static ProximitySensor proximityBack;
	
	public static boolean matchStart = false;
	public static long millis;

	public static boolean lights = true;
	
	@Override
	public void robotInit() {
		difDrive = new DriveTrain();
		claw = new Claw();
		arm = new Arm();
		// ultrasonic = new UltrasonicSensor();
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.cameraLow);
		server.startAutomaticCapture(PortMap.cameraHigh);
		compressor = new Compressor(PortMap.pcm);
		compressor.setClosedLoopControl(true);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		oi = new OIHandler();
		lighting1 = new DigitalOutput(PortMap.lighting1);
		lighting2 = new DigitalOutput(PortMap.lighting2);
		lighting1.set(true);
		lighting2.set(true);

		SmartDashboard.putData("Calibrate Arm and Claw", new CalibrateArmClaw());
		SmartDashboard.putData("Calibrate Drive", new CalibrateDrive());
		SmartDashboard.putData("Replace Claw", new ReplaceClaw());
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		lighting1.set(false);
		lighting2.set(false);
		if (!matchStart) {
			millis = System.currentTimeMillis();
			matchStart = true;
		}
		int seconds = 150-(int)(System.currentTimeMillis()-millis)/1000;
		//SmartDashboard.putBoolean("Ultrasonic status:", ultrasonic.getStatus());
		//SmartDashboard.putNumber("Ultrasonic range:" , (int)ultrasonic.read());
		SmartDashboard.putString("Time Remaining:", seconds/60 + ":" + seconds%60);
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Ahead:", Double.toString((proximity.read())));
		//SmartDashboard.putBoolean("In Range:", proximity.read()<40 && proximity.read()>30); //numbers need testing
		SmartDashboard.putString("Claw:", claw.getState());
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if (!matchStart) {
			millis = System.currentTimeMillis();
			matchStart = true;
		}
		int seconds = 150-(int)(System.currentTimeMillis()-millis)/1000;
		//SmartDashboard.putBoolean("Ultrasonic status:", ultrasonic.getStatus());
		//SmartDashboard.putNumber("Ultrasonic range:" , (int)ultrasonic.read());
		SmartDashboard.putString("Time Remaining:", seconds/60 + ":" + seconds%60);
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Ahead:", Double.toString((proximity.read())));
		//SmartDashboard.putBoolean("In Range:", proximity.read()<40 && proximity.read()>30); //numbers need testing
		SmartDashboard.putString("Claw:", claw.getState());
		lighting1.set(false);
		lighting2.set(false);
	}
}
