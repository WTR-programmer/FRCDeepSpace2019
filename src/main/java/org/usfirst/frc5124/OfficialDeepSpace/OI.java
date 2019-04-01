package org.usfirst.frc5124.OfficialDeepSpace;

import org.usfirst.frc5124.OfficialDeepSpace.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

    private XboxController operator;
    private JoystickButton operatorA;
    private JoystickButton operatorB;
    private JoystickButton operatorX;
    private JoystickButton operatorY;
    private JoystickButton operatorStart;
    private JoystickButton operatorBack;

    
    private Joystick will;
    
    private Joystick driver;
    private JoystickButton trigger;

    public OI() {

        // oi = new Subsystem(){
        //     @Override
        //     protected void initDefaultCommand() {
        //     }
        // };
    
        operator = new XboxController(0);
        
        //Shoot Hatch
        operatorA = new JoystickButton(operator, XboxButtons.kA.value);
        HatchShoot hatchShootCmd = new HatchShoot(true);
        operatorA.toggleWhenPressed(hatchShootCmd);
        SmartDashboard.putData("Operator A Button", hatchShootCmd);
        
        //Shoot Catapult
        operatorB = new JoystickButton(operator, XboxButtons.kB.value);
        CatapultShoot catapultShootCmd = new CatapultShoot(true);
        operatorB.whileHeld(catapultShootCmd);
        SmartDashboard.putData("Operator B Button", catapultShootCmd);
        
        //Intake Down
        operatorX = new JoystickButton(operator, XboxButtons.kX.value);
        IntakeDeploy intakeDeployerCmd = new IntakeDeploy(true);
        operatorX.whenPressed(intakeDeployerCmd);
        SmartDashboard.putData("Operator X Button", intakeDeployerCmd);
        
        //Intake Up
        operatorY = new JoystickButton(operator, XboxButtons.kY.value);
        IntakeDeploy intakeUndeployerCmd = new IntakeDeploy(false);
        operatorY.whenPressed(intakeUndeployerCmd);
        SmartDashboard.putData("Operator Y Button", intakeUndeployerCmd);
        
        //Disable PID
        operatorStart = new JoystickButton(operator, XboxButtons.kStart.value);
        HatchPIDEnabler disablePIDCmd = new HatchPIDEnabler(false); 
        operatorStart.whenPressed(disablePIDCmd);
        SmartDashboard.putData("Operator Start Button", disablePIDCmd);        
       
        //Enable PID
        operatorBack = new JoystickButton(operator, XboxButtons.kBack.value);
        HatchPIDEnabler enablePIDcmd = new HatchPIDEnabler(true); 
        operatorBack.whenPressed(enablePIDcmd);
        SmartDashboard.putData("Operator Back Button", enablePIDcmd);

        //Intake and Outtake Commands are set to POV Up and POV Down in Intake default command
        will = new Joystick(1);

        //Joystick
        driver = new Joystick(2);

        //Allow driver to stablize arm
        trigger = new JoystickButton(driver, 1);
        HatchMove hatchMoveCmd = new HatchMove(-.1);
        trigger.whileHeld(hatchMoveCmd);
        SmartDashboard.putData("Driver Trigger", hatchMoveCmd);


        SmartDashboard.putData("Default Autonomous Command", Robot.defaultAutonomousCommand);
        SmartDashboard.putData("DriveTrain Subsystem Command", Robot.driveTrain.getDefaultCommand());
        SmartDashboard.putData("Hatch Subsystem Command", Robot.hatch.getDefaultCommand());
        SmartDashboard.putData("intake Subsystem Command", Robot.intake.getDefaultCommand());

        // LiveWindow.add(Robot.driveTrain);
        // LiveWindow.add(Robot.hatch);
        // LiveWindow.add(Robot.intake);
        // LiveWindow.add(Robot.catapult);
        // LiveWindow.add(oi);

    }

    //Controller Declarations
    public XboxController getOperator() {
        return operator;
    }

    public Joystick getWill(){
        return will;
    }
    public Joystick getMultiStick(){
        return driver;
    }

    private enum XboxButtons {
        kBumperLeft(5),
        kBumperRight(6),
        kStickLeft(9),
        kStickRight(10),
        kA(1),
        kB(2),
        kX(3),
        kY(4),
        kBack(7),
        kStart(8);

    
        private final int value;
    
        XboxButtons(int value) {
          this.value = value;
        }
      }
      final double kLeftTrigger = 10;

      
}