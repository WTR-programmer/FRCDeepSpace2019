/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReleaseHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ReleaseHatch() {
    addSequential(new HatchClaws(false));
    addSequential(new HatchArmPower(0.1, true));
    addSequential(new Auto_Wait(0.25));
    addSequential(new HatchShoot(true, false));
  }

  @Override
  protected void interrupted() {
    Robot.hatch.launchHatch(false);
    Robot.hatch.setArm(0);
  }
}
