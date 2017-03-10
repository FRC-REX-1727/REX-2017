package org.usfirst.frc1727.REX;

import java.math.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class PID {
	private double kP;
	private double kI;
	private double kD;
	private double error = 0;
	private double integral = 0;
	private double derivative = 0;
	private double previousError = 0;
	private double power = 0;
	private double[] stability = new double[10];
	private int stabilityCounter;
	private boolean tenErrorCheck = false;
	private double averageError;
	private double totalError;
	private boolean stabilized = false;
	public PID(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kP;
		this.kD = kP;
	}
	
	public double veloctiyPIDControl(double targetValue, double sensorValue){
		error = targetValue - sensorValue;
		integral += error;
		derivative = error - previousError;
		if(integral >(.25/kI)){
			integral = (.25/kI);
		}
		if(Math.abs(error) < 200){
			integral = 0;
		}
		previousError = error;
		
		power += (kP * error) + (kI * integral) + (kD * derivative);
		
		if(power > 1){
			power = 1;
		}
		if(power < 0){
			power = 0;
		}
		stability[stabilityCounter] = error;
		if(stabilityCounter < 9){
			stabilityCounter++;
		}
		else{
		
			tenErrorCheck = true;
			stabilityCounter = 0;
		}
		if(tenErrorCheck){
			for(int i = 0; i < stability.length; i++){
	    		totalError += stability[i];
	    	}
	    	averageError = (totalError/10);
	    	if(averageError <= 1 && averageError >= -1){
	    		stabilized = true;
	    	}
	    	else{
	    		stabilized = false;
	    	}
		}
		else{
			stabilized = false;
		}
		return power;
	}
	public static double getVelocity(double sensorValue){
		double velocity = (OI.SHOOTER_CIRR/360) * sensorValue;
		return velocity;
	}
	private final Encoder rightDriveEncoder = RobotMap.fRPSrightDriveSensor;
	private final Encoder leftDriveEncoder = RobotMap.fRPSleftDriveSensor;
	
	public void moveForward(final double distance){
		rightDriveEncoder.reset();
		leftDriveEncoder.reset();
		Thread rightDriveThread = new Thread() {
			@Override
			public void run() {
				while (distance > rightDriveEncoder.getDistance()) {
					try {
						
					} catch (Exception e) {
					}
				}
			}
		};
		rightDriveThread.start();
		
	}
	
	public double getRawPIDPower(double targetValue, double sensorValue){
		error = targetValue - sensorValue ;
		integral += error;
		derivative = error - previousError;
		if(integral >(.25/kI)){
			integral = (.25/kI);
		}
		
		previousError = error;
		power = (kP * error) + (kI * integral) + (kD * derivative);
		
		if(power > 1){
			power = 1;
		}
		else if( power < -1){
			power = -1;
		}
		stability[stabilityCounter] = error;
		if(stabilityCounter < 9){
			stabilityCounter++;
		}
		else{
		
			tenErrorCheck = true;
			stabilityCounter = 0;
		}
		if(tenErrorCheck){
			for(int i = 0; i < stability.length; i++){
	    		totalError += stability[i];
	    	}
	    	averageError = (totalError/10);
	    	if(averageError <= 1 && averageError >= -1){
	    		stabilized = true;
	    	}
	    	else{
	    		stabilized = false;
	    	}
		}
		else{
			stabilized = false;
		}
		
		
		return power;
	}
	public double getError(){
		return error;
	}
	public double getRawPower(){
		return (kP * error) + (kI * integral) + (kD * derivative);
	}
	public boolean isStabilized(){
		return stabilized;
	}
	private final ADXRS450_Gyro gyro = RobotMap.autonGyro;
	public void setTurn(double degree){
		Robot.drivetrain.getDrive().tankDrive(-getRawPIDPower(degree, gyro.getAngle()), getRawPIDPower(degree, gyro.getAngle()));
	}
	/*public boolean isStablized(double error){
		stability[stabilityCounter] = error;
		if(stabilityCounter < 10){
			stabilityCounter++;
		}
		else{
		
			tenErrorCheck = true;
			stabilityCounter = 0;
		}
		if(tenErrorCheck){
			for(int i = 0; i < stability.length; i++){
	    		totalError += stability[i];
	    	}
	    	averageError = (totalError/10);
	    	if(averageError <= 1 && averageError >= -1){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
		}
		else{
			return false;
		}
		
		
	}*/
	
	
}
