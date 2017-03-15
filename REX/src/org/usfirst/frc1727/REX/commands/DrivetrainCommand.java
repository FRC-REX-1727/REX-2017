package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.Robot;
import org.usfirst.frc1727.REX.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainCommand extends Command {
	private static Command moveBack;
	private static final double MOVE_BACK_DISTANCE = -8;
    public DrivetrainCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	moveBack = new MoveAutonCommand(MOVE_BACK_DISTANCE);
    }

    // Called repeatedly when this Command is scheduled to run
    private static double leftValue;
    private static double rightValue;
    private static boolean leftLow = false;
    private static boolean rightLow = false;
    private static double leftJoy;
    private static double rightJoy;
    private static double driveDirectionR;
    private static double driveDirectionL;
     //pi/180
    protected void execute() {
    	
    	
    	//leftValue = -(Math.pow((Robot.oi.getdriver().getRawAxis(OI.DRIVER_LEFT_VERTICAL)*100),3)/ Math.pow(100, 3))*(0.7);
    	//rightValue = -(Math.pow((Robot.oi.getdriver().getRawAxis(OI.DRIVER_RIGHT_VERTICAL)*100), 3)/Math.pow(100, 3))*(0.7);
    	/*
    	leftValue = -(Math.sin((Robot.oi.getdriver().getRawAxis(OI.DRIVER_LEFT_VERTICAL)*90)*(Math.PI/180)));
    	rightValue = -(Math.sin((Robot.oi.getdriver().getRawAxis(OI.DRIVER_RIGHT_VERTICAL)*90)*(Math.PI/180)));
    	

    	
    	
    	if(Math.abs(rightValue - leftValue)> 0.7){
    		leftValue = leftValue*0.6;
    		rightValue = rightValue*0.6;
    	}
    	
    	if(Math.abs(Robot.oi.getdriver().getRawAxis(OI.DRIVER_RIGHT_TRIGGER)) > 0){
    		if(leftValue <0){
    			driveDirectionL = -1;
    		}
    		else {
    			driveDirectionL = 1;
    		}
    		if(rightValue < 0){
    			driveDirectionR = -1;
    		}
    		else{
    			driveDirectionR = 1;
    		}
    		leftValue = driveDirectionL * Math.sqrt(5 * Math.abs(leftValue))* 0.7;
    		rightValue = driveDirectionR * Math.sqrt(5 * Math.abs(rightValue))* 0.7;
    		
    	}
    	//leftValue = -Robot.oi.getdriver().getRawAxis(OI.DRIVER_LEFT_VERTICAL)*0.7;
    	//rightValue = -Robot.oi.getdriver().getRawAxis(OI.DRIVER_RIGHT_VERTICAL)*0.7;
    	/*if(Math.abs(leftValue) < 0.1){
    		leftValue = 0;
    	}
    	if(Math.abs(rightValue) < 0.1){
    		rightValue = 0;
    	}*/
    	/*
    	if(Math.abs(leftValue )>0 || Math.abs(rightValue) >0){
    		//Robot.drivetrain.getDrive().tankDrive(Math.pow(leftValue, 3) , Math.pow(rightValue, 3));
    		Robot.drivetrain.getDrive().tankDrive(leftValue , rightValue);
    	}
    	else{
    		Robot.drivetrain.getDrive().stopMotor();
    	}*/
    	
    	//Robot.drivetrain.getDrive().tankDrive(leftValue , rightValue, true);
    	//System.out.println(Robot.oi.getdriverLeft().getRawAxis(1) +  Robot.oi.getdriverRight().getRawAxis(1));
    	leftValue = -Robot.oi.getdriverLeft().getRawAxis(1);
    	rightValue = -Robot.oi.getdriverRight().getRawAxis(1);
    	leftJoy = leftValue;
    	rightJoy = rightValue;
    	if(Math.abs(leftValue)<0.1){
    		leftLow = true;
    	}
    	else{
    		leftLow = false;
    	}
    	if(Math.abs(rightValue)<0.1){
    		rightLow = true;
    	}
    	else{
    		rightLow = false;
    	}
    	//System.out.println("Sonar Voltage: " + Robot.drivetrain.getSonar().getVoltage());
    	//leftValue = Math.pow(leftValue, 3);
    	//rightValue = Math.pow(rightValue, 3);
    	/*
    	if(Math.abs(leftValue)<0.5 && Math.abs(leftValue)>0.1){
    		leftValue = (Math.sin((leftValue*90)*(Math.PI/180))) * 1;
    	}
    	else{
    		leftValue = (Math.sin((leftValue*90)*(Math.PI/180)));	
    	}
    	if(Math.abs(rightValue)<0.5 && Math.abs(rightValue)>0.1){
    		rightValue = (Math.sin((rightValue*90)*(Math.PI/180))) * 1;
    	}
    	else{
    		rightValue = (Math.sin((rightValue*90)*(Math.PI/180)));	
    	}
    	 if(Math.abs(rightValue - leftValue)> 0.6){
     		leftValue = leftValue*0.4;
     		rightValue = rightValue*0.4;
     	}
    	if(Robot.oi.driveSlowButton.get()){
    		leftValue = leftValue * 0.6;
    		rightValue = rightValue * 0.6;
    	}
    	else if(Math.abs(righ	tValue - leftValue)> 0.4){
    		leftValue = leftValue*0.6;
    		rightValue = rightValue*0.6;
    	}*/
    	
    	
    	//leftValue = Math.pow(leftValue, 3);
    	//rightValue = Math.pow(rightValue, 3);
    	leftValue = (Math.sin((leftValue*90)*(Math.PI/180)));
    	rightValue = (Math.sin((rightValue*90)*(Math.PI/180)));
    	if(Robot.oi.driveSlowButton.get()){
    		if(Math.abs(leftJoy)> 0.5){
    			leftValue = leftValue * 0.5;	
    		}
    		else{
    			leftValue = leftValue * 1.4;
    		}
    		if(Math.abs(rightJoy)>0.5){
    			rightValue = rightValue * 0.5;	
    		}
    		else{
    			rightValue = rightValue * 1.4;
    		}
    		
    	}
    	if(leftLow){
    		Robot.drivetrain.getLeftDrive().disable();
    	}
    	else{
    		Robot.drivetrain.getLeftDrive().set(leftValue);
    	}
    	if(rightLow){
    		Robot.drivetrain.getRightDrive().disable();
    	}
    	else{
    		Robot.drivetrain.getRightDrive().set(rightValue);
    	}
    	if(Robot.oi.driveStraightButton.get()){
    		Robot.drivetrain.getLeftDrive().set(1);
    		Robot.drivetrain.getRightDrive().set(1);	
    	}
    	
    	
    	
    	
    	
    	System.out.println("Sonar: " + (Robot.drivetrain.getSonar().getVoltage()* OI.VOLTAGE_TO_MM));
    	
    //	Robot.drivetrain.getDrive().tankDrive(leftValue, rightValue);
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		
    }
}
