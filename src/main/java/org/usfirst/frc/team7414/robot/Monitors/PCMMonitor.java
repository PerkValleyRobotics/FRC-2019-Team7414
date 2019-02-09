package org.usfirst.frc.team7414.robot.Monitors;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SolenoidBase;

import java.util.List;
import java.util.ArrayList;

/* This class is abstract because it it does not work!
 * Calling SolenoidBase results in this: ERROR 1015 HAL: 
 * Incompatible State: The operation cannot be completed 
 * PCMMonitor.getFaults(PCMMonitor.java:20) 
 * I have read somewhere that this is a bug in wpilib */
public abstract class PCMMonitor extends FaultMonitor {
    private int moduleNumber;
    private Compressor compressor;

    public PCMMonitor(int moduleNumber) {
        this.compressor = new Compressor(moduleNumber);
    }

    public List<Fault> getFaults() {
        List<Fault> faults = new ArrayList<>();

        int sBlackList = SolenoidBase.getPCMSolenoidBlackList(moduleNumber);
        // I found this somewhere--I think it evaluates each bit in an int as a boolean
        for (int i = 7; i >= 0; i--) {
            if ((sBlackList & (1 << i)) != 0) {
                faults.add(new Fault(FaultType.SOLENOIDFAULT, 
                    "There may be a voltage short on solenoid " + i + "."));
            }
        }

        if (compressor.getCompressorCurrentTooHighStickyFault()) {
            faults.add(new Fault(FaultType.COMPRESSORFAULT, "The compressor may have drawn too much current."));
        }
        if (compressor.getCompressorNotConnectedStickyFault()) {
            faults.add(new Fault(FaultType.COMPRESSORFAULT, "The compressor may be disconnected."));
        }
        if (compressor.getCompressorShortedStickyFault()) {
            faults.add(new Fault(FaultType.COMPRESSORFAULT, "The compressor may have a voltage short."));
        }

        return faults;
    }
}