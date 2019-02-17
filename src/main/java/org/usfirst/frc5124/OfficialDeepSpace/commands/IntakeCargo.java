package org.usfirst.frc5124.OfficialDeepSpace.commands;

import java.util.function.Supplier;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCargo extends Command {

  private Supplier<Double> powerSupplier;
  // object that gives out doubles when asked for
  // to have power change on the fly after command
  // is created, implement supplier with the desired
  // power returned.

  public IntakeCargo(double power) {
    this(() -> power);
  }

  //This.powerSupplier referes to the outside power supplier
  public IntakeCargo(Supplier<Double> powerSupplier) {
    this.powerSupplier = powerSupplier;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intake.powerIntake(powerSupplier.get());
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
    Robot.intake.powerIntake(0);
  }
}