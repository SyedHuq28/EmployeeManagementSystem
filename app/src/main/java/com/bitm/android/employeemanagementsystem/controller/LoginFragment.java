package com.bitm.android.employeemanagementsystem.controller;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitm.android.employeemanagementsystem.R;

public class LoginFragment extends Fragment {
    private EditText emailET, passET;
    private Button loginBtn;
    private EmployeePreference employeePreference;

    public LoginFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        employeePreference = new EmployeePreference(context);
        if (employeePreference.getLoginStatus()) {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_loginFragment_to_dashboardFragment);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailET = view.findViewById(R.id.emailInput);
        passET = view.findViewById(R.id.passwordInput);
        loginBtn = view.findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passET.getText().toString();


                if (email.isEmpty()){
                    emailET.setError("Please Provide Email");
                    return;
                }
                if (password.isEmpty()){
                    passET.setError("Please Provide Valid Name");
                    return;
                }



                if(email.equals("admin") && password.equals("admin")) {

                    employeePreference.setLoginStatus(true);
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_dashboardFragment);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }

               else{
                    employeePreference.setLoginStatus(true);
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_employeeDashboard);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

