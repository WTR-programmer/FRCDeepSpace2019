package org.usfirst.frc5124.OfficialDeepSpace.commands;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

public class Sub_Intake extends IntakeCargo {

    public Sub_Intake() {
        super(() -> {
            int pov = Robot.oi.getAidan().getPOV();
            if (pov == 180) {
                return 0.45; // intake
            }
            if (pov == 0) {
                return -0.45; // outtake
            }
            return 0.0;
        });
    }

    @Override
    protected void initialize() {
        Robot.intake.setIntakeDeployed(false);
    }

}