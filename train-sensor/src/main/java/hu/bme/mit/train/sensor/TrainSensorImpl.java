package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import java.time.LocalDateTime;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table<LocalDateTime, Integer, Integer> tacho;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
		this.tacho = HashBasedTable.create();
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public int getTableSize() {
		return  tacho.size();
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

	@Override
	public void writeTachoGraph (int joystickPosition, int referenceSpeed){
		tacho.put(LocalDateTime.now(), joystickPosition, referenceSpeed);

	}

}
