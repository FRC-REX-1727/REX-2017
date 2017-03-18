package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearAutonCommand extends Command {
	private boolean openFlag;
	private boolean Finished = false;
    public GearAutonCommand(boolean openFlag) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gearHopper);
        this.openFlag = openFlag;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(openFlag){
    		Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kForward);	
    		Finished = true;
    	}	
    	else{
    		Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kReverse);        	
    		Finished = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(openFlag){
    		Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kForward);	
    	}	
    	else{
    		Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kReverse);
    	}
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
