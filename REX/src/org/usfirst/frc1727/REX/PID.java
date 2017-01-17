package org.usfirst.frc1727.REX;

import java.math.*;

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
		if(integral >(50/kI)){
			integral = (50/kI);
		}
		if(Math.abs(error) < 200){
			integral = 0;
		}
		previousError = error;
		
		power = (kP * error) + (kI * integral) + (kD * derivative);
		
		if(power > 127){
			power = 127;
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
	
}
