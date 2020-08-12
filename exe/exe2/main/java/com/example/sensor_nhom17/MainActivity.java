package com.example.sensor_nhom17;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;

    SensorManager sensorManager;

    Sensor accelerometerSensor;
    Sensor proximitySensor;
    Sensor lightSensor;
    Sensor stepCounterSensor;
    Sensor tempSensor;
    Sensor gyroscopeSensor;
    private int currentSensor;
    long lastUpdate = 0;
    float last_x, last_y, last_z;
    static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }
    private void setEvent() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    private void setControl() {
        textView = findViewById(R.id.tvResult);
    }

    public boolean checkSensorAvailability(int sensorType) {
        boolean isSensor = false;
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            isSensor = true;
        }
        return isSensor;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == currentSensor) {

            if (currentSensor == Sensor.TYPE_LIGHT) {
                float valueZ = event.values[0];
                textView.setText("Brightness " + valueZ);
            } else if (currentSensor == Sensor.TYPE_PROXIMITY) {
                float distance = event.values[0];
                textView.setText("Proximity " + distance);
            } else if (currentSensor == Sensor.TYPE_STEP_DETECTOR) {
                float steps = event.values[0];
                textView.setText("Steps : " + steps);
            } else if (currentSensor == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {
                        Toast.makeText(getApplicationContext(), "Your phone just shook", Toast.LENGTH_LONG).show();
                    }

                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            } else if (currentSensor == Sensor.TYPE_GYROSCOPE) {
                if (event.values[2] > 0.5f) {
                    textView.setText("Anti Clock");
                } else if (event.values[2] < -0.5f) {
                    textView.setText("Clock");
                }
            } else if (currentSensor == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                textView.setText("Ambient Temp in Celsius :" + event.values[0]);
            }

        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void accelerometerSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_ACCELEROMETER)) {
            currentSensor = Sensor.TYPE_ACCELEROMETER;
        }
        textView.setText("Cảm biến gia tốc nó được sử dụng để đo gia tốc theo 3 chiều");
    }

    public void proximitySensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_PROXIMITY)) {
            currentSensor = Sensor.TYPE_PROXIMITY;
        }
        textView.setText("Mục đích cơ bản của cảm biến tiệm cận proximity sensors là để nhận thức sự xuất hiện của bất kỳ vật nào mà không cần phải tiếp xúc với nó");
    }

    public void gyroscopeSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_GYROSCOPE)) {
            currentSensor = Sensor.TYPE_GYROSCOPE;
        } else {
            textView.setText("Con quay hồi chuyển là một thiết bị dùng để đo đạc hoặc duy trì phương hướng, dựa trên các nguyên tắc bảo toàn mô men động lượng. ... Phương hướng này thay đổi nhiều hay ít tùy thuộc vào mô men xoắn bên ngoài hơn là liên quan đến con quay có vận tốc cao mà không cần mô men động lượng lớn.");
        }
    }

    public void lightSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_LIGHT)) {
            currentSensor = Sensor.TYPE_LIGHT;
        } else {
            textView.setText("Light Sensor not available");
        }
    }


    public void stepCounterOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_STEP_DETECTOR)) {
            currentSensor = Sensor.TYPE_STEP_DETECTOR;
        } else {
            textView.setText("Step Counter Sensor not available");
        }
    }

    public void ambientTempSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_AMBIENT_TEMPERATURE)) {
            currentSensor = Sensor.TYPE_AMBIENT_TEMPERATURE;
        } else {
            textView.setText("Ambient Temperature Sensor not available");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, stepCounterSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, tempSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
