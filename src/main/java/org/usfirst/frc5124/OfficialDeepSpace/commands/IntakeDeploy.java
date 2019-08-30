package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeDeploy extends Command {

  private boolean position;
  private boolean finished;
  private boolean interrupt;

  public IntakeDeploy (boolean position) {
    this.position = position;
    interrupt = false;
  }

  public IntakeDeploy () {
    interrupt = true;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (interrupt) {
      Robot.intake.setIntakeDeployed(true);
      return;
    }
    if (!Robot.catapult.getCatapultIsUp()) {
      Robot.intake.setIntakeDeployed(position);
      finished = true;
    } else {
      finished = position;
    }
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
    if (interrupt) {
      Robot.intake.setIntakeDeployed(false);
    }
  }
}