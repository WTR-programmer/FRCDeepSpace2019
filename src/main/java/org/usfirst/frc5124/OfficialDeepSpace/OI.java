package org.usfirst.frc5124.OfficialDeepSpace;

import org.usfirst.frc5124.OfficialDeepSpace.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

    private XboxController operator;
    private JoystickButton operatorA;
    private JoystickButton operatorB;
    private JoystickButton operatorX;
    private JoystickButton operatorY;
    // private JoystickButton operatorStart;
    // private JoystickButton operatorBack;

    
    private Joystick driverLeft;
    
    private Joystick driverRight;
    // private JoystickButton RightTrigger;

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
    //might have something wrong with intake deployed detector

        
        //Intake Down
        operatorX = new JoystickButton(operator, XboxButtons.kX.value);
        IntakeDeploy intakeDeployerCmd = new IntakeDeploy(true);
        operatorX.whenPressed(intakeDeployerCmd);
        SmartDashboard.putData("Operator X Button", intakeDeployerCmd);
     //might have something wrong with the safety
        
        //claws
        operatorY = new JoystickButton(operator, XboxButtons.kY.value);
        HatchClaws grabCmd= new HatchClaws(true);
        operatorY.whenPressed(grabCmd);//these lines fixed so they can be committed
        SmartDashboard.putData("Operator Y Button", grabCmd);//and this one
        

        //Intake and Outtake Commands are set to POV Up and POV Down in Sub_intake
        //Hatch arm cotrolls are in Hatch Controlls command
        
        driverLeft = new Joystick(1);
        driverRight = new Joystick(2);
        



        //Allow driver to stablize arm
        // trigger = new JoystickButton(driver, 1);
        // HatchMove hatchMoveCmd = new HatchMove(-.1);
        // trigger.whileHeld(hatchMoveCmd);
        // SmartDashboard.putData("Driver Trigger", hatchMoveCmd);


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

    public Joystick getDriverLeft(){
        return driverLeft;
    }
    public Joystick getDriverRight(){
        return driverRight;
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