package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;


import static org.mockito.Mockito.*;


public class TrainSensorTest {
    TrainController mockController;
    TrainUser mockUser;
    TrainSensorImpl sensor;

    @Before
    public void before() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockController, mockUser);
    }

    @Test
    public void overrideSpeedLimit_speedLimitBelowZero_raisesAlarm() {
        sensor.overrideSpeedLimit(-5);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_speedLimitOverFiveHundred_raisesAlarm() {
        sensor.overrideSpeedLimit(600);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_speedLimitOK_doesNotRaiseAlarm() {
        when(mockController.getReferenceSpeed()).thenReturn(200);
        sensor.overrideSpeedLimit(250);
        verify(mockUser, times(0)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_speedLimitToSlowForReferenceSpeed_raisesAlarm() {
        when(mockController.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(mockUser, times(1)).setAlarmState(true);
    }


}
