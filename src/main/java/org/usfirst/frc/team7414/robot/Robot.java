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
	public static Climber climb;
	public static ClimberDrive climbDrive;
	public static OIHandler oi;

	public static CameraServer server;
	public static Compressor compressor;
	public static String vision;
	public static DigitalOutput lighting1;
	public static DigitalOutput lighting2;
	
	@Override
	public void robotInit() {
		//intialize all of the subsystems
		difDrive = new DriveTrain();
		claw = new Claw();
		arm = new Arm();
		//climb = new Climber();
		//climbDrive = new ClimberDrive();
		
		//start cameras
		server = CameraServer.getInstance();
		server.startAutomaticCapture(PortMap.cameraLow);
		server.startAutomaticCapture(PortMap.cameraHigh);
		//start compressor
		compressor = new Compressor(PortMap.pcm);
		compressor.setClosedLoopControl(true);

		//Initialize OI last
		oi = new OIHandler();

		//turn on pretty lights
		lighting1 = new DigitalOutput(PortMap.lighting1);
		lighting2 = new DigitalOutput(PortMap.lighting2);
		lighting1.set(true);
		lighting2.set(true);

		//SmartDashboard.putData("Calibrate Arm and Claw", new CalibrateArmClaw());
		//SmartDashboard.putData("Calibrate Drive", new CalibrateDrive());
	}

	@Override
	public void teleopPeriodic() {
		//run the scheduler every cycle
		Scheduler.getInstance().run();
		
		lighting1.set(false);
		lighting2.set(false);

		//output data to SmartDashboard
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Claw:", claw.getState());
	}

	@Override
	public void autonomousPeriodic() {
		//run the scheduler every cycle - We use Teleop Sandstorm, so the scheduler is the same
		Scheduler.getInstance().run();
		
		//output data to SmartDashboard
		SmartDashboard.putBoolean("Compressor enabled:", compressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch:", compressor.getPressureSwitchValue());
		SmartDashboard.putString("Current:", Double.toString((double)((int)(compressor.getCompressorCurrent()*100))/100));
		SmartDashboard.putString("Claw:", claw.getState());
		lighting1.set(false);
		lighting2.set(false);
	}
}
