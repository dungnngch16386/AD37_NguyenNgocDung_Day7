package com.example.ad37_nguyenngocdung_day7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contacts;
    Contact contact1, contact2, contact3, contact4, contact5, contact6;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;

    View vAdd;
    int mPosition = -1;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contact");

        //Khai báo SharedPreferences bộ nhớ tạm
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = preferences.edit();

        recyclerView = findViewById(R.id.rvList);
        vAdd = findViewById(R.id.vAdd);

        contacts = new ArrayList<>();
        contact1 = new Contact("0127889153", "Viet Nam", "Mr.A");
        contact2 = new Contact("0123456789", "Viet Nam", "Mr B");
        contact3 = new Contact("0904149378", "Viet Nam", "Mommy");
        contact4 = new Contact("0987654321", "Viet Nam", "Sister");
        contact5 = new Contact("0789321456", "Viet Nam", "Mr.C");
        contact6 = new Contact("0912123768", "Viet Nam", "Dad");
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        contacts.add(contact6);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);

        contactAdapter = new ContactAdapter(contacts, getBaseContext());

        contactAdapter.setIonClickContact(new IonClickContact() {
            @Override
            public void onClickPhone(String phone, int position) {
                mPosition = position;
                Toast.makeText(getBaseContext(), phone, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), AddNewContact.class);
                intent.putExtra("name", phone);
                startActivityForResult(intent, 113);

            }

            @Override
            public void onClickInformation() {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                //intent.putExtra("oblist", contacts.get(i));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(layoutManager);

        vAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), AddNewContact.class);
                intent2.putExtra("name", "");
                // bắt sự kiện khi startActivityForResult để hứng giá trị result trả về là 115 ở hàm onActivityResult
                startActivityForResult(intent2, 115);
            }
        });
    }

    // khởi tạo Menu tổng thể
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // bắt sự kiện onclick menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnFull:
                //put vào SharedPreferences
                editor.putBoolean("full_mode", true);
                editor.commit();
                //làm mới lại list
                contactAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Menu Full", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnShort:
                editor.putBoolean("full_mode", false);
                editor.commit();
                contactAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Menu Short", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                String name = data.getStringExtra("nameAdd");
                String phone = data.getStringExtra("phoneAdd");
                String country = data.getStringExtra("countryAdd");

                if (requestCode == 113) {
                    contacts.set(mPosition, new Contact(phone, country, name));
                    contactAdapter.notifyDataSetChanged();
                } else if (requestCode == 115) {
                    contacts.add(new Contact(phone, country, name));
                    contactAdapter.notifyDataSetChanged();
                }
        }
    }
}
