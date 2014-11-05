package com.asyn.nile.vibeonprox;

import com.asyn.nile.proximitycounter.R;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SensorProx implements SensorEventListener {
	
	private static final String FAR = "FAR";
	private static final String NEAR = "NEAR";
	
	private static final int HORN_SOUND = R.raw.carhornsoundeffect;
	
	private SensorManager mSensorManager;
	private Sensor mProximity;
	private TextView displayer;
	private Vibrate vibrate;
	private MediaEngine media;
	
	private ToggleButton vibrateButton;
	private ToggleButton hornFXButton;
	
	public SensorProx(Context context, TextView textView) {
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		displayer = textView;
		vibrate = new Vibrate(context);
		media = new MediaEngine(context, HORN_SOUND);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float distance = event.values[0];
		if(distance == 0.0) {
			displayer.setText(NEAR);
			if(vibrateButton != null && hornFXButton != null) {
				if(vibrateButton.isChecked())
					vibrate.startVibration();
				if(hornFXButton.isChecked())
					media.play();
			}
		} else {
			displayer.setText(FAR);
			vibrate.stopVibration();
			if(hornFXButton.isChecked())
				media.stop();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	
	public void pauseSensor() {
		mSensorManager.unregisterListener(this);
		vibrate.stopVibration();
		media.destroy();
	}
	
	public void resumeSensor() {
		mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
		media.reinstate();
	}
	
	public void setToggleButtons(ToggleButton v, ToggleButton h) {
		vibrateButton = v;
		hornFXButton = h;
	}

}
