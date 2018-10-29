/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveToWall extends Command {
  private static final short impactThreshold = 2500;

  private double moveSpeed;
  private short prevAccelY = 0;
  private boolean impactDetected = false;

  public MoveToWall(double speed) {
    moveSpeed = speed;

    requires(Robot.driveTrain);
    setTimeout(4.0);  // failsafe
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.drive(-moveSpeed, 0.0);

    short[] accel = new short[3];
    Robot.pigeon.getBiasedAccelerometer(accel);
    System.out.println("Current accelerometer Y: " + String.valueOf(accel[1]));
    if ((accel[1] - prevAccelY) > impactThreshold) {
      System.out.println("Hit the wall!");
      impactDetected = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return impactDetected || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.drive(0.0, 0.0);
    System.out.println("Auto done in " + String.valueOf(Timer.getMatchTime()) + "s");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
