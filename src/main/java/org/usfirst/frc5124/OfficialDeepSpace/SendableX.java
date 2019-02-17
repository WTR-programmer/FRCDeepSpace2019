/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5124.OfficialDeepSpace;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * 
 */
public class SendableX implements Sendable {

    private BaseMotorController motor;
    private SendableImpl baseSendable;

    public SendableX(BaseMotorController motor) {
        this.motor = motor;
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
        builder.setSmartDashboardType("CAN Motor");
        builder.setActuator(true);
        builder.setSafeState(() -> motor.set(ControlMode.Current, 0));
        builder.addDoubleProperty("Percent Output",
            () -> motor.getMotorOutputPercent(),
            value -> motor.set(ControlMode.PercentOutput, value)
        );
        builder.addDoubleProperty("Bus Voltage", () -> motor.getBusVoltage(), (value)->{});
        builder.addDoubleProperty("Device ID", () -> motor.getDeviceID(), (value)->{});
        builder.addStringProperty("Control Mode", () -> motor.getControlMode().name(), (value)->{});
        builder.addDoubleProperty("Temperature", () -> motor.getTemperature(), (value)->{});
	}

}
