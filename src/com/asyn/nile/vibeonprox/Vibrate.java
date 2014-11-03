package com.asyn.nile.vibeonprox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;

public class Vibrate {
	
	private static final int VIB_TIME = 5 * 1000; // multiplied by 1000 cause the time is in milliseconds
	
	private Vibrator vibrator;

	public Vibrate(Context context) {
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	@SuppressLint("NewApi")
	public void startVibration() {
		if(vibrator.hasVibrator())
			vibrator.vibrate(VIB_TIME);
	}
	
	public void stopVibration() {
		vibrator.cancel();
	}

}
