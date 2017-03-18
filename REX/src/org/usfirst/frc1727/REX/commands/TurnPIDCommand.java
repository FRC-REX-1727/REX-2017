package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.Robot;
import org.usfirst.frc1727.REX.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnPIDCommand extends Command {
	private double kP;
	private double kI;
	private double kD;
	private double error;
	private double integral;
	private double derivative;
	private double previousError;
	private double power;
	private double targetDegree = 0;
	private double[] stability = new double[10];
	private final ADXRS450_Gyro gyro = RobotMap.autonGyro;
	private static int stabilityCounter = 0;
    private static double averageError = 0; 
    private static double totalError = 0;
	public TurnPIDCommand(double targetDegree) {
    	this.targetDegree = targetDegree;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	for(int i = 0; i < stability.length; i++){
    		this.stability[i] = targetDegree; 
    	}
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	kP = OI.TURN_KP;
    	kI = OI.TURN_KI;
    	kD = OI.TURN_KD;
    	gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = targetDegree -  gyro.getAngle();
    	integral += error;
		derivative = error - previousError;
		if(Math.abs(integral)>(.25/kI)){
			if(integral > 0){
				integral = (.25/kI);
			}
			else{
				integral = -(.25/kI);
			}
		}
		previousError = error;
		power = (kP * error) + (kI * integral) + (kD * derivative);
		
		if(power > 1){
			power = 1;
		}
		if(power< -1){
			power = -1;
		}
//		Robot.drivetrain.getDrive().tankDrive(((-power)), -(power));
		Robot.drivetrain.getLeftDrive().set(power);
		Robot.drivetrain.getRightDrive().set(-power);
		stability[stabilityCounter] = error;
		if(stabilityCounter < 9){
			stabilityCounter++;
		}
		else{
			stabilityCounter = 0;
			totalError = 0;
			stabilityCounter = 0;
		}
		System.out.println("gyro: " + gyro.getAngle() + " \t power: " + power);
		
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	for(int i = 0; i < stability.length; i++){
    		totalError += stability[i];
    	}
    	averageError = (totalError/10);
    	if(averageError <= 5 && averageError >= -5){
    		return true;
    	}
    	else{
    		return false;
    	}
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrain.getDrive().tankDrive(0, 0);
    	Robot.drivetrain.getLeftDrive().set(0);
    	Robot.drivetrain.getRightDrive().set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
