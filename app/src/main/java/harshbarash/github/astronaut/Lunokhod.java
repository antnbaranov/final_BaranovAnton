package harshbarash.github.astronaut;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;
import java.util.Locale;

import static android.hardware.Sensor.TYPE_LIGHT;

public class Lunokhod extends Activity implements LocationListener, SensorEventListener  {

        Button btn;

        private SensorManager sensorManager;
        private Sensor lightSensor, mGravity;
        private SensorEventListener lightEventListener;
        private View light;
        private float maxValue;
        private TextView textView, speed, magnit;
        public static DecimalFormat DECIMAL_FORMATTER;
        private TextView xTextView, yTextView, zTextView;
        private boolean isGravitySensorPresent;
        private ImageView ImageView;
        private float[] mmGravity = new float[3];
        private float[] mGeomagnetic = new float[3];
        private float azimuth = 0f;
        private float currectAzimuth = 0f;

        private ImageView image;
        private float currentDegree = 0f;
        private SensorManager mSensorManager;


        @SuppressLint({"ObsoleteSdkInt", "CutPasteId"})
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.lunokhod2);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                light = findViewById(R.id.light);
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
                textView = findViewById(R.id.lighttext);
                speed = findViewById(R.id.speed);
                magnit = findViewById(R.id.magnittext);
                // разпосзнать формат
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ROOT);
                symbols.setDecimalSeparator('.');
                DECIMAL_FORMATTER = new DecimalFormat("#.000", symbols);
                xTextView = findViewById(R.id.gravityX);
                yTextView = findViewById(R.id.gravityY);
                zTextView = findViewById(R.id.gravityZ);
                ImageView = (ImageView)findViewById(R.id.compass);

                image = (ImageView) findViewById(R.id.compass);

//                tvHeading  = (TextView)findViewById(R.id.tvHeading);


                mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

                if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!= null)
                {
                        mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
                        isGravitySensorPresent = true;
                } else {
                        xTextView.setText("Датчик остутсвует");
                        isGravitySensorPresent = false;
                }

                if(Build.VERSION.SDK_INT >= VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        !=PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
                }
                else{
                        doSpeed(); //если есть все разрешеня, выполняем программу
                }

                this.updateSpeed(null);


                if (lightSensor == null) {
                        Toast.makeText(this, "Датчик отсутсвует", Toast.LENGTH_SHORT).show();
                }

                maxValue = lightSensor.getMaximumRange();

                lightEventListener = new SensorEventListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSensorChanged(SensorEvent event) {
                                float value = event.values[0];
                                textView.setText( value + "лк");
                                int newValue = (int) (255 * value / maxValue);
                                light.setBackgroundColor(Color.rgb(newValue, newValue, newValue));

                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int accuracy) {

                        }
                };



                btn = (Button)findViewById( R.id.change);
                btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent i = new Intent(Lunokhod.this, MoonExpedition.class);
                                startActivity(i);
                                finish();
                        }
                });

        }

        @Override
        protected void onResume() {
                super.onResume();
                sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
                sensorManager.registerListener((SensorEventListener) this,
                        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                        SensorManager.SENSOR_DELAY_NORMAL);

                if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null);
                sensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_FASTEST);

                mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                        SensorManager.SENSOR_DELAY_GAME);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                        //даем значение xyz
                        float magX = event.values[0];
                        float magY = event.values[1];
                        float magZ = event.values[2];

                        double magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));

                        magnit.setText(DECIMAL_FORMATTER.format(magnitude) + " \u00B5Тесла");
                }
                xTextView.setText(event.values[0]+ "М/c²");
                yTextView.setText(event.values[1]+ "М/c²");
                zTextView.setText(event.values[2]+ "М/c²");

                float degree = Math.round(event.values[0]);

//                tvHeading.setText((int) degree);

                RotateAnimation ra = new RotateAnimation(
                        currentDegree,
                        -degree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);

                ra.setDuration(210);

                ra.setFillAfter(true);

                image.startAnimation(ra);
                currentDegree = -degree;
                        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        protected void onPause() {
                super.onPause();
                sensorManager.unregisterListener(lightEventListener);
                sensorManager.unregisterListener(this);
                if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null);
                sensorManager.unregisterListener(this, mGravity);
                sensorManager.unregisterListener(this);

                mSensorManager.unregisterListener(this);
        }



        @Override
        public void onLocationChanged(@NonNull Location location) {
                if (location != null) {
                        CLocation myLocation = new CLocation(location);
                        this.updateSpeed(myLocation);
                }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider){

        }

        @SuppressLint("MissingPermission")
        private void doSpeed() {
                LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                if (locationManager != null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                }
//        Toast.makeText(this, "Ожидаем подключение к GPS", Toast.LENGTH_SHORT).show();
        }

        @SuppressLint("SetTextI18n")
        private void updateSpeed(CLocation location) {
                float nCurrentSpeed = 0;

                if(location != null) {
                        nCurrentSpeed = location.getSpeed();
                }

                Formatter fmt = new Formatter(new StringBuilder());
                fmt.format(Locale.ROOT, "%4.1f", nCurrentSpeed);
                String strCurrentSpeed = fmt.toString();
                strCurrentSpeed = strCurrentSpeed.replace(" ", "0");

                speed.setText(strCurrentSpeed + "км/ч" );
        }


        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if (requestCode == 1000) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                doSpeed();
                        }
                        else {
                                finish();
                        }
                }
        }

}


