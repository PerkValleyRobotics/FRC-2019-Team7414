package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Commands.TeleopArmPush;
import org.usfirst.frc.team7414.robot.Commands.TeleopArmPushDown;
import org.usfirst.frc.team7414.robot.Commands.TeleopArmPushOut;
import org.usfirst.frc.team7414.robot.Commands.TeleopArmPushUp;
import org.usfirst.frc.team7414.robot.Commands.TeleopArmLift;
import org.usfirst.frc.team7414.robot.Commands.TeleopTurnLeft;
import org.usfirst.frc.team7414.robot.Commands.TeleopTurnRight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class OIHandler {

	public static Joystick joystick1;
	public static XboxController controller;

	public static JoystickButton liftArmButton;
	public static JoystickButton pushArmButton;
	public static JoystickButton moveLeftButton;
	public static JoystickButton moveRightButton;
	public static JoystickButton clawButton;
	public static JoystickButton turnRightButton;
	public static JoystickButton turnLeftButton;

	public static Button clawButton1;
	public static Button Dpad;

	public OIHandler() {
		//initalize controllers - joystick and xbox controller
		joystick1 = new Joystick(PortMap.joystick);
		controller = new XboxController(PortMap.controller);
		//this does not actually need to be connected
		
		//initalize joystick buttons and assign them to activate various commands
		liftArmButton = new JoystickButton(joystick1, PortMap.liftPistonToggle);
		pushArmButton = new JoystickButton(joystick1, PortMap.pushPistonsToggle);
		turnRightButton = new JoystickButton(joystick1, PortMap.turnRight);
		turnLeftButton = new JoystickButton(joystick1, PortMap.turnLeft);
		liftArmButton.whenPressed(new TeleopArmLift());
		pushArmButton.whenPressed(new TeleopArmPush());
		turnRightButton.whenPressed(new TeleopTurnRight());
		turnLeftButton.whenPressed(new TeleopTurnLeft());
	}

	//forwards and backwards
	public double getY() {
		return -1 * joystick1.getY();
		//forward is positive, backward is negative
	}
	
	//right and left
	public double getX() {
		return joystick1.getX();
		//right is positive, left is negative
	}

	//true is pressed, false is not pressed
	public boolean getTrigger() {
		return joystick1.getTrigger();
	}

	//true is pressed, false is not pressed
	public boolean getMissile() {
		return joystick1.getRawButton(PortMap.slowMode);
	}

	//true is pressed, false is not pressed
	public boolean getButton(int button) {
		return joystick1.getRawButton(button);
	}

	public boolean getButtonPressed(int button) {
		return joystick1.getRawButtonPressed(button);
	}

	public boolean getControllerButtonPressed(int button) {
		return controller.getRawButtonPressed(button);
	}

	public int getDpadValue() {
		return controller.getPOV();
	}

	public double getLeftXPadValue() {
		return controller.getRawAxis(0);
	}

	public double getLeftYPadValue() {
		return -1*controller.getRawAxis(1);
	}
}
