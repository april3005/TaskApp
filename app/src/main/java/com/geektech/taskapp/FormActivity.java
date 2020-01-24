package com.geektech.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {
private EditText editText;
private EditText editDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editText = findViewById(R.id.editText);
        editDesc = findViewById(R.id.editDesc);
    }



    public void onSaveClick(View view) {

        String text = editText.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();
        Intent i = new Intent();
        i.putExtra("title", text);
        i.putExtra("description", desc);
        setResult(RESULT_OK, i);
        finish();
    }
}
