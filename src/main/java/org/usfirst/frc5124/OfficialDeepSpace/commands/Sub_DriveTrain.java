package org.usfirst.frc5124.OfficialDeepSpace.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.DriveTrain;

/**
 *
 */
public class Sub_DriveTrain extends Command {

    public Sub_DriveTrain() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        // // TANK DRIVE
        // double left = Robot.oi.getWill().getY(Hand.kLeft);
        // double right = Robot.oi.getWill().getY(Hand.kRight);
        // left = DriveTrain.deadZone(left);
        // right = DriveTrain.deadZone(right);
        // Robot.driveTrain.tankDrive(left, right);

//        ARCADE DRIVE
        double power = Robot.oi.getMultiStick().getY();
        double x = -Robot.oi.getMultiStick().getX();
        double z = -Robot.oi.getMultiStick().getZ();
        double turn = Math.abs(x) > Math.abs(z) ? x:z;
        power = DriveTrain.deadZone(power);
        turn = DriveTrain.deadZone(turn);
        Robot.driveTrain.arcadeDrive(power, turn); 

        SmartDashboard.putNumber("X value ", x);
        SmartDashboard.putNumber("Z value ", z);
        SmartDashboard.putNumber("Turn Value ", turn);

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
