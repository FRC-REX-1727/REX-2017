package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OpenGearHopperCommand extends Command {

    public OpenGearHopperCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHopper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//Robot.gearHopper.getGearCompressor().start();
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.gearButton.get()){
    		Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kReverse);
    	}
    	if(Robot.oi.gearCatcherButton.get()){
    		Robot.gearHopper.getGearCatcher().set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		Robot.gearHopper.getGearCatcher().set(DoubleSolenoid.Value.kReverse);	
    	}
    	/*
    	if(Robot.gearHopper.getGearCompressor().enabled()){
    		SmartDashboard.putString("DB/String 1", "the compressor is on");
    		System.out.println("the compressor is on");
    		
    	}
    	else if(Robot.gearHopper.getGearCompressor().enabled()){
    		SmartDashboard.putString("DB/String 2", "the compressor is off");
    		System.out.println("the compressor is off");
    	}
    	*/
    	
    } 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kOff);
    	Robot.gearHopper.getGearCompressor().setClosedLoopControl(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
