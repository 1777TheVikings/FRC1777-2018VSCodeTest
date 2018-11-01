/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.runs;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.components.*;

/**
 * Places a cube in the right side of the switch when starting from the left side. This
 * hasn't been tested, but "it should work".
 */
public class LeftToRight extends CommandGroup {
  public LeftToRight() {
    addSequential(new Move(0.5, 5.2));
    addSequential(new Turn(90.0));
    addSequential(new Move(0.5, 5.5));
    addSequential(new Turn(90.0));
    addSequential(new Move(0.5, 1.2));
    addSequential(new Turn(90.0));
    addSequential(new MoveToWall(0.5));
    addSequential(new Move(0.33, 0.25));
    addSequential(new LowerArm());
    addSequential(new DropCube());
  }
}
