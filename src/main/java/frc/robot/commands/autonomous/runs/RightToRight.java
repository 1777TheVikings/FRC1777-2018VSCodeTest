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
 * Places a cube in the right side of the switch from the right starting position.
 */
public class RightToRight extends CommandGroup {
  public RightToRight() {
    addSequential(new Move(0.5, 4.0));
    addSequential(new Turn(-90.0));
    addSequential(new MoveToWall(0.5));
    addSequential(new Move(0.33, 0.25));
    addSequential(new LowerArm());
    addSequential(new DropCube());
  }
}
