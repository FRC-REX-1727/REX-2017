package org.usfirst.frc1727.REX.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleAutonCommandGroup extends CommandGroup {
	private static final double DRIVE_DISTANCE = (((93.3+25.7+5)-40)/4);
	private static final double DRIVE_BACK = (-40/4);
	
    public MiddleAutonCommandGroup() {
    	addSequential(new GearAutonCommand(false));
    	addSequential(new MoveAutonCommand(DRIVE_DISTANCE));
    	addSequential(new GearAutonCommand(true));
    	addSequential(new DelayCommand(0.6));
    	addSequential(new MoveAutonCommand(DRIVE_BACK));
    	addSequential(new DelayCommand(0.6));
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
