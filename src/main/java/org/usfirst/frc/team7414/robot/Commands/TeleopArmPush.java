package org.usfirst.frc.team7414.robot.Commands;

import org.usfirst.frc.team7414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmPush extends Command {

    boolean finished = false;

    public TeleopArmPush(){
        requires(Robot.arm);
    }

    public boolean isFinished(){

        return finished;
    }

    public void execute(){
        Robot.arm.toggleArmPiston();
        finished = true;
    }
}