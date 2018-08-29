package frc.utils;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Sensor class for the MaxBotix HRLV-MaxSonar-EZ1 (MB1013) ultrasonic sensor. The sensor should be wired for
 * analog output, and the PWM header should be connected to an analog input port on the RoboRIO.
 */
public class MB1013 {
    /**
     * The number of centimeters away from the sensor per volt. Distances below 30cm are recorded as 30cm.
     */
    private static final double CM_PER_VOLT = 94;

    private final AnalogInput analogInput;

    /**
     * Creates an MB1013 object connected to the specified analog input port.
     * 
     * @param channel The analog input channel that the sensor is connected to.
     */
    public MB1013(int channel) {
        this.analogInput = new AnalogInput(channel);
    }

    /**
     * Returns the distance reported by from the sensor, in centimeters. Distances below 30cm are recorded as
     * approximately 30cm.
     * 
     * @return The distance reported by the sensor.
     */
    public double getDistance() {
        return analogInput.getVoltage() * CM_PER_VOLT;
    }
}