package com.example.ad37_nguyenngocdung_day7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewContact extends AppCompatActivity {
    String mName, mPhone, mCountry;
    EditText etName, etPhone, etCountry;
    Button btnAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        setTitle("Add new Contact");

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etCountry = findViewById(R.id.etCountry);
        btnAction = findViewById(R.id.btnAction);

        final Intent intent = getIntent();

        mName = intent.getStringExtra("name");
        mPhone = intent.getStringExtra("phone");
        mCountry = intent.getStringExtra("country");

        if (mName.equals("")) btnAction.setText("Add");
        else {
            btnAction.setText("Update");
            etName.setText(mName);
            etPhone.setText(mPhone);
            etCountry.setText(mCountry);
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("nameAdd", etName.getText().toString());
                intent1.putExtra("phoneAdd", etPhone.getText().toString());
                intent1.putExtra("countryAdd", etCountry.getText().toString());

                //trả về kết quả Result cho mainActivity
                setResult(RESULT_OK, intent1);
                finish();
            }
        });

    }
}
