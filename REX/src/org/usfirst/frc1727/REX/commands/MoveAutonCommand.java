package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.Robot;
import org.usfirst.frc1727.REX.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveAutonCommand extends Command {
	private double targetDistance;
	private final Encoder rightDriveEncoder = RobotMap.fRPSrightDriveSensor;
	private final Encoder leftDriveEncoder = RobotMap.fRPSleftDriveSensor;
	/*
	private double errorR;
	private double integralR;
	private double derivativeR;
	private double previousErrorR;
	private double powerR;
	private double kP;
	private double kI;
	private double kD;
	private double adjustPowerR;
	private double errorL;
	private double integralL;
	private double derivativeL;
	private double previousErrorL;
	private double powerL;
	private double adjustPowerL;
	private double error;
	private double integral;
	private double derivative;
	private double previousError;
	private double power;
	private double correctionPower;
	*/
	private PID rightDrivePID;
	private PID leftDrivePID;
	private double powerR;
	private double powerL;
	
	private double[] stability = new double[10];
	private int stabilityCounter;
	private boolean tenErrorCheck = false;
	private double averageError;
	private double totalError;
	
    public MoveAutonCommand(double targetDistance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    	this.targetDistance =((int)(targetDistance * OI.INCH_TO_PULSE));
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*
    	kP = OI.DRIVE_KP;
    	kI = OI.DRIVE_KI;
    	kD = OI.DRIVE_KD;*/
    	rightDrivePID = new PID(OI.DRIVE_KP, OI.DRIVE_KI, OI.DRIVE_KD);
    	leftDrivePID = new PID(OI.DRIVE_KP, OI.DRIVE_KI, OI.DRIVE_KD);;
    	rightDriveEncoder.reset();
    	leftDriveEncoder.reset();
    	for(int i = 0; i<stability.length; i++){
    		stability[i] = targetDistance;
    	}
    }
    private boolean finish = false;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	powerR = rightDrivePID.getRawPIDPower(targetDistance, rightDriveEncoder.getDistance());
    	powerL = leftDrivePID.getRawPIDPower(targetDistance, leftDriveEncoder.getDistance());
    	stability[stabilityCounter] = ((leftDrivePID.getError() + rightDrivePID.getError())/2);
		if(stabilityCounter < 9){
			stabilityCounter++;
		}
		else{
		
			tenErrorCheck = true;
			
			stabilityCounter = 0;
		}
		//System.out.println(stability);
    	/*
    	while(targetDistance > ((rightDriveEncoder.getDistance() + leftDriveEncoder.getDistance())/2)){
    		Robot.drivetrain.getLeftDrive().set(.8);
    		Robot.drivetrain.getRightDrive().set(.8);
    		
    	}
    	finish = true;
    	System.out.println("true");
    	/*
    	errorR = targetDistance - rightDriveEncoder.getDistance();
		integralR += errorR;
		derivativeR = errorR - previousErrorR;
		if (integralR > (.25 / kI)) {
			integralR = (.25 / kI);
		}

		previousErrorR = errorR;

		powerR = (kP * errorR) + (kI * integralR) + (kD * derivativeR);

		if (powerR > 1) {
			powerR = 1;
		} else if (powerR < -1) {
			powerR = -1;
		}
    	
    	errorL = targetDistance - rightDriveEncoder.getDistance();
		integralL += errorL;
		derivativeL = errorL - previousErrorL;
		if (integralL > (.25 / kI)) {
			integralL = (.25 / kI);
		}

		previousErrorL = errorL;

		powerL = (kP * errorL) + (kI * integralL) + (kD * derivativeL);

		if (powerL > 1) {
			powerL = 1;
		} else if (powerL < -1) {
			powerL = -1;
		}*/
		Robot.drivetrain.getDrive().tankDrive(powerL, -powerR);
	
		System.out.println("Left Encoder: " +  leftDriveEncoder.getDistance() + " Right Encoder: " + rightDriveEncoder.getDistance() + "\t errorR: " + rightDrivePID.getError() + "\t errorL" + leftDrivePID.getError() + "\t powerL" + powerL + "powerR" + powerR);
    	/*
    	rightDriveThread.start();
    	leftDriveThread.start();
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(averageError);
    	totalError = 0;
    	if(tenErrorCheck){
			for(int i = 0; i < stability.length; i++){
	    		totalError += stability[i];
	    	}
	    	averageError = (totalError/10);
	    	if(averageError <= 400 && averageError >= -400){
	    		System.out.println("stablized, returned true");
	    		return true;
	    		
	    	}
	    	else{
	    		return false;
	    	}
		}
		else{
			return false;
		}
		
    	//return finish;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
		Robot.drivetrain.getDrive().tankDrive(0, 0);
		System.out.println("ended task");
	  	rightDriveEncoder.reset();
    	leftDriveEncoder.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
    protected void interrupted() {
    }
    /*
    Thread rightDriveThread = new Thread() {
		@Override
		public void run() {
			try {
				errorR = targetDistance - rightDriveEncoder.getDistance();
				integralR += errorR;
				derivativeR = errorR - previousErrorR;
				if (integralR > (.25 / kI)) {
					integralR = (.25 / kI);
				}

				previousErrorR = errorR;

				powerR = (kP * errorR) + (kI * integralR) + (kD * derivativeR);

				if (powerR > 1) {
					powerR = 1;
				} else if (powerR < -1) {
					powerR = -1;
				}
				adjustPowerR = powerR;
			} catch (Exception e) {
			}	
		}
	};
	Thread leftDriveThread = new Thread() {
		@Override
		public void run() {
			try {
				errorL = targetDistance - rightDriveEncoder.getDistance();
				integralL += errorL;
				derivativeL = errorL - previousErrorL;
				if (integralL > (.25 / kI)) {
					integralL = (.25 / kI);
				}

				previousErrorL = errorL;

				powerL = (kP * errorL) + (kI * integralL) + (kD * derivativeL);

				if (powerL > 1) {
					powerL = 1;
				} else if (powerL < -1) {
					powerL = -1;
				}
				adjustPowerL = powerL;
			} catch (Exception e) {
			}
		}
	};
	//while (targetDistance > leftDriveEncoder.getDistance()) 
	Thread coureAdjustionThread = new Thread() {
		@Override
		public void run() {
			try {
				
			} catch (Exception e) {
			}
		}
	};
*/
}
