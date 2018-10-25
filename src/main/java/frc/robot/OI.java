/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;
import frc.utils.configuration.Configuration.ButtonMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * All controls should be configurable using the Configuration class.
 */
public class OI {
  private static XboxController controller = new XboxController(RobotMap.controller);

  public static double kP = 0.013;
  public static double errorThreshold = 2.0;

  private boolean isTransmissionToggledOn = false;
  private boolean isArmTransmissionToggledOn = false;
  private boolean isClawToggledOpen = false;

  public double getLeftY() {
    return controller.getY(Robot.configManager.getSelectedConfiguration().getMovementHand()) *
                           Robot.configManager.getSelectedConfiguration().getThrottleMultiplier();
  }

  public double getLeftX() {
    return controller.getX(Robot.configManager.getSelectedConfiguration().getMovementHand()) *
                           Robot.configManager.getSelectedConfiguration().getRotationMultiplier();
  }

  public boolean getTransmission() {
    if (Robot.configManager.getSelectedConfiguration().getTransmissionButtonMode() == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getSelectedConfiguration().getTransmissionButton());
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getSelectedConfiguration().getTransmissionButton())) {
        isTransmissionToggledOn = !isTransmissionToggledOn;
      }
      return isTransmissionToggledOn;
    }
  }

  public double getArm() {
    return controller.getY(Robot.configManager.getSelectedConfiguration().getArmHand()) *
                           Robot.configManager.getSelectedConfiguration().getArmMultiplier();
  }

  public boolean getArmTransmission() {
    if (Robot.configManager.getSelectedConfiguration().getArmTransmissionButtonMode() == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getSelectedConfiguration().getArmTransmissionButton());
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getSelectedConfiguration().getArmTransmissionButton())) {
        isArmTransmissionToggledOn = !isArmTransmissionToggledOn;
      }
      return isTransmissionToggledOn;
    }
  }

  public double getClaw() {
    return controller.getTriggerAxis(Robot.configManager.getSelectedConfiguration().getClawIntakeTrigger()) -
           controller.getTriggerAxis(Robot.configManager.getSelectedConfiguration().getClawOutputTrigger());
  }

  public boolean getClawOpen() {
    if (Robot.configManager.getSelectedConfiguration().getClawOpenButtonMode() == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getSelectedConfiguration().getClawOpenButton());
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getSelectedConfiguration().getClawOpenButton())) {
        isClawToggledOpen = !isClawToggledOpen;
      }
      return isClawToggledOpen;
    }
  }
}
