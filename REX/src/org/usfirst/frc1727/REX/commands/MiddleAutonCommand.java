package org.usfirst.frc1727.REX.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleAutonCommand extends Command {
	private CommandGroup middleAutonCommandGroup;
    public MiddleAutonCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	middleAutonCommandGroup = new MiddleAutonCommandGroup();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	middleAutonCommandGroup.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
