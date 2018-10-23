package frc.utils.configuration;

import edu.wpi.first.wpilibj.GenericHID.Hand;


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

  public String username = "Default";
  public Hand movementHand = Hand.kLeft;
  public double throttleMultiplier = 1.0;
  public double rotationMultiplier = 1.0;
  public int transmissionButton = 0;
  public ButtonMode transmissionButtonMode = ButtonMode.HOLD;
  public Hand armHand = Hand.kRight;
  public int armTransmissionButton = 3;
  public ButtonMode armTransmissionButtonMode = ButtonMode.HOLD;
  public Hand clawIntakeTrigger = Hand.kLeft;
  public Hand clawOutputTrigger = Hand.kRight;
  public int clawOpenButton = 2;
  public ButtonMode clawOpenButtonMode = ButtonMode.HOLD;

  public Configuration() {
  }

  public Configuration(String username) {
    this.username = username;
  }
}