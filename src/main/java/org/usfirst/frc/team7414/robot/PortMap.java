package org.usfirst.frc.team7414.robot;

public class PortMap {

	//USB
	public static int joystick = 0;
	
	//Joystick Buttons
	public static int curvatureDrive = 1;
	public static int slowMode = 2;
	public static int clawToggle = 3;
	public static int straightDrive = 4;
	public static int pushPistonsToggle = 5;
	public static int liftPistonToggle = 6;
	public static int turnLeft = 9;
	public static int turnRight = 10;
	public static int moveLeft = 11;
	public static int moveRight = 12;

	//PWM
	public static int driveBackLeft = 0;
	public static int driveBackRight = 1;
	public static int driveFrontLeft = 2;
	public static int driveFrontRight = 3;
	public static int claw = 4;

	//Analog
	public static int proximitySensor = 0;
	public static int proximityBack = 1;

	//CAN Bus
	public static int pdp = 0;
	public static int pcm = 1;
	
	//PCM
	public static int liftSolenoid1 = 0;
	public static int liftSolenoid2 = 1;
	public static int pushSolenoid1 = 2;
	public static int pushSolenoid2 = 3;

	public static int camera = 0;
	
}
