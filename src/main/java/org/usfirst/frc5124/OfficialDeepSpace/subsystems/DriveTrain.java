package org.usfirst.frc5124.OfficialDeepSpace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

// import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import org.usfirst.frc5124.OfficialDeepSpace.SendableX;
import org.usfirst.frc5124.OfficialDeepSpace.SpeedControllerX;
import org.usfirst.frc5124.OfficialDeepSpace.commands.Sub_DriveTrain;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

    private boolean autoDriving;
    private static double DEAD_ZONE = .05;

    private TalonSRX leftMotor1;
    private TalonSRX leftMotor2;
    private TalonSRX leftMotor3;
    private TalonSRX rightMotor1;
    private TalonSRX rightMotor2;
    private TalonSRX rightMotor3;

    private DifferentialDrive drive;

    public DriveTrain() {

        leftMotor1 = new TalonSRX(3);
        addChild("Left Motor 1", new SendableX(leftMotor1));
        leftMotor1.setInverted(false);
        
        leftMotor2 = new TalonSRX(2);
        addChild("Left Motor 2", new SendableX(leftMotor2));
        leftMotor2.setInverted(false);

        leftMotor3 = new TalonSRX(1);
        addChild("Left Motor 3", new SendableX(leftMotor3));
        leftMotor3.setInverted(false);

        rightMotor1 = new TalonSRX(4);
        addChild("Right Motor 1", new SendableX(rightMotor1));
        rightMotor1.setInverted(false);
        
        rightMotor2 = new TalonSRX(5);
        addChild("Right Motor 2", new SendableX(rightMotor2));
        rightMotor2.setInverted(false);

        rightMotor3 = new TalonSRX(6);
        addChild("Right Motor 3", new SendableX(rightMotor3));
        rightMotor3.setInverted(false);

        drive = new DifferentialDrive(
            new SpeedControllerGroup(
                new SpeedControllerX(leftMotor1),
                new SpeedControllerX(leftMotor2),
                new SpeedControllerX(leftMotor3)
            ),
            new SpeedControllerGroup(
                new SpeedControllerX(rightMotor1),
                new SpeedControllerX(rightMotor2),
                new SpeedControllerX(rightMotor3)
            )
        );
        addChild("Differential Drive", drive);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Sub_DriveTrain());
    }

    public void arcadeDrive(double power, double turn) {
        if(!autoDriving){
        drive.arcadeDrive(power, turn);
        }
    }
    public void autoArcadeDrive(double power, double turn){
        setAutoDriving(true);
        drive.arcadeDrive(power, turn);
    }
    public void setAutoDriving(boolean visionRunning){
        autoDriving = visionRunning;
    }

    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    //Getting power for each motor
    public double getRightMotor1Power(){
        return rightMotor1.getMotorOutputPercent();
    }
    public double getRightMotor2Power(){
        return rightMotor2.getMotorOutputPercent();
    }
    public double getLeftMotor1Power(){
        return leftMotor1.getMotorOutputPercent();
    }
    public double getLeftMotor2Power(){
        return leftMotor2.getMotorOutputPercent();
    }

    

    //utility Function. makes min value .15
    public static double deadZone(double value) {
        if (Math.abs(value) < Math.abs(DEAD_ZONE)) return 0;
        else return value;
    }
}