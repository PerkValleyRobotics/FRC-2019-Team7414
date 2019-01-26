package org.usfirst.frc.team7414.robot.Subsystems;

import org.usfirst.frc.team7414.robot.PortMap;
import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class Claw extends Subsystem {

    public static String state = PortMap.clawOut;

    private static PWMVictorSPX clawMotor = new PWMVictorSPX(PortMap.claw); 

    public void pushOut() {

    }

    public void pullIn() {

    }

    @Override
    protected void initDefaultCommand() {
        
    }
}