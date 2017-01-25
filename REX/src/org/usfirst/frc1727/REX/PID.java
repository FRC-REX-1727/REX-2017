package org.usfirst.frc1727.REX;

import java.math.*;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class PID {
	private double kP;
	private double kI;
	private double kD;
	private double error;
	private double integral;
	private double derivative;
	private double previousError;
	private double power;
	public PID(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kP;
		this.kD = kP;
	}
	
	public double veloctiyPIDControl(double targetValue, double sensorValue){
		error = getVelocity(targetValue) - getVelocity(sensorValue);
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
		
		if(Math.abs(power) > 1){
			power = 1;
		}
		
		return power;
	}
	private final AnalogGyro gyro = RobotMap.fRPSyawSensor;
	public void setTurn(double degree){
		Robot.drivetrain.getDrive().tankDrive(-getRawPIDPower(degree, gyro.getAngle()), getRawPIDPower(degree, gyro.getAngle()));
	}
	
	
}
