package org.usfirst.frc5124.OfficialDeepSpace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.usfirst.frc5124.OfficialDeepSpace.SendableX;
import org.usfirst.frc5124.OfficialDeepSpace.SpeedControllerX;
import org.usfirst.frc5124.OfficialDeepSpace.commands.HatchControlls;
import org.usfirst.frc5124.OfficialDeepSpace.commands.HatchStill;
import org.usfirst.frc5124.OfficialDeepSpace.commands.Sub_Hatch;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

<<<<<<< HEAD
public class Hatch extends Subsystem {

    public static final double MAX_SAFE_ARM_VALUE = 1;
=======


public class Hatch extends Subsystem {

    public static final double MAX_SAFE_ARM_VALUE = 1.2; //more than 1
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3
    public static final double MIN_SAFE_ARM_VALUE = .38;
    public static final double DEFAULT_HIGH_POSITION = .5;
    public static final double DEFAULT_LOW_POSITION = 1; 

    private VictorSPX hatchArm;
    private PIDController armPid;
    private AnalogPotentiometer armPot;
    private DoubleSolenoid hatchEject;
    private DoubleSolenoid hatchClaws;
    

    public Hatch() {

        hatchArm = new VictorSPX(5);
        addChild("Arm", new SendableX(hatchArm));
        hatchArm.setInverted(true);

        armPot = new AnalogPotentiometer(0);
        addChild("Arm Potentiometer", armPot);

        //Untuned PID
        armPid = new PIDController(0, 0, 0, armPot, new SpeedControllerX(hatchArm));
        addChild("Arm PID", armPid);

        hatchEject = new DoubleSolenoid(0, 1, 6);
        addChild("Deployer Double Solenoid", hatchEject);

        hatchClaws = new DoubleSolenoid(0, 3, 4);
        addChild("Claws Double Solenoid", hatchClaws);

    }

    @Override
    public void initDefaultCommand() {
<<<<<<< HEAD
        setDefaultCommand(new Sub_Hatch());
=======
        setDefaultCommand(new HatchControlls());
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3
    }

    public void setArmPosition(double position) {
        if (position > MAX_SAFE_ARM_VALUE) {
            position = MAX_SAFE_ARM_VALUE;
        }
        if (position < MIN_SAFE_ARM_VALUE) {
            position = MIN_SAFE_ARM_VALUE;
        }
        armPid.setSetpoint(position);
    }

    public void launchHatch(boolean launch) {
        hatchEject.set(launch ? Value.kReverse : Value.kForward);
    }

<<<<<<< HEAD
    public void activateClaws(boolean active) {
        hatchEject.set(active ? Value.kReverse : Value.kForward);
    }

    public void setArmPidEnabled(boolean enabled) {
        armPid.setEnabled(enabled);
    }
=======
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3

    public double getDesiredArmPosition () {
        return armPid.getSetpoint();
    }

    public static double deadZone(double original) {
        return Math.abs (original) < 0.1 ? 0 : original;
    }

    public double getPot(){
        return armPot.get();
    }
<<<<<<< HEAD

    public double getArmPower(){
        return hatchArm.getMotorOutputPercent();
    }

    public void setArm(double power) {
        hatchArm.set(ControlMode.PercentOutput, power);
    }

=======
    
    public void disablePID(){
        armPid.disable();
    }
    
    public void setArm(double power) {
        hatchArm.set(ControlMode.PercentOutput, power);
    }
    public double getArmPower(){
        return hatchArm.getMotorOutputPercent();
    }
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3
    public boolean isSafeForDown(){
        return (getPot() < MAX_SAFE_ARM_VALUE);
    }

    public boolean isSafeForUp() {
        return (getPot() > MIN_SAFE_ARM_VALUE);
    }

}