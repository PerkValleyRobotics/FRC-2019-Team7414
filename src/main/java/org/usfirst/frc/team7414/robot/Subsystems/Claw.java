package org.usfirst.frc.team7414.robot.Subsystems;


import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {


    public static String state = PortMap.clawOut;

    private static PWMVictorSPX clawMotor = new PWMVictorSPX(PortMap.claw); 

    public void pushOut() {
        //clawMotor.set(1); // change value as necessary
    }

    public void pullIn() {
        //clawMotor.set(-1); // change value as necessary
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}