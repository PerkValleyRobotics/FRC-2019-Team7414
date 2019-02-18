package org.usfirst.frc.team7414.robot.Hardware;

import org.usfirst.frc.team7414.robot.PortMap;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSensor {

    private Ultrasonic sensor;
    
    public UltrasonicSensor() {
        sensor = new Ultrasonic(PortMap.ultrasonicPing, PortMap.ultrasonicEcho);
        sensor.setAutomaticMode(true);
    }

    public double read() {
        //sensor.ping();
        return sensor.getRangeInches();
    }

    public boolean getStatus() {
        return sensor.isEnabled();
    }
}