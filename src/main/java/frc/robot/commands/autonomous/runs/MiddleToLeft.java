/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.runs;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.components.*;

public class MiddleToLeft extends CommandGroup {
  /**
   * Add your docs here.
   */
  public MiddleToLeft() {
    addSequential(new Move(0.33, 1.0));
    addSequential(new Turn(-45.0));
    addSequential(new Move(0.33, 2.9));
    addSequential(new Turn(45.0));
    addSequential(new MoveToWall(0.33));
    addSequential(new Move(0.33, 0.25));
    addSequential(new LowerArm());
    addSequential(new DropCube());
  }
}
