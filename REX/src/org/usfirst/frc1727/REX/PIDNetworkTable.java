package org.usfirst.frc1727.REX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class PIDNetworkTable {
	NetworkTable table = NetworkTable.getTable("PIDdatatable");
	
	public void setPIDDatatable(){
		table.putNumber("time", Timer.getFPGATimestamp());
		table.putNumber("SensorValue", Robot.flywheel.getTalonFlywheelSpeed().getEncVelocity());
		Timer.delay(0.25);
	}
	
}
