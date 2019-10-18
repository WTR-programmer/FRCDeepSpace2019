package org.usfirst.frc5124.OfficialDeepSpace.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.DriveTrain;

/**
 *
 */
public class Sub_DriveTrain extends Command {
    NetworkTableInstance inst;
  NetworkTable limelight;
  NetworkTableEntry tx;
  NetworkTableEntry thor;
  NetworkTableEntry dist;

  double distance;
  double angle;
  
  double KPdist;
  double KFdist;
  double KPangle;
  double KFangle;

  double power;
  double turn;

  double average;

    public Sub_DriveTrain() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.driveTrain.setAutoDriving(false);
        inst = NetworkTableInstance.getDefault();
    limelight = inst.getTable("limelight");
    tx = limelight.getEntry("tx");
    thor =limelight.getEntry("thor");
    
    KPangle = .05;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        average = (Robot.oi.getDriverLeft().getY() + Robot.oi.getDriverRight().getY())/2;
        turn = tx.getDouble(0);
        turn = KPangle * turn;
        // // TANK DRIVE
        // if (false){
            // Robot.driveTrain.arcadeDrive(average, turn);
        // }
        // else{
        double left = -Robot.oi.getDriverBox().getY(Hand.kLeft);
        double right = -Robot.oi.getDriverBox().getY(Hand.kRight);
        // System.out.println(left + ", " + right);
        left = DriveTrain.deadZone(left);
        right = DriveTrain.deadZone(right);
        Robot.driveTrain.tankDrive(0.85 * left, 0.85 * right);
        // }

//        ARCADE DRIVE
        // double power = Robot.oi.getWill().getY();
        // double x = -Robot.oi.getWill().getX();
        // double z = -Robot.oi.getWill().getZ();
        // double turn = Math.abs(x) > Math.abs(z) ? x:z;
        // power = DriveTrain.deadZone(power);
        // turn = DriveTrain.deadZone(turn);
        // Robot.driveTrain.arcadeDrive(power, turn); 

        // SmartDashboard.putNumber("X value ", x);
        // SmartDashboard.putNumber("Z value ", z);
        // SmartDashboard.putNumber("Turn Value ", turn);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
