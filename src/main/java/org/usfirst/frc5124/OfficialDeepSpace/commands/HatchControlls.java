package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class HatchControlls extends Command {
  public HatchControlls() {
    requires (Robot.hatch);
  }

  @Override
  protected void initialize() {
  }

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

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
