package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.Robot;
import org.usfirst.frc1727.REX.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveSonarAutonCommand extends Command {
	private final Encoder rightDriveEncoder = RobotMap.fRPSrightDriveSensor;
	private final Encoder leftDriveEncoder = RobotMap.fRPSleftDriveSensor;
	private PID moveSonarR;
	private PID moveSonarL;
	private double powerR;
	private double powerL;

    public MoveSonarAutonCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	moveSonarR = new PID(OI.DRIVE_KP, OI.DRIVE_KI, OI.DRIVE_KD);
    	moveSonarL = new PID(OI.DRIVE_KP, OI.DRIVE_KI, OI.DRIVE_KD);
    	rightDriveEncoder.reset();
    	leftDriveEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	powerR = moveSonarR.getRawPIDPower(OI.ROBOT_CONTACTPOINT, rightDriveEncoder.getDistance());
    	powerL = moveSonarR.getRawPIDPower(OI.ROBOT_CONTACTPOINT, rightDriveEncoder.getDistance());
    	Robot.drivetrain.getLeftDrive().set(powerL);
		Robot.drivetrain.getRightDrive().set(powerR);	

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return moveSonarR.isStabilized();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getLeftDrive().set(0);
		Robot.drivetrain.getRightDrive().set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
