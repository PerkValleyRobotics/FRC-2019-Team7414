package org.usfirst.frc.team7414.robot.Hardware;

import org.usfirst.frc.team7414.robot.PortMap;

import edu.wpi.first.wpilibj.AnalogInput;

public class ProximitySensor {
    AnalogInput input;

    // channel: the analog channel the sensor is connected to
    public ProximitySensor(int channel) {
        input = new AnalogInput(channel);
    }

    /* Reads the distance of the nearest object from the sensor
     * using only one sample; less accurate than an average.
     * Note that the distance is measured in CENTIMETERS (cm) */
    public double read() {
        return input.getValue() / 8.0;
        /* For our sensor, a voltage step of 1/512 is equal to a
         * distance of 1 cm but our ADC records 4096 steps, not 512  */
    }
}