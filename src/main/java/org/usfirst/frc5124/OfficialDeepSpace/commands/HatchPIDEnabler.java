/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HatchPIDEnabler extends Command {

  private boolean enabled;
  private boolean finished;

  public HatchPIDEnabler(boolean enabled) {
    this.enabled = enabled;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatch.setArmPidEnabled(enabled);
    finished = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
