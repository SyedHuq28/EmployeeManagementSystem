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

public class DashboardFragment extends Fragment {
    private Button addNewEmpBtn, viewEmpBtn;
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNewEmpBtn = view.findViewById(R.id.add_new_employee_btn);
        viewEmpBtn = view.findViewById(R.id.view_all_employee_btn);

        addNewEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashboardFragment_to_newEmployeeFragment);
            }
        });

        viewEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashboardFragment_to_employeeListFragment);
            }
        });

    }
}