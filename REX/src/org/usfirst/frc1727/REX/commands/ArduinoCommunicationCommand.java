package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArduinoCommunicationCommand extends Command {
	I2C i2cComm;
	
    public ArduinoCommunicationCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arduinoCommunication);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i2cComm = new I2C(I2C.Port.kOnboard, OI.DEVICE_ADDRESS);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//i2cComm.transaction(dataToSend, sendSize, dataReceived, receiveSize)
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
