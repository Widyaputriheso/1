package com.example.aplikasiservice;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import java.io.IOException;

public class WallpaperChangeService extends Service implements Runnable{
    private int wallpaperId[] = {R.drawable.ess1, R.drawable.ess2};
    private int waktu;
    private int FLAG=0;
    private Thread t;
    private Bitmap ess1;
    private Bitmap ess2;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand (Intent intent, int flag, int startId)
    {
        super.onStartCommand(intent,flag,startId);
        Bundle bundle=intent.getExtras();
        waktu = bundle.getInt("Durasi");
        ess1= BitmapFactory.decodeResource(getResources(), wallpaperId[0]);
        ess2=BitmapFactory.decodeResource(getResources(),wallpaperId[1]);

        t = new Thread(WallpaperChangeService.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void run() {
        WallpaperManager myWallpaper;
        myWallpaper = WallpaperManager.getInstance(this);
        try {
            while (true){
                if (FLAG==0) {
                    myWallpaper.setBitmap(ess1);
                    FLAG++;
                }
                else {
                    myWallpaper.setBitmap(ess2);
                    FLAG--;
                }
                Thread.sleep(100*waktu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
