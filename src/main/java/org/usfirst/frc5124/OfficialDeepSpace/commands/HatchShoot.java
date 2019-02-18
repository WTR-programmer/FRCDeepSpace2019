/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace.commands;

import java.util.function.Supplier;
import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HatchShoot extends Command {

  private Supplier<Boolean> positionSupplier;
  // object that gives out doubles when asked for
  // to have position change on the fly after command
  // is created, implement supplier with the desired
  // position returned.

  public HatchShoot(boolean position) {
    this(() -> position);
  }

  public HatchShoot(Supplier<Boolean> positionSupplier) {
    this.positionSupplier = positionSupplier;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatch.launchHatch(positionSupplier.get());
    // Robot.hatch.setArm(.1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.hatch.launchHatch(false);
  }
}
