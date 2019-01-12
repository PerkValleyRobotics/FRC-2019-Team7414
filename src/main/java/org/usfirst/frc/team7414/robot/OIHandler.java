package org.usfirst.frc.team7414.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OIHandler {
	
	private static int forkliftPosition = 0;
	
	public static Joystick joystick1 = new Joystick(PortMap.joystick);
	
	public double getY() {
		return -1 * joystick1.getY();
		//forward is positive, backward is negative
	}
	
	public double getX() {
		return joystick1.getX();
		//right is positive, left is negative
	}
	
	public double getZ() {
		return joystick1.getZ();
	}
	
	public double getThrottle() {
		return joystick1.getThrottle();
	}
	
	public double getTwist() {
		return joystick1.getTwist();
	}
	
	public boolean triggerPressed() {
		return joystick1.getTrigger();	
	}
}
