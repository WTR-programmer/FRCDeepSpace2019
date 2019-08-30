package org.usfirst.frc5124.OfficialDeepSpace;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * 
 */
public class SpeedControllerX implements SpeedController {

    private final BaseMotorController motor;
    private boolean inverted;

    public SpeedControllerX (BaseMotorController motor) {
        this.motor = motor;
        inverted = false;
    }

    @Override
    public void pidWrite(double output) {
        set(output);
    }

    @Override
    public void set(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public double get() {
        return motor.getMotorOutputPercent();
    }

    @Override
    public void setInverted(boolean isInverted) {
        this.inverted = isInverted;
    }

    @Override
    public boolean getInverted() {
        return inverted;
    }

    @Override
    public void disable() {
        stopMotor();
    }

    @Override
    public void stopMotor() {
        motor.set(ControlMode.Current, 0);
    }

}
