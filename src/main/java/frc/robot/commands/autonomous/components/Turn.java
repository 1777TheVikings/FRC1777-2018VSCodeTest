/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.components;

import java.sql.Time;
import java.util.Timer;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class Turn extends Command {
  double targetAngle;
  double error;
  boolean isFinished = false;

  public Turn(double angle) {
    targetAngle = angle;
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
    error = targetAngle - pigeon_out[0];

    System.out.println("-----");
    System.out.println("Yaw: " + String.valueOf(pigeon_out[0]));
    System.out.println("Error: " + String.valueOf(error));
    System.out.println("Below threshold?: " + String.valueOf(error < OI.errorThreshold));

    if (Math.abs(error) < OI.errorThreshold) {
      isFinished = true;
    } else {
      double turn_speed = OI.kP * error;
      System.out.println("Output value: " + String.valueOf(turn_speed));
      // if (turn_speed < -0.15) {
      //   System.out.println("Value too low!");
      //   turn_speed = -0.15;
      // } else if (turn_speed > 0.25) {
      //   System.out.println("Value too high!");
      //   turn_speed = 0.15;
      // }
      Robot.driveTrain.drive(0.0, turn_speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.drive(0.0, 0.0);
    System.out.println("Finished!");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
