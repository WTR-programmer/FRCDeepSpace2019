package org.usfirst.frc5124.OfficialDeepSpace.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Catapult extends Subsystem {

    private DoubleSolenoid catapultSolenoid;

    public Catapult() {
        catapultSolenoid = new DoubleSolenoid(0, 0, 7);
        addChild("Double Solenoid", catapultSolenoid);
    }

    @Override
    public void initDefaultCommand() {
        // No default command, all teleop controls are buttons.
        launchCatapult(false);
    }

    public void launchCatapult(boolean launch) {
        catapultSolenoid.set(launch ? Value.kReverse : Value.kForward);
    }

    public boolean getCatapultIsUp() {
        return catapultSolenoid.get() == Value.kReverse;
    }

}