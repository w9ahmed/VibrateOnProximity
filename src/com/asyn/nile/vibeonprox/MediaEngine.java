package com.asyn.nile.vibeonprox;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaEngine {
	
	private MediaPlayer mediaPlayer;
	private Context context;
	private int soundFile;
	
	public MediaEngine(Context context, int soundFile) {
		this.context = context;
		this.soundFile = soundFile;
		reinstate();
	}
	
	public void play() {
		reinstate();
		mediaPlayer.start();
	}
	
	public void stop() {
		if(mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			destroy();
		}
	}
	
	public void destroy() {
		if(mediaPlayer != null) {
			mediaPlayer.reset();
			mediaPlayer.release();
		}
	}
	
	public void reinstate() {
		mediaPlayer = MediaPlayer.create(context, soundFile);
		mediaPlayer.setLooping(true);
	}

}
