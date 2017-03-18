package org.usfirst.frc1727.REX.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSideAutonCommandGroup extends CommandGroup {
	private static final double DRIVE_DISTANCE = ((53.3 + 39 + 13)/4);
	private static final double DRIVE_DISTANCE_TWO = (25/4);
	private static final double TURN_ANGLE = 59;
	private static final double DRIVE_BACK = 10;
	
    public LeftSideAutonCommandGroup() {
    	addSequential(new GearAutonCommand(false));
    	addSequential( new MoveAutonCommand(DRIVE_DISTANCE));
    	
    	
    	System.out.println("ended");
    	
    	addSequential(new TurnPIDCommand(TURN_ANGLE));
    	addSequential(new MoveAutonCommand(DRIVE_DISTANCE_TWO));
    	addSequential(new GearAutonCommand(true));
    	addSequential(new MoveAutonCommand(DRIVE_BACK));
    	addSequential(new GearAutonCommand(false));
    	
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
