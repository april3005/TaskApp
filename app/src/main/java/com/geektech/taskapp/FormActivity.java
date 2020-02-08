package com.geektech.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {
private EditText editText;
private EditText editDesc;
private Task mtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editText = findViewById(R.id.editText);
        editDesc = findViewById(R.id.editDesc);
        edit();
    }

    public void edit() {

        mtask = (Task) getIntent().getSerializableExtra("Task");
        if (mtask != null) {
            editText.setText(mtask.getTitle());
            editDesc.setText(mtask.getDesc());
        }
    }

    public void onSaveClick(View view) {

        String title = editText.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();
        if (editText.getText().toString().equals("") && editDesc.getText().toString().equals("") ) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please insert your text!",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else{
            mtask = new Task(title, desc);
            App.getDatabase().taskDao().insert(mtask);

        }

        if (mtask != null) {
            mtask.setTitle(title);
            mtask.setDesc(desc);
            App.getDatabase().taskDao().update(mtask);
        }
        finish();
    }

   // if (title.isEmpty || desc.isEmpty){ // для Стринг типа
    //    Toast
    }

   // if (TextUtils.isEmpty)

