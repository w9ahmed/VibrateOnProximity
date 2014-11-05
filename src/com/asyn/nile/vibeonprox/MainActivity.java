package com.asyn.nile.vibeonprox;

import com.asyn.nile.proximitycounter.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity {
	
	private SensorProx sensorProx;
	private TextView proxText;
	private ToggleButton vibrate;
	private ToggleButton hornFX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		proxText = (TextView) findViewById(R.id.proxVal);
		vibrate = (ToggleButton) findViewById(R.id.vibrateButton);
		hornFX = (ToggleButton) findViewById(R.id.hornButton);
		
		sensorProx = new SensorProx(this, proxText);
		sensorProx.setToggleButtons(vibrate, hornFX);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		sensorProx.resumeSensor();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorProx.pauseSensor();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
