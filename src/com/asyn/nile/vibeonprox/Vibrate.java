package com.asyn.nile.vibeonprox;

import android.content.Context;
import android.os.Vibrator;

public class Vibrate {
	
	private static final int VIB_TIME = 5 * 1000; // multiplied by 1000 cause the time is in milliseconds
	
	private Vibrator vibrator;

	public Vibrate(Context context) {
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void startVibration() {
		vibrator.vibrate(VIB_TIME);
	}
	
	public void stopVibration() {
		vibrator.cancel();
	}

}
