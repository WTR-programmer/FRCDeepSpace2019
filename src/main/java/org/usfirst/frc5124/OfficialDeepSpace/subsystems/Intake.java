package org.usfirst.frc5124.OfficialDeepSpace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.usfirst.frc5124.OfficialDeepSpace.SendableX;
import org.usfirst.frc5124.OfficialDeepSpace.commands.Sub_Intake;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class Intake extends Subsystem {

    private VictorSPX cargoIntake;
    private DoubleSolenoid intakeDeploy;

    public Intake() {

        cargoIntake = new VictorSPX(6);
        addChild("Cargo Intake", new SendableX(cargoIntake));
        cargoIntake.setInverted(false);

        intakeDeploy = new DoubleSolenoid(0, 2, 5);
        addChild("Intake Deploy", intakeDeploy);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Sub_Intake());
    }

    @Override
    public void periodic() {

    }

    public void powerIntake(double power) {
        cargoIntake.set(ControlMode.PercentOutput, power);
    }

    public double getIntakeVoltage() {
        return cargoIntake.getBusVoltage();
    }

    public void setIntakeDeployed(boolean deployed) {
        intakeDeploy.set(deployed ? Value.kForward : Value.kReverse);
    }

    public boolean getIntakeDeployed() {
        return intakeDeploy.get() == Value.kForward;
    }

}