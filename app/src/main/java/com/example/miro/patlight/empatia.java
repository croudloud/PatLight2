package com.example.miro.patlight;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class empatia extends Activity {

    TextView textLIGHT_available, textLIGHT_reading;
    //flag to detect flash is on or off
    private boolean isLighOn = false;
    private boolean TextOn = true;

    private Camera camera;
    private Button button;
    private Button button2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button2 = (Button) findViewById(R.id.buttonSensor);


        this.button = (Button) findViewById(R.id.buttonFlashlight);


        // if device support camera?
        if (!getPackageManager().hasSystemFeature("android.hardware.camera"))
        {
            Log.e("err", "Device has no camera!");
            return;
        }
        // finishes check of support camera


        camera = Camera.open();
        final Camera.Parameters localParameters = camera.getParameters();
        Button button = this.button;
        View.OnClickListener local1 = new View.OnClickListener() {

            @Override
            public void onClick(View paramAnonymousView) {

                if (isLighOn) {

                    Log.i("info", "torch is turn off!");
                    localParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(localParameters);
                    camera.stopPreview();
                    isLighOn = false;
                    return;

                } {

                    Log.i("info", "torch is turn on!");

                    localParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                    camera.setParameters(localParameters);
                    camera.startPreview();
                    isLighOn = true;

                }

            }

        };
        final Camera.Parameters localParameters3 = camera.getParameters();
        Button button2 = this.button2;
        View.OnClickListener local2 = new View.OnClickListener() {


            @Override
            public void onClick(View paramAnonymousView) {

                textLIGHT_available
                        = (TextView)findViewById(R.id.LIGHT_available);
                textLIGHT_reading
                        = (TextView)findViewById(R.id.LIGHT_reading);

                SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
                Sensor LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


                if (TextOn) {
                    textLIGHT_reading.setVisibility(View.VISIBLE);
                    textLIGHT_available.setText("El sensor de luz funciona correctamente. La luz se encenderá a 40 o menos.");
                    mySensorManager.registerListener(
                            LightSensorListener,
                            LightSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);




                    TextOn = false;
                    return;

                }  {
                    textLIGHT_available.setText("");
                    textLIGHT_reading.setVisibility(View.INVISIBLE);
                    localParameters3.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(localParameters3);
                    camera.stopPreview();

                    TextOn = true;



                }






            }
        };




        button.setOnClickListener(local1);
        button2.setOnClickListener(local2);
    }

    private final SensorEventListener LightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {

            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                textLIGHT_reading.setText("Sensor: " + event.values[0]);
                Camera.Parameters localParameters2 = camera.getParameters();
                float lux = event.values[0];
                if(lux <= 40){

                    localParameters2.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(localParameters2);
                    camera.startPreview();
return;

            }{
                    localParameters2.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(localParameters2);
                    camera.stopPreview();
                }
            }
        }

    };





    protected void onStop()
    {
        super.onStop();
        if (this.camera != null) {
            this.camera.release();
        }


    }


}