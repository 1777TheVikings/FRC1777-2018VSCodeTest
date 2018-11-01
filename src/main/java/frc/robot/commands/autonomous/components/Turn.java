/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Turns the robot. This uses a P loop and a Pigeon IMU to ensure that the robot rotates
 * to the correct angle. We aren't expecting perfect precision (and we don't need it,
 * given our high error tolerance this year), but it should be good enough.
 */
public class Turn extends Command {
  double targetAngle;
  double error;
  boolean isFinished = false;

  public Turn(double angle) {
    targetAngle = angle;
    Robot.pigeon.setYaw(0.0, 1000);

    requires(Robot.driveTrain);
    setTimeout(4.0);  // failsafe
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.pigeon.setYaw(0.0, 1000);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double[] pigeon_out = new double[3];
    Robot.pigeon.getYawPitchRoll(pigeon_out);  // TODO: abstract this?
    error = targetAngle - pigeon_out[0];
    
    if (Math.abs(error) < OI.errorThreshold) {
      isFinished = true;
    } else {
      double turn_speed = OI.kP * error * 0.75;
      Robot.driveTrain.drive(0.0, turn_speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.drive(0.0, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
