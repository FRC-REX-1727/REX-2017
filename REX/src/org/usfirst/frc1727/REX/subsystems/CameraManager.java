package org.usfirst.frc1727.REX.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

public class CameraManager extends Command{

	private UsbCamera cam1;
	private UsbCamera cam2;
	private CvSink sink1;
	private CvSink sink2;
	private VideoSink server;
	
	boolean cam1selected = true;
	
	public CameraManager() {
//		cam1 = CameraServer.getInstance().startAutomaticCapture(0);
//		cam2 = CameraServer.getInstance().startAutomaticCapture(1);
//		server = CameraServer.getInstance().getServer();
//		
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
