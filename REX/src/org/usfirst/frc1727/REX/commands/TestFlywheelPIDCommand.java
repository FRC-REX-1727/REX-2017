package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestFlywheelPIDCommand extends Command {
	private PID testFlywheelPID;
    private static double targetVelocity;
    private static double power;
    private static boolean autonFlag = false;
	public TestFlywheelPIDCommand() {
        // Use requires() here to declare subsystem dependencies
        autonFlag = false;
		requires(Robot.flywheel);
        
    }
	public TestFlywheelPIDCommand(double targetPower){
		targetVelocity = targetPower;
		autonFlag = true;
		requires(Robot.flywheel);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	testFlywheelPID = new PID(OI.FLYWHEEL_KP, OI.FLYWHEEL_KI, OI.FLYWHEEL_KD);
    	Robot.flywheel.getTalonFlywheelSpeed().changeControlMode(TalonControlMode.PercentVbus);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*if(!autonFlag){
    		targetVelocity = RPMControllerCommand.targetShooterRPM;
    	}*/
    	targetVelocity = RPMControllerCommand.targetShooterRPM;
    	
    	if(targetVelocity == 0){
    		power = 0;
    	}
    	else{
    		power = testFlywheelPID.veloctiyPIDControl(targetVelocity, Robot.flywheel.getTalonFlywheelSpeed().getEncVelocity());	
    	}
    	
    	
    	Robot.flywheel.getTalonFlywheelSpeed().set(-power);
    	
    	
    	/*
    	System.out.println(
    			"\t\tEncoder Velocity: " +  Robot.flywheel.getTalonFlywheelSpeed().getEncVelocity() + 
    			"\t\t power: " + power +
    			"\t\t targetVelocity" + targetVelocity +
    			"\t\t RPM" + RPMControllerCommand.targetShooterRPM
    			);
*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flywheel.getTalonFlywheelSpeed().disable();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
