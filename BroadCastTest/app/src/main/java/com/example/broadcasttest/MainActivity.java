package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {

    private IntentFilter filter;

    NetWorkChangeReceiver netWorkChangeR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeR = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeR,filter);

        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeR);
    }

    class NetWorkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetWork = connectivityManager.getActiveNetworkInfo();
            if(activeNetWork !=null && activeNetWork.isAvailable()){
                Toast.makeText(context, "network is availavle",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"network is un available",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
