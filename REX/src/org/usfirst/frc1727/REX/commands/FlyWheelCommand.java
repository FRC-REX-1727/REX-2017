package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlyWheelCommand extends Command {
	public static PID pid;
    public FlyWheelCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.flywheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid = new PID(0, 0, 0);
    }

    double targetValue;
    double sensorValue;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get targetValue here
    	sensorValue = Robot.flywheel.getFlyWheelSpeed().getRate();
    	Robot.flywheel.getFlyWheel().set(pid.veloctiyPIDControl( targetValue, sensorValue));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flywheel.getFlyWheel().set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
