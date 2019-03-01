package org.usfirst.frc.team7414.robot.Hardware;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
    private DigitalInput input;
    private boolean triggeredState;

    public LimitSwitch(int digitalChannel, boolean triggeredState) {
        input = new DigitalInput(digitalChannel);
        this.triggeredState = triggeredState;
    }

    public boolean isTriggered() {
        return triggeredState == input.get();
    }
}