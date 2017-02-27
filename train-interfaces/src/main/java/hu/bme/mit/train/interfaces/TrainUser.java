package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();

	int getReferenceSpeed();

	boolean getAlarmFlag();

	void overrideJoystickPosition(int joystickPosition);

}
