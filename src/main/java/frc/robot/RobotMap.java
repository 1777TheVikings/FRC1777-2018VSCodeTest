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
  public static int driveLeftFrontMotor = 3;
  public static int driveRightFrontMotor = 2;
  public static int driveRightRearMotor = 1;
  public static int driveLeftRearMotor = 4;

  // Pneumatics
  // DoubleSolenoid values should be int[2] {forward channel, reverse channel}
  public static int compressor = 0;
  public static int[] transmissionSolenoid = {0, 1};
  
  // 

  // Joysticks
  public static int controller = 0;
}
