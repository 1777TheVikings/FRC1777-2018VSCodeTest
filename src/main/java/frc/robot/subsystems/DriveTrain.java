/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDrive;

public class DriveTrain extends Subsystem {
  private static WPI_TalonSRX driveLeftFrontMotor = new WPI_TalonSRX(RobotMap.driveLeftFrontMotor);
  private static WPI_TalonSRX driveRightFrontMotor = new WPI_TalonSRX(RobotMap.driveRightFrontMotor);
  private static WPI_TalonSRX driveRightRearMotor = new WPI_TalonSRX(RobotMap.driveRightRearMotor);
  private static WPI_TalonSRX driveLeftRearMotor = new WPI_TalonSRX(RobotMap.driveLeftRearMotor);

  private static SpeedControllerGroup driveLeftSide = new SpeedControllerGroup(driveLeftFrontMotor, driveLeftRearMotor);
  private static SpeedControllerGroup driveRightSide = new SpeedControllerGroup(driveRightFrontMotor, driveRightRearMotor);

  private static DifferentialDrive drive = new DifferentialDrive(driveRightSide, driveLeftSide);

  private static DoubleSolenoid transmissionSolenoid = new DoubleSolenoid(RobotMap.transmissionSolenoid[0], RobotMap.transmissionSolenoid[1]);

  public DriveTrain() {
    driveLeftFrontMotor.configOpenloopRamp(0, 0);
    driveRightFrontMotor.configOpenloopRamp(0, 0);
    driveRightRearMotor.configOpenloopRamp(0, 0);
    driveLeftRearMotor.configOpenloopRamp(0, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TeleopDrive());
  }

  public void teleopDrive(double throttle, double rotation) {
    drive.arcadeDrive(throttle, rotation);
  }

  public void autoDrive(double left, double right) {
    drive.tankDrive(left, right);
  }

  public void fastTransmission() {
    transmissionSolenoid.set(Value.kReverse);
  }

  public void slowTransmission() {
    transmissionSolenoid.set(Value.kForward);
  }
}
