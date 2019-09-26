package org.usfirst.frc.team7414.robot;

public class PortMap {

	//USB
	public static int joystick = 0;
	public static int controller = 1;
	
	//Joystick Buttons
	public static int curvatureDrive = 1;
	public static int slowMode = 2;
	public static int clawToggle = 3;
	public static int straightDrive = 4;
	public static int pushPistonsToggle = 5;
	public static int liftPistonToggle = 6;
	public static int straightBack = 7;
	public static int turnLeft = 11;
	public static int turnRight = 12;

	//PWM
	public static int driveBackLeft = 0;
	public static int driveBackRight = 1;
	public static int driveFrontLeft = 2;
	public static int driveFrontRight = 3;
	public static int claw = 5;
	public static int lift1 = 6;
	public static int lift2 = 7;
	public static int liftDrive1 = 8;
	public static int liftDrive2 = 9;

	//Analog
	public static int proximitySensor = 0;
	public static int proximityBack = 1;

	//DIO
	public static int lighting1 = 3;
	public static int lighting2 = 4;
	public static int liftEncoder1 = 1;
	public static int liftEncoder2 = 2;
	
	//CAN Bus
	public static int pdp = 0;
	public static int pcm = 1;
	
	//PCM
	public static int liftSolenoid1 = 1;
	public static int liftSolenoid2 = 0;
	public static int pushSolenoid1 = 3;
	public static int pushSolenoid2 = 2;

	//cameras
	public static int cameraLow = 1;
	public static int cameraHigh = 0;
	
}
