package org.usfirst.frc5124.OfficialDeepSpace;

import org.usfirst.frc5124.OfficialDeepSpace.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.command.Subsystem;
=======
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

<<<<<<< HEAD
    private Subsystem oi;

    private XboxController aidan;
    private JoystickButton aidanA;
    private JoystickButton aidanB;
    private JoystickButton aidanX;
    private JoystickButton aidanY;
    private JoystickButton aidanLeftBumper;
    private JoystickButton aidanRightBumper;
    private JoystickButton aidanStart;
    private JoystickButton aidanBack;
=======
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

>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3

    private Joystick will;
    private JoystickButton willTrigger;
    private JoystickButton willThumb;

    public OI() {

        oi = new Subsystem(){
            @Override
            protected void initDefaultCommand() {
            }
        };
    
        operator = new XboxController(0);
        
<<<<<<< HEAD
        aidanA = new JoystickButton(aidan, XboxButtons.kA.value);
        AcceptHatch hatchAcceptCmd = new AcceptHatch();
        aidanA.whileHeld(hatchAcceptCmd);
        oi.addChild("Operator A Stick", hatchAcceptCmd);
        
        aidanB = new JoystickButton(aidan, XboxButtons.kB.value);
        ReleaseHatch hatchReleaseCmd = new ReleaseHatch();
        aidanB.whileHeld(hatchReleaseCmd);
        oi.addChild("Operator B Button", hatchReleaseCmd);
        
        aidanX = new JoystickButton(aidan, XboxButtons.kX.value);
        CatapultShoot catapultShootCmd = new CatapultShoot(true);
        aidanX.whileHeld(catapultShootCmd);
        oi.addChild("Operator X Button", catapultShootCmd);
        
        aidanY = new JoystickButton(aidan, XboxButtons.kY.value);
        IntakeDeploy intakeDeployerCmd = new IntakeDeploy();
        aidanY.toggleWhenPressed(intakeDeployerCmd);
        oi.addChild("Operator Y Button", intakeDeployerCmd);
                
        aidanLeftBumper = new JoystickButton(aidan, XboxButtons.kBumperLeft.value);
        HatchArmPower lowArmPositionCmd = new HatchArmPower(0.1);
        aidanLeftBumper.whenPressed(lowArmPositionCmd);
        oi.addChild("Operator Left Bumper", lowArmPositionCmd);
        
        aidanRightBumper = new JoystickButton(aidan, XboxButtons.kBumperRight.value);
        HatchArmPower highArmPositionCmd = new HatchArmPower(-0.1);
        aidanRightBumper.whenPressed(highArmPositionCmd);
        oi.addChild("Operator Right Bumper", highArmPositionCmd);

        aidanStart = new JoystickButton(aidan, XboxButtons.kStart.value);
        HatchPidActive activateHatchPidCmd = new HatchPidActive(true);
        aidanStart.whenPressed(activateHatchPidCmd);
        oi.addChild("Operator Start", activateHatchPidCmd);
       
        aidanBack = new JoystickButton(aidan, XboxButtons.kBack.value);
        HatchPidActive deactivateHatchPidCmd = new HatchPidActive(false);
        aidanBack.whenPressed(deactivateHatchPidCmd);
        oi.addChild("Operator Back", deactivateHatchPidCmd);


        will = new Joystick(2);
=======
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
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3

        willTrigger = new JoystickButton(will, 1);
        HatchShoot hatchShootCmd = new HatchShoot(true);
        willTrigger.whileHeld(hatchShootCmd);
        oi.addChild("Driver Trigger", hatchShootCmd);
        
        willThumb = new JoystickButton(will, 2);
        VisionCommand visionCmd = new VisionCommand();
        willThumb.whileHeld(visionCmd);
        oi.addChild("Driver Thumb", visionCmd);
        
        will = new XboxController(1);

<<<<<<< HEAD
        oi.addChild("Default Autonomous Command", Robot.defaultAutonomousCommand);
        oi.addChild("DriveTrain Subsystem Command", Robot.driveTrain.getDefaultCommand());
        oi.addChild("Hatch Subsystem Command", Robot.hatch.getDefaultCommand());
        oi.addChild("Intake Subsystem Command", Robot.intake.getDefaultCommand());
=======
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
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3

        LiveWindow.add(Robot.driveTrain);
        LiveWindow.add(Robot.hatch);
        LiveWindow.add(Robot.intake);
        LiveWindow.add(Robot.catapult);
        LiveWindow.add(oi);

    }

    //Controller Declarations
    public XboxController getOperator() {
        return operator;
    }

    public Joystick getWill(){
        return will;
    }
<<<<<<< HEAD
=======
    public Joystick getMultiStick(){
        return driver;
    }
>>>>>>> 1f0248a82c417e74892f02ce3d05588732ad8fe3

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