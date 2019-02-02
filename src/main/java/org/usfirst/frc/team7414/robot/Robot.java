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

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends TimedRobot {
	
	public static OIHandler oi = new OIHandler();
	public static DriveTrain difDrive = new DriveTrain();
	//public static Claw claw = new Claw();
	public static Arm arm = new Arm();
	
	public static CameraServer server;
	public static Compressor compressor;
	public static ProximitySensor proximity;
	public static String vision;

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;

	private VisionThread visionThread;
	private double centerX = 0.0;
	private RobotDrive drive;

	private final Object imgLock = new Object();
	
	@Override
	public void robotInit() {
		compressor = new Compressor(PortMap.compressor);
		compressor.setClosedLoopControl(true);
		proximity = new ProximitySensor(PortMap.proximitySensor);
		//server.getVideo();
		//server.putVideo(vision, 320, 240);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(PortMap.camera);
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		visionThread = new VisionThread(camera, new MyVisionPipeline(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (imgLock) {
					centerX = r.x + (r.width / 2);
				}
			}
		});
		visionThread.start();
			
		drive = new RobotDrive(1, 2);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	    //double proxDistance = proximity.read();
		//System.out.println(proxDistance);
		
		//*Checks the status of the compressors*//
		/*boolean enabled = compressor.enabled();
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
		System.out.print("Current: " + current);*/
	}
	@Override
	public void autonomousPeriodic() {
    	double centerX;
    	synchronized (imgLock) {
        	centerX = this.centerX;
		}
    	double turn = centerX - (IMG_WIDTH / 2);
    	drive.arcadeDrive(-0.6, turn * 0.005);
	}
}
