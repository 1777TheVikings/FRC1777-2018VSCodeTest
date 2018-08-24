/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopClaw;

public class Claw extends Subsystem {
  private static TalonSRX leftMotor = new TalonSRX(RobotMap.clawLeftMotor);
  private static TalonSRX rightMotor = new TalonSRX(RobotMap.clawRightMotor);

  private static DoubleSolenoid solenoid = new DoubleSolenoid(RobotMap.clawSolenoid[0], RobotMap.clawSolenoid[1]);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopClaw());
  }

  public void teleopControl() {
    controlIntake(Robot.oi.getClawWheels());
    controlSolenoid(Robot.oi.getClawSolenoid());
  }

  /**
   * Control the speed of the intake.
   * 
   * @param speed The speed of the intake. Positive numbers will suck in cubes.
   */
  public void controlIntake(double speed) {
    leftMotor.set(ControlMode.PercentOutput, speed);
    rightMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Control the opening and closing of the claw.
   * 
   * @param open If true, the claw will be open. Otherwise, the claw will close.
   */
  public void controlSolenoid(boolean open) {
    solenoid.set(open ? Value.kReverse : Value.kForward);
  }
}
