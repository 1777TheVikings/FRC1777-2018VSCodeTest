package frc.utils.configuration;

import edu.wpi.first.wpilibj.GenericHID.Hand;


/**
 * The Configuration system allows individual drivers to save their own sensitivities and
 * button mappings, rather than relying on one person's preferences. This could be integrated
 * with a SendableChooser to easily change configs from the driver station.
 * 
 * New configurations can be created by subclassing Configuration and overriding individual
 * methods based on user preferences. See {@link AydanConfig} and {@link ColbyConfig} for examples.
 */
public class Configuration {
  public enum ButtonMode {
    /**
     * The button must be held for the action to occur.
     */
    HOLD,
    /**
     * The button's action can be toggled on and off.
     */
    TOGGLE
  }

  public String getUsername() {
    return "Default";
  }

  public Hand getMovementHand() {
    return Hand.kLeft;
  }

  public double getThrottleMultiplier() {
    return 1.0;
  }

  public double getRotationMultiplier() {
    return 1.0;
  }

  public int getTransmissionButton() {
    return 1;
  }

  public ButtonMode getTransmissionButtonMode() {
    return ButtonMode.HOLD;
  }

  public Hand getArmHand() {
    return Hand.kRight;
  }

  public double getArmMultiplier() {
    return 0.5;
  }

  public int getArmTransmissionButton() {
    return 4;
  }

  public ButtonMode getArmTransmissionButtonMode() {
    return ButtonMode.HOLD;
  }

  public Hand getClawIntakeTrigger() {
    return Hand.kLeft;
  }

  public Hand getClawOutputTrigger() {
    return Hand.kRight;
  }

  public int getClawOpenButton() {
    return 3;
  }

  public ButtonMode getClawOpenButtonMode() {
    return ButtonMode.HOLD;
  }
}