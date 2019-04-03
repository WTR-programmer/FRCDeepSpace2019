/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc5124.OfficialDeepSpace.Robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class DriveToTarget extends Command {
  public DriveToTarget() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  double DISTANCE_CONSTANT = 5820;

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

  @Override
  protected void initialize() {
    inst = NetworkTableInstance.getDefault();
    limelight = inst.getTable("limelight");
    tx = limelight.getEntry("tx");
    thor =limelight.getEntry("thor");
    // dist = limelight.getEntry("");

    KPdist = .05;
    KFdist = .1;
    KPangle = .05;
    KFangle = .1;

  }

  @Override
  protected void execute() {
    distance = thor.getDouble(0) / DISTANCE_CONSTANT;
    turn = tx.getDouble(0);
    power = KPdist * distance;
    turn = KPangle * turn;

//Make P inadequate so that the robot is normal at beginning but slow at the end
    // power = KPdist * distance > .5 ? power + KFdist : power;
    // turn = KPangle * turn > .5 ? turn + KFangle : turn;

    Robot.driveTrain.autoArcadeDrive(power, turn);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    Robot.driveTrain.setAutoDriving(false);
  }
}
