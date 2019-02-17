package org.usfirst.frc5124.OfficialDeepSpace;

import org.usfirst.frc5124.OfficialDeepSpace.commands.*;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.Hatch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private XboxController aidan;
    private JoystickButton aidanA;
    private JoystickButton aidanB;
    private JoystickButton aidanX;
    private JoystickButton aidanY;
    private JoystickButton aidanLeftBumper;
    private JoystickButton aidanRightBumper;
    private JoystickButton aidanStart;
    private JoystickButton aidanBack;
    
    private XboxController will;

    private Joystick multiPurposeJoystick;
    private JoystickButton intakeButton;
    private JoystickButton outTakeButton;
    private JoystickButton hatchShootButton;
    private JoystickButton catapultButton;
    private JoystickButton hatchArmUpButton;
    private JoystickButton hatchArmDownButton;


    public OI() {
    
        aidan = new XboxController(0);
        
        aidanA = new JoystickButton(aidan, XboxButtons.kA.value);
        HatchShoot hatchShootCmd = new HatchShoot(true);
        aidanA.whileHeld(hatchShootCmd);
        SmartDashboard.putData("Operator A Button", hatchShootCmd);
        
        aidanB = new JoystickButton(aidan, XboxButtons.kB.value);
        CatapultShoot catapultShootCmd = new CatapultShoot(true);
        aidanB.whileHeld(catapultShootCmd);
        SmartDashboard.putData("Operator B Button", catapultShootCmd);
        
        aidanX = new JoystickButton(aidan, XboxButtons.kX.value);
        IntakeDeploy intakeDeployerCmd = new IntakeDeploy(true);
        aidanX.whenPressed(intakeDeployerCmd);
        SmartDashboard.putData("Operator X Button", intakeDeployerCmd);
        
        aidanY = new JoystickButton(aidan, XboxButtons.kY.value);
        IntakeDeploy intakeUndeployerCmd = new IntakeDeploy(false);
        aidanY.whenPressed(intakeUndeployerCmd);
        SmartDashboard.putData("Operator Y Button", intakeUndeployerCmd);
                
        aidanLeftBumper = new JoystickButton(aidan, XboxButtons.kBumperLeft.value);
        HatchArmPosition lowArmPositionCmd = new HatchArmPosition(Hatch.DEFAULT_HIGH_POSITION, true);
        aidanLeftBumper.whenPressed(lowArmPositionCmd);
        SmartDashboard.putData("Operator Left Bumper", lowArmPositionCmd);
        
        // aidanRightBumper = new JoystickButton(aidan, XboxButtons.kBumperRight.value);
        // HatchArmPosition highArmPositionCmd = new HatchArmPosition(Hatch.DEFAULT_HIGH_POSITION, true);
        // aidanRightBumper.whenPressed(highArmPositionCmd);
        // SmartDashboard.putData(highArmPositionCmd);

        aidanStart = new JoystickButton(aidan, XboxButtons.kStart.value);
        aidanStart.whenPressed(new Command(){
        
            @Override
            protected void execute() {
                Robot.hatch.disablePID();
            }

            @Override
            protected boolean isFinished() {
                return true;
            }
        });
       
        aidanBack = new JoystickButton(aidan, XboxButtons.kBack.value);
        aidanBack.whenPressed(new Command(){
        
            @Override
            protected void execute() {
                Robot.hatch.enablePID();
            }

            @Override
            protected boolean isFinished() {
                return true;
            }
        });

        //Multi Purpose Joystick in case we need it

        



    

        will = new XboxController(1);
        multiPurposeJoystick = new Joystick(2);

        SmartDashboard.putData("Default Autonomous Command", Robot.defaultAutonomousCommand);
        SmartDashboard.putData("DriveTrain Subsystem Command", Robot.driveTrain.getDefaultCommand());
        // SmartDashboard.putData("Hatch Subsystem Command", Robot.hatch.getDefaultCommand());
        // SmartDashboard.putData("Intake Subsystem Command", Robot.intake.getDefaultCommand());

        LiveWindow.add(Robot.driveTrain);
        LiveWindow.add(Robot.hatch);
        LiveWindow.add(Robot.intake);
        LiveWindow.add(Robot.catapult);

    }

    public XboxController getAidan() {
        return aidan;
    }

    public XboxController getWill(){
        return will;
    }
    public Joystick getMultiStick(){
        return multiPurposeJoystick;
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

      
}