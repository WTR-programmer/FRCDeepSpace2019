package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.Hatch;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sub_Hatch extends HatchArmPower {

  public Sub_Hatch() {
    /* // position change: set extends to HatchArmPosition
    super(() -> {
      double right = Hatch.deadZone(Robot.oi.getAidan().getTriggerAxis(Hand.kRight));
      double left = Hatch.deadZone(Robot.oi.getAidan().getTriggerAxis(Hand.kLeft));
      double change =  0.01 * Hatch.deadZone(right - left);
      return Robot.hatch.getDesiredArmPosition() + change;
    }, false);
    */

    // power change: set extends to HatchArmPower
    super(() -> {
      SmartDashboard.putNumber("pot", Robot.hatch.getPot());
      SmartDashboard.putNumber("desired value", Robot.hatch.getDesiredArmPosition());
      SmartDashboard.putNumber("arm power", Robot.hatch.getArmPower());
      if (Robot.oi.getAidan().getTriggerAxis(Hand.kLeft) > .15) {
        return -.3;
      }
      if (Robot.oi.getAidan().getTriggerAxis(Hand.kRight) > .15) {
        return .3;
      }
      if (Robot.oi.getAidan().getPOV() == 90 && Robot.hatch.isSafeForUp()){
        return -.4;
      }
      if (Robot.oi.getAidan().getPOV() == 270 && Robot.hatch.isSafeForDown()){
        return .4;
      }
      return 0.0;
    });

  }

  @Override
  protected void initialize() {
    Robot.hatch.setArmPidEnabled(false);
    Robot.hatch.setArmPosition(Hatch.DEFAULT_HIGH_POSITION);
    Robot.hatch.launchHatch(false);
  }

}
