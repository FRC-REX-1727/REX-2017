package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RPMControllerCommand extends Command {
	public static boolean increaseFLag;
	public static double tempShooterRPM;
	public static double targetShooterRPM;
    public RPMControllerCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.flywheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    private static boolean heldInc = false;
    private static boolean heldDec = false;
    protected void execute() {
    	/*
    	if(held){
    		
    	}
    	if(Robot.oi.targetRPMDecreaseBtn.get()||Robot.oi.targetRPMIncreaseBtn.get()|| Robot.oi.baseShooterRPMBtn.get()){
    		held = true;
    	}*/
    	if(Robot.oi.targetRPMIncreaseBtn.get()){
    		if(!heldInc){
        		tempShooterRPM += 2000;
        		heldInc = true;
    		}
    	}
    	else{
			//if(heldInc && !Robot.oi.targetRPMIncreaseBtn.get()){
			heldInc = false;
			//}
		}
    	if(Robot.oi.targetRPMDecreaseBtn.get()){
    		if(!heldDec){
        		tempShooterRPM -= 2000;
        		heldDec = true;
    		}
    	}
    	else{
    		heldDec = false;
    	}
    	if(Robot.oi.baseShooterRPMBtn.get()){
    		//tempShooterRPM = 1980;
    		tempShooterRPM = 40000;
    	}
    	if(Robot.oi.shooterStopBtn.get()){
    		tempShooterRPM = 0;
    	}
    	if(tempShooterRPM < 0){
    		tempShooterRPM = 0;
    	}
    	targetShooterRPM = tempShooterRPM;
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
