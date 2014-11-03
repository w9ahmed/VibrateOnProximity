package com.asyn.nile.vibeonprox;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class SensorProx implements SensorEventListener {
	
	private static final String FAR = "FAR";
	private static final String NEAR = "NEAR";
	
	private SensorManager mSensorManager;
	private Sensor mProximity;
	private TextView displayer;
	private Vibrate vibrate;
	
	public SensorProx(Context context, TextView textView) {
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		displayer = textView;
		vibrate = new Vibrate(context);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float distance = event.values[0];
		if(distance == 0.0) {
			displayer.setText(NEAR);
			vibrate.startVibration();
		} else {
			displayer.setText(FAR);
			vibrate.stopVibration();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	
	public void pauseSensor() {
		mSensorManager.unregisterListener(this);
		vibrate.stopVibration();
	}
	
	public void resumeSensor() {
		mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
	}

}
