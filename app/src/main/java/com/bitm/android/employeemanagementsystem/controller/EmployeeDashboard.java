package com.bitm.android.employeemanagementsystem.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bitm.android.employeemanagementsystem.R;

public class EmployeeDashboard extends Fragment {
    private Button  viewEmpBtn1;
    public EmployeeDashboard() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_employee_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewEmpBtn1 = view.findViewById(R.id.view_all_employee_btn1);



        viewEmpBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_employeeDashboard_to_employeeListFragment);
            }
        });

    }
}