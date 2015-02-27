package com.example.miro.patlight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    //flag to detect flash is on or off


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logotemp);
        final Context context = this;
        //Aqui empieza experimento del logo
          Thread logoTimer = new Thread(){
          public void run(){
           try{
              sleep(2000);
               Intent empatiaIntent = new Intent(context, com.example.miro.patlight.empatia.class);
                startActivity(empatiaIntent);


        } catch (InterruptedException e) {
               e.printStackTrace();

           } finally {
           finish();

            }
         }





};


// Aqui ha acabado el experimento del logo


logoTimer.start();


}}


//Aqui empieza experimento del logo
//Thread logoTimer = new Thread(){
//  public void run(){
//    try{
//      sleep(5000);
//    Intent empatiaIntent = new Intent("com.example.miro.patlight.empatia");
//  startActivity(empatiaIntent);

//        } catch (InterruptedException e) {
//          e.printStackTrace();

//    }
//  finally {
//    finish();

//    }
// }





//};


// Aqui ha acabado el experimento del logo


// logoTimer.start();