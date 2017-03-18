package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAutonCommand extends Command {
	private double targetAngle;
	
	private PID turnPID;
    public TurnAutonCommand(double targetAngle) {
        // Use requires() here to declare subsystem dependencies	
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnPID = new PID(OI.TURN_KP, OI.TURN_KI, OI.TURN_KD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnPID.setTurn(targetAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnPID.isStabilized();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getDrive().stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
