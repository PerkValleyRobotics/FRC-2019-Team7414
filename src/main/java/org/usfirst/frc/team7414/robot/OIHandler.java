package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Commands.TeleopArmPush;
import org.usfirst.frc.team7414.robot.Commands.TeleopArmLift;
import org.usfirst.frc.team7414.robot.Commands.TeleopMoveLeft;
import org.usfirst.frc.team7414.robot.Commands.TeleopMoveRight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OIHandler {
	
	public static Joystick joystick1;

	public static JoystickButton liftArmButton;
	public static JoystickButton pushArmButton;
	public static JoystickButton moveLeftButton;
	public static JoystickButton moveRightButton;

	public OIHandler() {
		joystick1 = new Joystick(PortMap.joystick);
		liftArmButton = new JoystickButton(joystick1, PortMap.liftPistonToggle);
		pushArmButton = new JoystickButton(joystick1, PortMap.pushPistonsToggle);
		moveLeftButton = new JoystickButton(joystick1, PortMap.moveLeft);
		moveRightButton = new JoystickButton(joystick1, PortMap.moveRight);

		liftArmButton.whenPressed(new TeleopArmLift());
		pushArmButton.whenPressed(new TeleopArmPush());
		moveLeftButton.whenPressed(new TeleopMoveLeft());
		moveRightButton.whenPressed(new TeleopMoveRight());
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
	
	//twist
	public double getZ() {
		return joystick1.getZ();
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
}
