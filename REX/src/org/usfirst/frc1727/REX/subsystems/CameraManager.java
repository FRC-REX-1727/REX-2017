package org.usfirst.frc1727.REX.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraManager {

	private UsbCamera gearCamera;
	private UsbCamera gearWideAngleCamera;
	private CvSink gearSink;
	private CvSink climberSink;
	private VideoSink server;
	public enum Camera {
		GEAR,
		CLIMBER;
	};
	
	Camera current;
	
	public CameraManager(){
		gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
		gearWideAngleCamera = CameraServer.getInstance().startAutomaticCapture(1);
		server = CameraServer.getInstance().getServer();
		//Dummy sinks to keep cams open
		gearSink = new CvSink("Gear");
		gearSink.setSource(gearCamera);
		gearSink.setEnabled(true);
		climberSink = new CvSink("Climber");
		climberSink.setSource(gearWideAngleCamera);
		climberSink.setEnabled(true);
		setCamera(Camera.CLIMBER);
	}
	
	public void disable() {
		//No disable
	}

	public void setCamera(Camera camera) {
		switch(camera){
		case GEAR:server.setSource(gearCamera);break;
		case CLIMBER:server.setSource(gearWideAngleCamera);break;
		}
		current = camera;
	}

	public Camera getCamera() {
		return current;
	}
	
	public void toggleCamera(){
		switch(current){
		case GEAR: setCamera(Camera.CLIMBER);break;
		case CLIMBER: setCamera(Camera.GEAR);break;
		}
	}

}