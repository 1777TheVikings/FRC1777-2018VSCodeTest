/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.utils.Configuration;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * All controls should be configurable using the Configuration class.
 */
public class OI {
  private static XboxController controller = new XboxController(RobotMap.controller);
  private static Configuration config = new Configuration("Default");

  public double getLeftY() {
    return controller.getY(config.getMovementHand()) * config.getThrottleMultiplier();
  }

  public double getLeftX() {
    return controller.getX(config.getMovementHand()) * config.getRotationMultiplier();
  }

  public boolean getTransmission() {
    return controller.getRawButton(config.getTransmissionButton());
  }

  public boolean getClawSolenoid() {
    return controller.getRawButton(config.getClawButton());
  }

  // TODO: don't hardcode this
  public double getClawWheels() {
    return controller.getRawAxis(3) - controller.getRawAxis(2);
  }
}
