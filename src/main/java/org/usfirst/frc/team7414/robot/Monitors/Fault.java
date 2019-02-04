package org.usfirst.frc.team7414.robot.Monitors;

public class Fault {
    private FaultType type;
    private String message;

    public Fault(FaultType type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * @return the Fault Type
     */
    public FaultType getType() {
        return type;
    }

    /**
     * @return the Fault internal Message
     */
    public String getMessage() {
        return message;
    }
}