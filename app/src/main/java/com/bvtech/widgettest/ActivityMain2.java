package com.bvtech.widgettest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bvtech.toolslibrary.utility.FileUtil;
import com.bvtech.toolslibrary.utility.ViewUtility;
import com.bvtech.toolslibrary.widget.ExtendSnackBar;
import com.bvtech.toolslibrary.widget.ExtendToast;
import com.bvtech.toolslibrary.widget.progressbar.BatteryProgressbar;
import com.bvtech.toolslibrary.widget.waveview.WaveView;

import java.util.Calendar;
import java.util.Date;

public class ActivityMain2 extends ActivityEnhanced{

    private WaveView wave;
    private BatteryProgressbar bp, bp2;
    private int prg = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wave = findViewById(R.id.wave);

        bp = findViewById(R.id.batteryProgress);
        bp2 = findViewById(R.id.batteryProgress2);
        //bp.setCharging(true);

        ViewUtility.shrinkExpandAnimation(findViewById(R.id.btn1), 0.95f, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtendSnackBar.snackError(getApplicationContext(), findViewById(R.id.layContent), "Unable to download", ExtendSnackBar.LENGTH_LONG);
            }
        });

        ViewUtility.shrinkExpandAnimation(findViewById(R.id.btn2), 0.95f, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtendSnackBar.snackDone(getApplicationContext(), findViewById(R.id.layContent), "Download done", ExtendSnackBar.LENGTH_LONG);
            }
        });

        Date date = new Date();
        long d = date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d);

        String ss = "" + calendar.get(Calendar.DAY_OF_MONTH);
        TextView tdate = findViewById(R.id.txtDate);
        tdate.setText(ss);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1010);
        }else{

        }

        if(FileUtil.copyFileFromAssetManagerToApplicationDirectory(this, "databases", "databases")){
            Log.i("COPY", "Success");
            ExtendToast.toastDone(this, "Copy success").show();
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    bp.setCharging(true);
                    bp2.setCharging(true);
                    try {
                        Thread.sleep(100);
                        if(prg < bp.getMaxProgress()){
                            prg++;
                            bp.setProgress(prg);
                            bp2.setProgress(prg);
                        }else{
                            prg = 0;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                t.start();
            }
        }, 2000);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1010 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
    }
}
