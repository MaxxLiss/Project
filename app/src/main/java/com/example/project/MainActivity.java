package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.fragments.FragmentSettings;
import com.example.project.fragments.FragmentSongList;
import com.example.project.fragments.SendInfoFromFragment;

public class MainActivity extends AppCompatActivity implements SendInfoFromFragment {

    private AppCompatImageButton btn_bar_music;
    private AppCompatImageButton btn_bar_settings;

    ////
    private TextView test;
    ////

    private LinearLayout main_screen;
    private SwipeRefreshLayout refresh_layout;

    private BluetoothService bluetoothService;

    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////
        test = findViewById(R.id.test);
        ////

        refresh_layout = findViewById(R.id.refresh_layout);
        main_screen = findViewById(R.id.main_screen);
        btn_bar_music = findViewById(R.id.btn_bar_music);
        btn_bar_settings = findViewById(R.id.btn_bar_settings);

        FragmentTransaction ft = fm.beginTransaction();
        FragmentSongList startFragment = new FragmentSongList();
        ft.add(R.id.main_screen, startFragment);
        ft.commit();

        bluetoothService = new BluetoothService();
        startService(new Intent(MainActivity.this, bluetoothService.getClass()));

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                FragmentTransaction ft = fm.beginTransaction();
                FragmentSongList fragmentSongList = new FragmentSongList();
                ft.replace(R.id.main_screen, fragmentSongList);
                ft.commit();

                refresh_layout.setRefreshing(false);
            }
        });

        btn_bar_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                FragmentSongList fragment = new FragmentSongList();
                ft.replace(R.id.main_screen, fragment);
                ft.commit();
            }
        });

        btn_bar_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                FragmentSettings fragment = new FragmentSettings();
                ft.replace(R.id.main_screen, fragment);
                ft.commit();
            }
        });
    }

    @Override
    public void sendNumber(int number) {

        test.setText(String.valueOf(number));

    }

    @Override
    public void autoUpdate(boolean update) {

        bluetoothService.setScanningPeriod(update);

    }
}