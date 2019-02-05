package org.usfirst.frc.team7414.robot.Monitors;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class PDPMonitor extends FaultMonitor {
    private PowerDistributionPanel pdp;
    private PDPConstraints cts;

    public PDPMonitor(int moduleNumber, PDPConstraints constraints) {
        this.pdp = new PowerDistributionPanel(moduleNumber);
        this.cts = constraints;
    }

    public List<Fault> getFaults() {
        List<Fault> faults = new ArrayList<>();
        Optional<Fault> f;

        // Per Channel Current
        for(int i = 0; i < PDPConstraints.CHANNELS; i++) {
            f = cts.getFault(i, pdp.getCurrent(i),
                new Fault(FaultType.UNDERCURRENTFAULT,
                "PDP port " + i + " is drawing too little current."), 
                new Fault(FaultType.OVERCURRENTFAULT,
                "PDP port " + i + " is drawing too much current."));
            if(f.isPresent()) faults.add(f.get());
        }

        // Temperature
        f = cts.getFault(PDPConstraints.TEMPERATURE, pdp.getTemperature(),
                new Fault(FaultType.TEMPERATUREFAULT,
                "The PDP temperature is too low."),
                new Fault(FaultType.TEMPERATUREFAULT,
                "The PDP temperature is too high."));
        if(f.isPresent()) faults.add(f.get());

        // Total Current
        f = cts.getFault(PDPConstraints.TOTALCURRENT, pdp.getTotalCurrent(),
                new Fault(FaultType.UNDERCURRENTFAULT,
                "The PDP is passing too little total current."),
                new Fault(FaultType.OVERCURRENTFAULT,
                "The PDP is passing too much total current."));
        if(f.isPresent()) faults.add(f.get());

        // Input Voltage
        f = cts.getFault(PDPConstraints.INPUTVOLTAGE, pdp.getVoltage(),
                new Fault(FaultType.UNDERVOLTAGEFAULT,
                "The PDP input voltage is too low."),
                new Fault(FaultType.OVERVOLTAGEFAULT,
                "The PDP input voltage is too high."));
        if(f.isPresent()) faults.add(f.get());

        return faults;
    }
}