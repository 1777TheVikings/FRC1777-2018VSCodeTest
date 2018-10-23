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
    return controller.getY(Robot.configManager.getSelectedConfiguration().movementHand) *
                           Robot.configManager.getSelectedConfiguration().throttleMultiplier;
  }

  public double getLeftX() {
    return controller.getX(Robot.configManager.getSelectedConfiguration().movementHand) *
                           Robot.configManager.getSelectedConfiguration().rotationMultiplier;
  }

  public boolean getTransmission() {
    if (Robot.configManager.getSelectedConfiguration().transmissionButtonMode == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getSelectedConfiguration().transmissionButton);
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getSelectedConfiguration().transmissionButton)) {
        isTransmissionToggledOn = !isTransmissionToggledOn;
      }
      return isTransmissionToggledOn;
    }
  }

  public double getArm() {
    return controller.getY(Robot.configManager.getSelectedConfiguration().armHand);
  }

  public boolean getArmTransmission() {
    if (Robot.configManager.getSelectedConfiguration().armTransmissionButtonMode == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getSelectedConfiguration().armTransmissionButton);
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getSelectedConfiguration().armTransmissionButton)) {
        isArmTransmissionToggledOn = !isArmTransmissionToggledOn;
      }
      return isTransmissionToggledOn;
    }
  }

  public double getClaw() {
    return controller.getTriggerAxis(Robot.configManager.getSelectedConfiguration().clawIntakeTrigger) -
           controller.getTriggerAxis(Robot.configManager.getSelectedConfiguration().clawOutputTrigger);
  }

  public boolean getClawOpen() {
    if (Robot.configManager.getSelectedConfiguration().clawOpenButtonMode == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getSelectedConfiguration().clawOpenButton);
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getSelectedConfiguration().clawOpenButton)) {
        isClawToggledOpen = !isClawToggledOpen;
      }
      return isClawToggledOpen;
    }
  }
}
