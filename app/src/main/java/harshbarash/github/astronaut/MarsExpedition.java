package harshbarash.github.astronaut;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MarsExpedition extends AppCompatActivity implements SensorEventListener {
    Button btn;
    private TextView texttemp, barometr;
    private SensorManager sensorManager;
    private Sensor tempSensor, pressureSensor;
    private Boolean isTemperatureSsensoreAvailable;
    ImageButton btnm1, btnm2;


    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            barometr.setText(String.format("%.3f мбар", values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_researches);

        btn = (Button)findViewById( R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MarsExpedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        barometr = findViewById(R.id.barometr);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        texttemp = findViewById(R.id.texttemp);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!= null)
        {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSsensoreAvailable = true;
        } else {
            texttemp.setText(":(");
            isTemperatureSsensoreAvailable = false;
        }

        btnm1 = (ImageButton)findViewById(R.id.btn);
        btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MarsExpedition.this, Rover.class);
                startActivity(i);
                finish();
            }
        });

        btnm2 = (ImageButton)findViewById(R.id.btn2);
        btnm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MarsExpedition.this, Rover2.class);
                startActivity(i);
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        texttemp.setText(sensorEvent.values[0]+"°");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //реализовываем цикл жизни андройд приложения

    @Override
    protected void onResume() {
        super.onResume();
        if (isTemperatureSsensoreAvailable) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(sensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_UI);


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTemperatureSsensoreAvailable) {
            sensorManager.unregisterListener(this);
            sensorManager.unregisterListener(sensorEventListener);

        }
    }
}