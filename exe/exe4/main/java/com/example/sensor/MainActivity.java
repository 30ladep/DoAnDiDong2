package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends Activity {
    private static SensorManager sensorService;
    private MyCompassView compass;
    private Sensor sensor = null;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensor != null)
            sensorService.unregisterListener(mySensorEventListener);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compass = new MyCompassView(this);
        setContentView(compass);
        sensorService = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null)
            sensorService.registerListener(mySensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        else
            finish();
    }
    private SensorEventListener mySensorEventListener =
            new SensorEventListener() {
                @Override
                public void onAccuracyChanged(Sensor s, int accuracy) {
                }
                @Override
                public void onSensorChanged(SensorEvent event) {
                    compass.updateData(event.values[0]);
                }
            };




}
