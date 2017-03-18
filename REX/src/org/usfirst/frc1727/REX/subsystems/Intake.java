package org.usfirst.frc1727.REX.subsystems;

import org.usfirst.frc1727.REX.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    private final SpeedController flywheelIntake = RobotMap.flywheelIntake;
    private final Relay basketIntake = RobotMap.basketIntake;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public SpeedController getFlywheelIntake(){
    	return flywheelIntake;
    }
    public Relay getBasketIntake(){
    	return basketIntake;
    }
}

