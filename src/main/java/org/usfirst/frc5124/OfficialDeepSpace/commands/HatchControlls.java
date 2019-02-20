/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class HatchControlls extends Command {
  public HatchControlls() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires (Robot.hatch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //  //Move Hatch down normally
    //  HatchMove armDownCmd = new HatchMove(.6);
    //  if (Robot.oi.getoperator().getPOV() == 90){
    //      armDownCmd.start(); }
    //  else armDownCmd.cancel();

     

    //  //Move Hatch Up Normally
    //  HatchMove armUpCmd = new HatchMove(-.4);
    //  if (Robot.oi.getoperator().getPOV() == 270){
    //      armUpCmd.start(); }
    //  else armUpCmd.cancel();

     
     
    //  //Mega Down
    //  HatchMove megaDownCmd = new HatchMove(1.0);
    //  if (Robot.oi.getoperator().getTriggerAxis(Hand.kRight) > .15){
    //      megaDownCmd.start(); }
    //  else megaDownCmd.cancel();

    //  //Mega Up
    //  HatchMove megaUpCmd = new HatchMove(-.6);
    //  if (Robot.oi.getoperator().getTriggerAxis(Hand.kLeft) > .15){
    //      megaUpCmd.start(); }
    //  else megaUpCmd.cancel();

// alternative 
if (Robot.oi.getOperator().getPOV() == 90) {
  Robot.hatch.setArm(.6);
}
else if (Robot.oi.getOperator().getPOV() == 270){
  Robot.hatch.setArm(-.4);
}
else if (Robot.oi.getOperator().getTriggerAxis(Hand.kLeft) > .15){
  Robot.hatch.setArm(-.6);
}
else if (Robot.oi.getOperator().getTriggerAxis(Hand.kRight) > .15) {
  Robot.hatch.setArm(1);
}
else Robot.hatch.setArm(0);

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
  }
}
