/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Lowers the robot's arm. Our robot's arm must start pointing directly upwards to stay within
 * the frame perimeter, so it has to be lowered before the cube can be placed in the switch.
 */
public class LowerArm extends Command {
  public LowerArm() {
    requires(Robot.arm);
    setTimeout(0.85);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.arm.move(0.23);  // joystick Y axis is inverted, so this makes it go down
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.move(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
