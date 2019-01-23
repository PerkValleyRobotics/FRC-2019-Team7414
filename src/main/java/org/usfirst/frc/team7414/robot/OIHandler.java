package org.usfirst.frc.team7414.robot;

import org.usfirst.frc.team7414.robot.Hardware.ProximitySensor;

import edu.wpi.first.wpilibj.Joystick;

public class OIHandler {
	
	public static Joystick joystick1 = new Joystick(PortMap.joystick);
	public static ProximitySensor proximitySensor1 = new ProximitySensor(PortMap.proximitySensor);
	
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

	public boolean getTrigger() {
		return joystick1.getTrigger();
	}

	public boolean getMissile() {
		return joystick1.getRawButton(2);
	}

	public boolean getButton(int button) {
		return joystick1.getRawButton(button);
	}
}
