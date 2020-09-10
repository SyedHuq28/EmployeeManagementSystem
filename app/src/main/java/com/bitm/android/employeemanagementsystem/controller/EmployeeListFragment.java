package com.bitm.android.employeemanagementsystem.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bitm.android.employeemanagementsystem.R;
import com.bitm.android.employeemanagementsystem.controller.adapters.EmployeeAdapter;
import com.bitm.android.employeemanagementsystem.models.Employee;
import com.bitm.android.employeemanagementsystem.models.viewmodels.EmployeeViewModel;

import java.util.List;

public class EmployeeListFragment extends Fragment implements EmployeeAdapter.OnEmployeeEditDeleteListener {
    private RecyclerView employeeRecyclerView;
    private EmployeeAdapter employeeAdapter;
    private EmployeeViewModel employeeViewModel;
    private ImageView emptyListIV;

    public EmployeeListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        return inflater.inflate(R.layout.fragment_employee_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeRecyclerView = view.findViewById(R.id.employeeRV);
        emptyListIV = view.findViewById(R.id.emptyListIV);
        employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employeeList) {
                if (employeeList.size() == 0) {
                    emptyListIV.setVisibility(View.VISIBLE);
                }
                employeeAdapter = new EmployeeAdapter(getActivity(), employeeList, EmployeeListFragment.this);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());


                GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                employeeRecyclerView.setLayoutManager(llm);
                employeeRecyclerView.setAdapter(employeeAdapter);
            }
        });
    }


    @Override
    public void onEdit(Employee employee) {

    }

    @Override
    public void onDelete(Employee employee) {
        employeeViewModel.delete(employee);
        employeeAdapter.refresh(employee);
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
    }
}