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
import frc.robot.RobotMap;
import frc.robot.commands.TeleopArm;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  private static VictorSP leftVictor = new VictorSP(RobotMap.armLeftMotor);
  private static VictorSP rightVictor = new VictorSP(RobotMap.armRightMotor);
  private static DoubleSolenoid gearShiftSolenoid = new DoubleSolenoid(RobotMap.armSolenoid[0], RobotMap.armSolenoid[1]);
  

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopArm());
  }

  public void move(double speed) {
    leftVictor.set(-speed);
    rightVictor.set(speed);
  }

  public void fastGear() {
    gearShiftSolenoid.set(Value.kReverse);
  }

  public void slowGear() {
    gearShiftSolenoid.set(Value.kForward);
  }
}
