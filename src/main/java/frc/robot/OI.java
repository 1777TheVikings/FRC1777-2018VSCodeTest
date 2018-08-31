/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;
import frc.utils.Configuration.ButtonMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * All controls should be configurable using the Configuration class.
 */
public class OI {
  private static XboxController controller = new XboxController(RobotMap.controller);

  private boolean isTransmissionToggledOn = false;

  public double getLeftY() {
    return controller.getY(Robot.configManager.getCurrentConfiguration().getMovementHand()) *
                           Robot.configManager.getCurrentConfiguration().getThrottleMultiplier();
  }

  public double getLeftX() {
    return controller.getX(Robot.configManager.getCurrentConfiguration().getMovementHand()) *
                           Robot.configManager.getCurrentConfiguration().getRotationMultiplier();
  }

  public boolean getTransmission() {
    if (Robot.configManager.getCurrentConfiguration().getTransmissionButtonMode() == ButtonMode.HOLD) {
      return controller.getRawButton(Robot.configManager.getCurrentConfiguration().getTransmissionButton());
    } else {  // ButtonMode.TOGGLE
      if (controller.getRawButtonPressed(Robot.configManager.getCurrentConfiguration().getTransmissionButton())) {
        isTransmissionToggledOn = !isTransmissionToggledOn;
      }
      return isTransmissionToggledOn;
    }
  }
}
