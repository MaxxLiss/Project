package com.example.project.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.BluetoothService;
import com.example.project.FindUser;
import com.example.project.FindUserAdapter;
import com.example.project.MainActivity;
import com.example.project.R;

import java.util.ArrayList;

public class FragmentSongList extends Fragment {

    private RecyclerView rv_songs_list;
    private FindUserAdapter adapter;

    private View screen;

    private TextView tv_user_track_name;
    private AppCompatImageButton btn_bluetooth_state;

    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        screen = inflater.inflate(R.layout.fragment_songs_list, container, false);

        btn_bluetooth_state = screen.findViewById(R.id.btn_bluetooth_state);
        rv_songs_list = screen.findViewById(R.id.rv_songs_list);

        rv_songs_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<FindUser> findUsers = new ArrayList<FindUser>();
        for (int i = 0; i < 100; i++) {
            findUsers.add(i, new FindUser("aaa", "bbb"));
        }

        adapter = new FindUserAdapter(getActivity(), findUsers);
        rv_songs_list.setAdapter(adapter);

        btn_bluetooth_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<FindUser> findUsers = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    findUsers.add(i, new FindUser("bbb", "ccc"));
                    adapter = new FindUserAdapter(getActivity(), findUsers);
                    rv_songs_list.setAdapter(adapter);
                }
            }
        });

        adapter.setOnItemClickListener(new FindUserAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                try {
                    ((SendInfoFromFragment) activity).sendNumber(position);
                } catch (ClassCastException exception) {
                    Log.d("FragmentSongList", "Data not send");
                }
            }
        });


        return screen;
    }

}
