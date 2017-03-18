package org.usfirst.frc1727.REX.commands;

import org.usfirst.frc1727.REX.Robot;
import org.usfirst.frc1727.REX.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSideAutonCommandGroup extends CommandGroup {
	private static final double DRIVE_DISTANCE = ((53.3 + 39 + 13)/4);
	private static final double DRIVE_DISTANCE_TWO = (25/4);
	private static final double TURN_ANGLE = -59;
	private static final double DRIVE_BACK = (-10/4);
	//private final Encoder rightDriveEncoder = RobotMap.fRPSrightDriveSensor;
	//private final Encoder leftDriveEncoder = RobotMap.fRPSleftDriveSensor;
	Command firstMove = new MoveAutonCommand(((53.3 + 39 + 13)/4));
	//Command secondMove = 
	private boolean moved = false;
	//76.394
    public RightSideAutonCommandGroup() {
    	addSequential(new GearAutonCommand(false));
    	addSequential( new MoveAutonCommand(DRIVE_DISTANCE));
    	
    	moved = true;
    	
    	System.out.println("ended");
    	
    	addSequential(new TurnPIDCommand(TURN_ANGLE));
    	addSequential(new MoveAutonCommand(DRIVE_DISTANCE_TWO));
    	
    	
    	/*while((DRIVE_DISTANCE/4) > ((rightDriveEncoder.getDistance() + leftDriveEncoder.getDistance())/2)){
		Robot.drivetrain.getLeftDrive().set(.8);
		Robot.drivetrain.getRightDrive().set(.8);
		
	}*/
    	//addP(new MoveAutonCommand(((53.3 + 39 + 13)/4)));
    	/*
    	if(moved){
    		addSequential(new MoveAutonCommand(40/2));
    	}
    	*/
    	addSequential(new GearAutonCommand(true));
    	//Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kForward);
    	
    	addSequential(new MoveAutonCommand(DRIVE_BACK));
    	addSequential(new GearAutonCommand(false));
    	//Robot.gearHopper.getHopperRelease().set(DoubleSolenoid.Value.kReverse);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
