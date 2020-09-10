package com.bitm.android.employeemanagementsystem.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bitm.android.employeemanagementsystem.R;

public class MainActivity extends AppCompatActivity {
    private EmployeePreference employeePreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeePreference = new EmployeePreference(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_settings:

                break;

            case R.id.item_logout:
                employeePreference.setLoginStatus(false);
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.loginFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

