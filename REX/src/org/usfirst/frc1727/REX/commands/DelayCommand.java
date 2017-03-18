package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayCommand extends Command {
	private boolean isDone = false;
	private double delaytimer;
    public DelayCommand(double delaytimer) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        this.delaytimer = delaytimer;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Timer.delay(delaytimer);
    	isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
