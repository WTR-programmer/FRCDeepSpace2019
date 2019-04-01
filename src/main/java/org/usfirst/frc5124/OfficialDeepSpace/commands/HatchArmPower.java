package org.usfirst.frc5124.OfficialDeepSpace.commands;

import java.util.function.Supplier;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HatchArmPower extends Command {

  private Supplier<Double> powerSupplier;
  private boolean finished;

  public HatchArmPower(Supplier<Double> powerSupplier) {
    this(powerSupplier, false);
  }

  public HatchArmPower(double power) {
    this(() -> power);
  }

  public HatchArmPower(Supplier<Double> powerSupplier, boolean killImmediately) {
    this.powerSupplier = powerSupplier;
    requires(Robot.hatch);
    finished = killImmediately;
  }

  public HatchArmPower(double power, boolean killImmediately) {
    this(() -> power, killImmediately);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hatch.setArmPidEnabled(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatch.setArm(powerSupplier.get());
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
    Robot.hatch.setArm(0);
  }
}