package com.bitm.android.employeemanagementsystem.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitm.android.employeemanagementsystem.R;
import com.bitm.android.employeemanagementsystem.models.Employee;
import com.bitm.android.employeemanagementsystem.models.viewmodels.EmployeeViewModel;

public class EmployeeDetailsFragment extends Fragment {
    private static final String TAG = EmployeeDetailsFragment.class.getSimpleName();
    private TextView nameTV, depTV, desTV, dobTV, allowanceTV;
    private EmployeeViewModel employeeViewModel;
    public EmployeeDetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        return inflater.inflate(R.layout.fragment_employee_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        final long id = getArguments().getLong("id");
        employeeViewModel.getSingleEmployee(id)
                .observe(getViewLifecycleOwner(), new Observer<Employee>() {
                    @Override
                    public void onChanged(Employee employee) {
                        nameTV.setText(employee.getName());
                        depTV.setText(employee.getDepartment());
                        desTV.setText(employee.getDesignation());
                        dobTV.setText(employee.getDob());
                        allowanceTV.setText(employee.getAllowances());
                        Log.e(TAG, employee.toString());
                    }
                });
    }

    private void initViews(View view) {
        nameTV = view.findViewById(R.id.details_emp_name);
        depTV = view.findViewById(R.id.details_emp_department);
        desTV = view.findViewById(R.id.details_emp_designation);
        dobTV = view.findViewById(R.id.details_emp_dob);
        allowanceTV = view.findViewById(R.id.details_emp_allowance);
    }
}