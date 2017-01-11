package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.Robot;
import org.usfirst.frc1727.REX.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainCommand extends Command {

    public DrivetrainCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    double leftValue;
    double rightValue;
    protected void execute() {
    	leftValue = Robot.oi.getdriver().getRawAxis(OI.DRIVER_LEFT_VERTICAL);
    	rightValue = Robot.oi.getdriver().getRawAxis(OI.DRIVER_RIGHT_VERTICAL);
    	Robot.drivetrain.getDrive().tankDrive(leftValue, rightValue);
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
