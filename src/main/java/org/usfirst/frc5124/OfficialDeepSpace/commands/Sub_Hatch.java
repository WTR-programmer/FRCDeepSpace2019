package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@Deprecated
public class Sub_Hatch extends HatchArmPower {

  public Sub_Hatch() {
    super(() -> {
      int pov = Robot.oi.getOperator().getPOV();
            if (pov == 90) {
                return 0.30; // down
            }
            if (pov == 270) {
                return -0.30; // up
            }
            return 0.0;
    }, false);
    
  }

  @Override
  protected void initialize() {
    Robot.hatch.disablePID();
    // Robot.hatch.setArmPosition(Hatch.DEFAULT_HIGH_POSITION);
    Robot.hatch.launchHatch(true);
  }

}
