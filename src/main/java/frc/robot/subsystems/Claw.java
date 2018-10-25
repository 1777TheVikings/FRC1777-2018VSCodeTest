/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.TeleopClaw;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
  private static VictorSP leftWheels = new VictorSP(RobotMap.clawLeftMotor);
  private static VictorSP rightWheels = new VictorSP(RobotMap.clawRightMotor);
  private static DoubleSolenoid clawSolenoid = new DoubleSolenoid(RobotMap.clawSolenoid[0], RobotMap.clawSolenoid[1]);
  

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopClaw());
  }

  /**
   * Output is negative, intake is positive.
   */
  public void move(double speed) {
    leftWheels.set(speed);
    rightWheels.set(speed);
  }

  public void openClaw() {
    clawSolenoid.set(Value.kForward);
  }

  public void closeClaw() {
    clawSolenoid.set(Value.kReverse);
  }
}
