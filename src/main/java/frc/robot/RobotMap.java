/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // Talon SRX motors
  public static int driveLeftFrontMotor = 1;
  public static int driveRightFrontMotor = 2;
  public static int driveRightRearMotor = 3;
  public static int driveLeftRearMotor = 4;

  // Victor SP motors
  public static int armRightMotor = 0;
  public static int armLeftMotor = 1;
  public static int clawLeftMotor = 2;
  public static int clawRightMotor = 3;


  // CAN bus sensors
  public static int pigeon = 0;

  // DIO sensors
  public static int distanceSensor = 1;


  // Pneumatics
  // DoubleSolenoid values should be int[2] {forward channel, reverse channel}
  public static int compressor = 0;
  public static int[] transmissionSolenoid = {0, 1};
  public static int[] armSolenoid = {2, 3};
  public static int[] clawSolenoid = {4, 5};


  // Joysticks
  public static int controller = 0;
}
