/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;

public class CatapultShoot extends Command {
  
  private Supplier<Boolean> positionSupplier;

  public CatapultShoot(boolean position) {
    this(() -> position);
  }

  public CatapultShoot(Supplier<Boolean> positionSupplier) {
    this.positionSupplier = positionSupplier;
  }
 
  public CatapultShoot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.intake.getIntakeDeployed()) {
      Robot.catapult.launchCatapult(positionSupplier.get());
    }
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
    Robot.catapult.launchCatapult(false);
  }
}