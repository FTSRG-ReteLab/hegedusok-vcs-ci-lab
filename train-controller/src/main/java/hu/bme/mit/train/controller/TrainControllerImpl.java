package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	
	private Table<Integer, String, Integer> tachometer;
	
	public TrainControllerImpl(){
		tachometer = HashBasedTable.create();
	}
	
	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int time = 0;

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}

		enforceSpeedLimit();
		
		tachometer.put(time++, "step", step);
		tachometer.put(time++, "referenceSpeed", referenceSpeed);
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;	
		tachometer.put(time++, "step", step);
	}
	@Override
	public int getJoyPosTime(int time){
		return tachometer.row(time).get("step");
	}
	@Override
	public int getSpeedTime(int time){
		return tachometer.row(time).get("referenceSpeed");
	}

}
