package hu.bme.mit.train.interfaces;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	void writeTachoGraph (int joystickPosition, int referenceSpeed);

	int getTableSize();

}
