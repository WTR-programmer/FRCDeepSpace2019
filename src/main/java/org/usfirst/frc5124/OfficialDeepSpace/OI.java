package org.usfirst.frc5124.OfficialDeepSpace;

import org.usfirst.frc5124.OfficialDeepSpace.commands.*;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.Hatch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private XboxController operator;
    private JoystickButton operatorA;
    private JoystickButton operatorB;
    private JoystickButton operatorX;
    private JoystickButton operatorY;
    private JoystickButton operatorLeftBumper;
    private JoystickButton operatorRightBumper;
    private JoystickButton operatorStart;
    private JoystickButton operatorBack;
    private JoystickButton operatorLeftTrigger;
    private JoystickButton operatorRightTrigger;
    

    private XboxController will;
    

    private Joystick driver;
    private JoystickButton trigger;



    public OI() {
    
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

        //Move Hatch down normally
        // HatchMove armDownCmd = new HatchMove(.6);
        // if (operator.getPOV() == 90){
        //     armDownCmd.start(); }
        // else armDownCmd.cancel();
        // SmartDashboard.putData("Operator Right D_Pad", armDownCmd);
        
/*
        //Move Hatch Up Normally
        HatchMove armUpCmd = new HatchMove(-.4);
        if (operator.getPOV() == 270){
            armUpCmd.start(); }
        else armUpCmd.cancel();
        SmartDashboard.putData("Operator Right D_Pad", armUpCmd);
        
        
        //Mega Down
        HatchMove megaDownCmd = new HatchMove(1.0);
        if (operator.getTriggerAxis(Hand.kRight) > .15){
            megaDownCmd.start(); }
        else megaDownCmd.cancel();
        SmartDashboard.putData("Operator Right Trigger", megaDownCmd);

        //Mega Up
        HatchMove megaUpCmd = new HatchMove(-.6);
        if (operator.getTriggerAxis(Hand.kLeft) > .15){
            megaUpCmd.start(); }
        else megaUpCmd.cancel();
        SmartDashboard.putData("Operator Left Trigger", megaUpCmd);
*/
        //Disable PID
        operatorStart = new JoystickButton(operator, XboxButtons.kStart.value);
        HatchPIDEnabler disablePIDCmd = new HatchPIDEnabler(false); 
        operatorStart.whenPressed(disablePIDCmd);
        SmartDashboard.putData("Operator Start Button", disablePIDCmd);
        
       
        //Enable PID
        operatorBack = new JoystickButton(operator, XboxButtons.kBack.value);
        HatchPIDEnabler enablePIDcmd = new HatchPIDEnabler(true); 
        operatorStart.whenPressed(enablePIDcmd);
        SmartDashboard.putData("Operator Back Button", enablePIDcmd);

         //Intake and Outtake Commands are set to POV Up and POV Down in Intake default command

        
        will = new XboxController(1);

        //Joystick
        driver = new Joystick(2);

        //Allow driver to stablize arm
        trigger = new JoystickButton(driver, 1);
        HatchMove hatchMoveCmd = new HatchMove(-.1);
        trigger.whileHeld(hatchMoveCmd);
        SmartDashboard.putData("Driver Trigger", hatchMoveCmd);



        SmartDashboard.putData("Default Autonomous Command", Robot.defaultAutonomousCommand);
        SmartDashboard.putData("DriveTrain Subsystem Command", Robot.driveTrain.getDefaultCommand());
        // SmartDashboard.putData("Hatch Subsystem Command", Robot.hatch.getDefaultCommand());
        // SmartDashboard.putData("Intake Subsystem Command", Robot.intake.getDefaultCommand());

        LiveWindow.add(Robot.driveTrain);
        LiveWindow.add(Robot.hatch);
        LiveWindow.add(Robot.intake);
        LiveWindow.add(Robot.catapult);

    }

    //Controller Declarations
    public XboxController getOperator() {
        return operator;
    }

    public XboxController getWill(){
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