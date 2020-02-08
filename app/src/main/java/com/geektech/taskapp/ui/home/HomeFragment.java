package com.geektech.taskapp.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.taskapp.App;
import com.geektech.taskapp.FormActivity;
import com.geektech.taskapp.MainActivity;
import com.geektech.taskapp.OnItemClickListener;
import com.geektech.taskapp.R;
import com.geektech.taskapp.Task;
import com.geektech.taskapp.room.TaskDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private TaskAdapter adapter;
    private List<Task> list;

    AlertDialog.Builder ad;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        list = new ArrayList<>();
        App.getDatabase().taskDao().getAllLive().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(final List<Task> tasks) {
                list.clear();
                list.addAll(tasks);
                Collections.reverse(list);
                adapter.notifyDataSetChanged();


            }
        });
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));



        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Task task = list.get(position);
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("Task", task);
                startActivity(intent);

            }

            @Override
            public void OnLongClick(final int position) {
                ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("Выберите действие");
                ad.setMessage("Вы точно хотите удалить?");
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getDatabase().taskDao().delete(list.get(position));

                    }

                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();


                    }
                });

                ad.show();
            }
        });
        return root;
    }

/*    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 55 && resultCode == Activity.RESULT_OK) {
            Task task = (Task) data.getSerializableExtra("task");
            list.add(task);
            adapter.notifyDataSetChanged();
        }*/
    }