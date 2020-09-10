package com.bitm.android.employeemanagementsystem.controller;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bitm.android.employeemanagementsystem.R;
import com.bitm.android.employeemanagementsystem.models.Employee;
import com.bitm.android.employeemanagementsystem.models.viewmodels.EmployeeViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewEmployeeFragment extends Fragment {
    private EmployeeViewModel employeeViewModel;
    private final String TAG = "NewEmployeeFragment";
    private Spinner departmentSP, designationSP, locationSP;
    private TextInputEditText nameET, ageET, phoneET, emailET, salaryET;
    private RadioGroup genderRG;
    private Button dobBtn, saveBtn;
    private CheckBox foodCB, transportCB, medicalCB, houseRentCB, mobileCB, othersCB;
    private String gender = "Male", dob, department, designation, location;
    private List<String> allowances = new ArrayList<>();
    private long empID = 0;
    public NewEmployeeFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        return inflater.inflate(R.layout.fragment_new_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            saveBtn.setText("Update");
            empID = bundle.getLong("id");
            employeeViewModel.getSingleEmployee(empID).observe(getViewLifecycleOwner(), new Observer<Employee>() {
                @Override
                public void onChanged(Employee employee) {

                    String name = nameET.getText().toString();



                    nameET.setText(employee.getName());
                    ageET.setText(String.valueOf(employee.getAge()));
                    phoneET.setText(employee.getMobile());
                    emailET.setText(employee.getEmailAddress());
                    salaryET.setText(String.valueOf(employee.getSalary()));
                    dobBtn.setText(employee.getDob());
                    if (employee.getGender().equals("Female")) {
                        genderRG.check(R.id.femaleRB);
                    }
                    departmentSP.setSelection(TempDB.departments.indexOf(employee.getDepartment()));
                    designationSP.setSelection(TempDB.designations.indexOf(employee.getDesignation()));
                    locationSP.setSelection(TempDB.jobLocations.indexOf(employee.getLocation()));
                    String allowances = employee.getAllowances();
                    List<String> allowanceList = Arrays.asList(allowances.split(","));
                    for (String s : allowanceList) {
                        Log.e("Allowances", s);
                    }
                    for (String s : allowanceList) {
                        setAllownaceOnCheckbox(s);
                    }

                }
            });
        }
        foodCB.setOnClickListener(checkboxClickListener);
        medicalCB.setOnClickListener(checkboxClickListener);
        transportCB.setOnClickListener(checkboxClickListener);
        mobileCB.setOnClickListener(checkboxClickListener);
        houseRentCB.setOnClickListener(checkboxClickListener);
        othersCB.setOnClickListener(checkboxClickListener);

        ArrayAdapter<String> depAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TempDB.departments);
        ArrayAdapter<String> desgAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TempDB.designations);
        ArrayAdapter<String> locAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TempDB.jobLocations);

        departmentSP.setAdapter(depAdapter);
        designationSP.setAdapter(desgAdapter);
        locationSP.setAdapter(locAdapter);

        dobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        formatDate(year, month, dayOfMonth);

                    }
                }, 2000, 0, 1);
                datePickerDialog.show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameET.getText().toString();

                if (name.isEmpty()){
                    nameET.setError("Please Provide Valid Name");
                    return;
                }


                final String age = ageET.getText().toString();
                if (age.isEmpty()){
                    ageET.setError("Please Provide Valid age");
                    return;
                }
                final String mobile = phoneET.getText().toString();
                if (mobile.isEmpty()){
                    phoneET.setError("Please Provide Valid mobile number");
                    return;
                }

                final String email = emailET.getText().toString();

                if (email.isEmpty()){
                    emailET.setError("Please Provide Valid email");
                    return;
                }

                final String salary = salaryET.getText().toString();

                if (salary.isEmpty()){
                    salaryET.setError("Please Provide Valid salary");
                    return;
                }

                String allowanceString = TextUtils.join(",", allowances);

                final Employee employee = new Employee(name, Integer.parseInt(age), mobile, email, Double.parseDouble(salary),
                        gender, dob, department, designation, location, allowanceString);
                Log.e(TAG, "onClick: "+employee);

                if (empID > 0) {
                    employee.setId(empID);
                    employeeViewModel.update(employee);
                }else {
                    employeeViewModel.addNewEmployee(employee);
                }
                Navigation.findNavController(v).navigate(R.id.dashboardFragment);

                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();





            }
        });

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final RadioButton rb = view.findViewById(checkedId);
                gender = rb.getText().toString();
            }
        });

        departmentSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        designationSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                designation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        locationSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setAllownaceOnCheckbox(String s) {
        switch (s) {
            case "Food":
                foodCB.setChecked(true);
                break;
            case "Medical":
                medicalCB.setChecked(true);
                break;
            case "Transport":
                transportCB.setChecked(true);
                break;
            case "Mobile":
                mobileCB.setChecked(true);
                break;
            case "HouseRent":
                houseRentCB.setChecked(true);
                break;
            case "Others":
                othersCB.setChecked(true);
                break;

        }
    }

    private void formatDate(int year, int month, int dayOfMonth) {
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd, yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        dob = sdf.format(calendar.getTime());
        dobBtn.setText(dob);
    }

    private void initViews(View view) {
        departmentSP = view.findViewById(R.id.departmentSpinner);
        designationSP = view.findViewById(R.id.designationSpinner);
        locationSP = view.findViewById(R.id.locationSpinner);

        nameET = view.findViewById(R.id.nameInput);
        ageET = view.findViewById(R.id.ageInput);
        emailET = view.findViewById(R.id.emailInput);
        phoneET = view.findViewById(R.id.phoneInput);
        salaryET = view.findViewById(R.id.salaryInput);

        genderRG = view.findViewById(R.id.genderRadioGroup);

        saveBtn = view.findViewById(R.id.saveEmployeeBtn);
        dobBtn = view.findViewById(R.id.dobBtn);

        foodCB = view.findViewById(R.id.foodCB);
        medicalCB = view.findViewById(R.id.medicalCB);
        transportCB = view.findViewById(R.id.transportCB);
        mobileCB = view.findViewById(R.id.phoneCB);
        houseRentCB = view.findViewById(R.id.houseRentCB);
        othersCB = view.findViewById(R.id.othersCB);
    }

    private View.OnClickListener checkboxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final CheckBox cb = (CheckBox) v;
            final String allowance = cb.getText().toString();
            if (cb.isChecked()) {
                allowances.add(allowance);
            }else{
                allowances.remove(allowance);
            }
        }
    };
}