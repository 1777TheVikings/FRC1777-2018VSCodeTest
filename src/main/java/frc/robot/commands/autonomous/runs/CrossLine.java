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
 * Crosses the auto line from any starting position.
 */
public class CrossLine extends CommandGroup {
  public CrossLine() {
    addSequential(new Move(0.5, 3.0));
  }
}
