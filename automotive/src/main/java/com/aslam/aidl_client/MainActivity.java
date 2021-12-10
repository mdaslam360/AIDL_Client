package com.aslam.aidl_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.aslam.aidl_server.ISum;

public class MainActivity extends AppCompatActivity {

    ISum iSum;
    private boolean isBound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iSum = (ISum)ISum.Stub.asInterface(iBinder);
            try {
                int a = iSum.Sum(4, 6);
                User user = (User) iSum.getUser(20);
                Log.d("Aslam", "connected: " + a+" User :"+user.getNumber());
                isBound = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Aslam", "onServiceDisconnected: ");
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Aslam", "onStart: ");

        Intent intent = new Intent();
        intent.setAction("com.aslam.aidl_server.MyAIDLSERVER");
        intent.setPackage("com.aslam.aidl_server");
        boolean serviceBinding =  bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        Log.i("Aslam", "service is bound: " + serviceBinding);
    }
}