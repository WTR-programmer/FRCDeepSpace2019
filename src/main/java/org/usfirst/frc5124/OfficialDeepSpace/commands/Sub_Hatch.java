package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.Hatch;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Sub_Hatch extends HatchArmPosition {

  public Sub_Hatch() {
    super(() -> {
      double right = Hatch.deadZone(Robot.oi.getAidan().getTriggerAxis(Hand.kRight));
      double left = Hatch.deadZone(Robot.oi.getAidan().getTriggerAxis(Hand.kLeft));
      double change =  0.01 * Hatch.deadZone(right - left);
      return Robot.hatch.getDesiredArmPosition() + change;
    }, false);
    requires(Robot.hatch);
  }

  @Override
  protected void initialize() {
    Robot.hatch.setArmPosition(Hatch.DEFAULT_HIGH_POSITION);
    Robot.hatch.launchHatch(false);
  }

}