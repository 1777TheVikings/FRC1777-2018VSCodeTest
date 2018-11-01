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
 * Moves the robot linearly based on time. This uses a P loop and the Pigeon IMU to ensure
 * that the robot actually drives straight.
 */
public class Move extends Command {
  double speed;
  double moveTime;

  public Move(double speed, double time) {
    this.speed = speed;
    moveTime = time;
    setTimeout(moveTime);

    Robot.pigeon.setYaw(0.0, 1000);

    requires(Robot.driveTrain);
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

    double error = -1.0 * pigeon_out[0];
    double turn_speed = OI.kP * error;

    Robot.driveTrain.drive(-speed, turn_speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
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
