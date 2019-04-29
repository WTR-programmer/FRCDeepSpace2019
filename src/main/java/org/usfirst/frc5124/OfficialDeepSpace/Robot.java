package org.usfirst.frc5124.OfficialDeepSpace;

import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.hal.PDPJNI;
import edu.wpi.first.wpilibj.TimedRobot;
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
    //This should only be in LancasterPrep

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

        CameraServer.getInstance().startAutomaticCapture(0);
        CameraServer.getInstance().startAutomaticCapture(1);
        // CameraServer.getInstance().startAutomaticCapture(2);

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
        hatch.setArmPidEnabled(false);

        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.hatch.setArmPidEnabled(false);
        
      ////arm Controlls

        // if (Robot.oi.getoperator().getPOV() == 90){
        //     hatch.setArm(.6);
        // }
        // else if (Robot.oi.getoperator().getRawAxis(2) > .15){
        //     hatch.setArm(-.7);
        // }
        // else if (Robot.oi.getoperator().getPOV() == 270){
        //     hatch.setArm(-.4);
        // }
        // else if (Robot.oi.getoperator().getRawAxis(3) > .15) {
        //     hatch.setArm(1);
        // }
        // else {
        //     hatch.setArm(0);
        // }
        
        

        ////hatch shooter
        
        // if (Robot.oi.getOperator().getRawButton(3)){
         //     Robot.hatch.activateClaws(false);
        //     Robot.hatch.launchHatch(true);
        // }
        // else{
        //     Robot.hatch.launchHatch(false);
        // }

        ////claws

        // if(Robot.oi.getOperator().getRawButton(2)){
        //      Robot.hatch.activateClaws(true);
        // }

        ////Catapult

        // if (Robot.oi.getOperator().getRawButton(1) /*&& Robot.intake.getIntakeDeployed()*/){
        //     Robot.catapult.launchCatapult(true);
        // }
        // else {
        //     Robot.catapult.launchCatapult(false);
        // }

        ////Intake

        // if (Robot.oi.getoperator().getPOV() == 360){
            ////Raw button 8?
        //     Robot.intake.powerIntake(.45);
        // } 
        // else if (Robot.oi.getoperator().getPOV() == 180){
            ////Raw button 7?
        //     Robot.intake.powerIntake(-.45);
        // }
        // else {
        //     Robot.intake.powerIntake(0);
        // }
        
        ////intake

        // if (Robot.oi.getOperator().getRawButton(4) && Robot.intake.getIntakeDeployed()){
        //     Robot.intake.setIntakeDeployed(false);
        // }
        // else if(Robot.oi.getOperator().getRawButton(4) && !Robot.intake.getIntakeDeployed()){
        //     Robot.intake.setIntakeDeployed(true);
        // }

        


        



        

        SmartDashboard.putNumber("pot", hatch.getPot());
        SmartDashboard.putNumber("desired value", hatch.getDesiredArmPosition());
        SmartDashboard.putNumber("arm power", hatch.getArmPower());
        SmartDashboard.putNumber("POV", Robot.oi.getOperator().getPOV());
        // SmartDashboard.putNumber("L1 Voltage", driveTrain.getLeftMotor1Voltage());
        // SmartDashboard.putNumber("L2 Voltage", driveTrain.getLeftMotor2Voltage());
        // SmartDashboard.putNumber("R1 Voltage", driveTrain.getRightMotor1Voltage());
        // SmartDashboard.putNumber("R2 Voltage", driveTrain.getRightMotor2Voltage());
        SmartDashboard.putNumber("L1 Power", driveTrain.getLeftMotor1Power());
        SmartDashboard.putNumber("L2 Power", driveTrain.getLeftMotor2Power());
        SmartDashboard.putNumber("R1 Power", driveTrain.getRightMotor1Power());
        SmartDashboard.putNumber("R2 Power", driveTrain.getRightMotor2Power());
        // SmartDashboard.putNumber("L1 Current", Robot.pdp.getPDPTotalCurrent(2));
        // SmartDashboard.putNumber("L2 Current", Robot.pdp.getPDPTotalCurrent(2));
        // SmartDashboard.putNumber("R1 Current", Robot.pdp.getPDPTotalCurrent(2));
        // SmartDashboard.putNumber("R2 Current", Robot.pdp.getPDPTotalCurrent(2));
        

    }
}
