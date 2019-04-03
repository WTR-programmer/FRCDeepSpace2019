package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.Hatch;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sub_Hatch extends HatchArmPower {

  public Sub_Hatch() {
    //this requires tuned PID
    super(() -> {
      double right = Hatch.deadZone(Robot.oi.getOperator().getTriggerAxis(Hand.kRight));
      double left = Hatch.deadZone(Robot.oi.getOperator().getTriggerAxis(Hand.kLeft));
      double change =  0.01 * Hatch.deadZone(right - left);
      return Robot.hatch.getDesiredArmPosition() + change;
    }, false);
    
  }

  @Override
  protected void initialize() {
    Robot.hatch.setArmPidEnabled(false);
    // Robot.hatch.setArmPosition(Hatch.DEFAULT_HIGH_POSITION);
    Robot.hatch.launchHatch(false);
  }

}
