package org.usfirst.frc5124.OfficialDeepSpace;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc5124.OfficialDeepSpace.commands.*;
import org.usfirst.frc5124.OfficialDeepSpace.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    public static Command autonomousCommand;
    public static Command defaultAutonomousCommand;
    public static SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static DriveTrain driveTrain;
    public static Catapult catapult;
    public static Hatch hatch;
    public static Intake intake;

    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        defaultAutonomousCommand = new Auto_DefaultCommand();

        driveTrain = new DriveTrain();
        catapult = new Catapult();
        hatch = new Hatch();
        intake = new Intake();

        oi = new OI();


        CameraServer.getInstance().startAutomaticCapture();

        chooser.setDefaultOption("Default Autonomous Command", defaultAutonomousCommand);
        SmartDashboard.putData("Auto mode", chooser);

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.

        //init PID so it doesn't try to destroy the insides on init
        // hatch.setArmPosition(.5); //Except just kidding
        hatch.setArmPidEnabled(true);

        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        // //shit stuff for emergency
        // hatch.setArmPidEnabled(false);

        if (Robot.oi.getAidan().getTriggerAxis(Hand.kLeft) > .15) {
            hatch.setArm(-.3);
        }
        else if (Robot.oi.getAidan().getTriggerAxis(Hand.kRight) > .15) {
            hatch.setArm(.3);
        }
        else hatch.setArm(0);

        //PID Code
        // if (Robot.oi.getAidan().getPOV() == 90){
        //     hatch.setArmPosition(hatch.DEFAULT_HIGH_POSITION);
        // }
        // else if (Robot.oi.getAidan().getPOV() == 270){
        //     hatch.setArmPosition(hatch.DEFAULT_LOW_POSITION);
        // }

        if (Robot.oi.getAidan().getPOV() == 90 && hatch.isSafeForUp()){
            hatch.setArmPidEnabled(false);
            hatch.setArm(-.4);
        }
        else if (Robot.oi.getAidan().getPOV() == 270 && hatch.isSafeForDown()){
            hatch.setArm(.4);
            hatch.setArmPidEnabled(false);
        }
        else hatch.setArm(0);
        
        


        
        // if (Robot.oi.getMultiStick().getRawButton(6)){
        //     Robot.hatch.launchHatch(true);
        // }
        // else{
        //     Robot.hatch.launchHatch(false);
        // }
        // if (Robot.oi.getMultiStick().getRawButton(1) && Robot.intake.getIntakeDeployed()){
        //     Robot.catapult.launchCatapult(true);
        // }
        // else {
        //     Robot.catapult.launchCatapult(false);
        // }

        // if (Robot.oi.getMultiStick().getRawButton(8)){
        //     Robot.intake.powerIntake(.45);
        // } 
        // else if (Robot.oi.getMultiStick().getRawButton(7)){
        //     Robot.intake.powerIntake(-.45);
        // }
        // else {
        //     Robot.intake.powerIntake(0);
        // }
        
        

        // if (Robot.oi.getMultiStick().getRawButton(3)){
        //     Robot.intake.setIntakeDeployed(true);
        // }
        // else if(Robot.oi.getMultiStick().getRawButton(5)){
        //     Robot.intake.setIntakeDeployed(false);
        // }


        





        SmartDashboard.putNumber("pot", hatch.getPot());
        SmartDashboard.putNumber("desired value", hatch.getDesiredArmPosition());
        SmartDashboard.putNumber("arm power", hatch.getArmPower());
        

    }
}
