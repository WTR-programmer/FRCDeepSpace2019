/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * 
 */
public class SendableX implements Sendable {

    private SpeedControllerX motor;
    private SendableImpl baseSendable;

    public SendableX(BaseMotorController motor) {
        this.motor = new SpeedControllerX(motor);
        baseSendable = new SendableImpl(true);
    }

    @Override
    public String getName() {
        return baseSendable.getName();
    }

    @Override
    public void setName(String name) {
        baseSendable.setName(name);
    }

    @Override
    public String getSubsystem() {
        return baseSendable.getSubsystem();
    }

    @Override
    public void setSubsystem(String subsystem) {
        baseSendable.setSubsystem(subsystem);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
      builder.setSmartDashboardType("Speed Controller");
      builder.setActuator(true);
      builder.setSafeState(() -> {motor.set(0);});
      builder.addDoubleProperty("Value", motor::get, motor::set);
    }
}
