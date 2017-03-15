package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCommand extends Command {
	private static boolean climberUseFlag = false; 
    public ClimberCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    private static double climberValue; 
    protected void execute() {
    	climberValue = Robot.oi.getoperator().getRawAxis(1);
    	if(Robot.oi.climberButton.get()){
    		climberUseFlag = true;
    		Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kForward);
    		Robot.climber.getWinchMotor().set(Math.pow(climberValue, 3));
    		if(Robot.oi.emergencyBreakButton.get()){
        		Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kReverse);
        	}
    	}
    	else{
    		Robot.climber.getWinchMotor().stopMotor();
    		Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kReverse);
    	}
    	
    	if(!climberUseFlag){
    		Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kOff);
    	}
    	
    	
    	/*
    	if(Robot.oi.climberBreakBtn.get()){
    		Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kReverse);
    	}
    	else{
    		Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kForward);
    	}*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.getWinchMotor().stopMotor();
    	Robot.climber.getClimberBreak().set(DoubleSolenoid.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
