package org.usfirst.frc.team7414.robot.Monitors;

import java.util.List;

public abstract class FaultMonitor {
    public abstract List<Fault> getFaults();
}