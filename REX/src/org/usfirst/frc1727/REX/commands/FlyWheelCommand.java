package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.OI;
import org.usfirst.frc1727.REX.PID;
import org.usfirst.frc1727.REX.PIDNetworkTable;
import org.usfirst.frc1727.REX.Robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.*;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlyWheelCommand extends Command {
	//public static PID pid;
	private double[] stability = new double[10];
	public static PIDNetworkTable pidNetworktable;
	private double targetValue;
	public FlyWheelCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//pid = new PID(0, 0, 0);
    	
    	pidNetworktable = new PIDNetworkTable();
    }

    private static boolean tenErrorCheck = false;
    private static int stabilityCounter = 0;
    private static double averageError = 0;
    private static double totalError = 0;
    //double sensorValue;
    // Called repeatedly when this Command is scheduled to run
    
    protected void execute() {
    	
    	//get targetValue here
    	//sensorValue = Robot.flywheel.getFlyWheelSpeed().getRate();
    	//Robot.flywheel.getFlyWheel().set(pid.veloctiyPIDControl( targetValue, sensorValue));
    	
    	
//    	stability[stabilityCounter] = Robot.flywheel.getTalonFlywheelSpeed().getError();
//		if(stabilityCounter < 10){
//			stabilityCounter++;
//		}
//		else{
//		
    	//tenErrorCheck = true;
//			stabilityCounter = 0;
//		}
//		if(tenErrorCheck){
//			for(int i = 0; i < stability.length; i++){
//	    		totalError += stability[i];
//	    	}
//	    	averageError = (totalError/10);
//	    	if(averageError <= 1 && averageError >= -1){
//	    		
//	    	}
//	    	else{
//	    		Robot.flywheel.getFlywheelIntake().set(0);
//	    	}
//		}
		if(Robot.oi.flyWheelButton.get()){
			Robot.intake.getFlywheelIntake().set(1.0);
			Robot.intake.getBasketIntake().set(Relay.Value.kForward);
		}
		else
		{
			Robot.intake.getFlywheelIntake().stopMotor();
			Robot.intake.getBasketIntake().set(Relay.Value.kOff);
			
		}
		SmartDashboard.putNumber("DB/String 0", Robot.flywheel.getTalonFlywheelSpeed().getError());
		/*System.out.println("FLYWHEEL: setPower: " + RPMControllerCommand.targetShooterRPM 
				+ " closeCloopError: " + Robot.flywheel.getTalonFlywheelSpeed().getClosedLoopError() 
				+ " speed: " + Robot.flywheel.getTalonFlywheelSpeed().getEncVelocity() 
				+ " error: " + Robot.flywheel.getTalonFlywheelSpeed().getError());*/
		//System.out.println("intake task running");
    	pidNetworktable.setPIDDatatable();
    	//Robot.flywheel.getFlyWheel().set(127);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.intake.getFlywheelIntake().stopMotor();
    	Robot.intake.getBasketIntake().set(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
