package org.usfirst.frc5124.OfficialDeepSpace;

import org.usfirst.frc5124.OfficialDeepSpace.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

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

    private Joystick will;
    private JoystickButton willTrigger;
    private JoystickButton willThumb;

    public OI() {

        oi = new Subsystem(){
            @Override
            protected void initDefaultCommand() {
            }
        };
    
        aidan = new XboxController(0);
        
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

        willTrigger = new JoystickButton(will, 1);
        HatchShoot hatchShootCmd = new HatchShoot(true);
        willTrigger.whileHeld(hatchShootCmd);
        oi.addChild("Driver Trigger", hatchShootCmd);
        
        willThumb = new JoystickButton(will, 2);
        VisionCommand visionCmd = new VisionCommand();
        willThumb.whileHeld(visionCmd);
        oi.addChild("Driver Thumb", visionCmd);
        

        oi.addChild("Default Autonomous Command", Robot.defaultAutonomousCommand);
        oi.addChild("DriveTrain Subsystem Command", Robot.driveTrain.getDefaultCommand());
        oi.addChild("Hatch Subsystem Command", Robot.hatch.getDefaultCommand());
        oi.addChild("Intake Subsystem Command", Robot.intake.getDefaultCommand());

        LiveWindow.add(Robot.driveTrain);
        LiveWindow.add(Robot.hatch);
        LiveWindow.add(Robot.intake);
        LiveWindow.add(Robot.catapult);
        LiveWindow.add(oi);

    }

    public XboxController getAidan() {
        return aidan;
    }

    public Joystick getWill(){
        return will;
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